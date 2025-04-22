package controller;

import dao.AvisDao;
import dao.AvisDaoImpl;
import modele.Avis;

import dao.ReservationDao;
import dao.ReservationDaoImpl;
import db.AzureDBConnector;
import modele.Reservation;
import modele.User;
import view.NavBarView;
import view.ReservationView;

import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ReservationController {

    private final Stage primaryStage;
    private final ReservationView view;
    private final ReservationDao reservationDao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final AvisDao avisDao;

    public ReservationController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new ReservationView();
        this.reservationDao = new ReservationDaoImpl(new AzureDBConnector());
        this.avisDao = new AvisDaoImpl(new AzureDBConnector());

        configureEventHandlers();
    }

    private void configureEventHandlers() {
        new NavBarController(primaryStage, view.getNavBarView());
    }

    public void show() {
        User currentUser = UserSession.getInstance().getConnectedUser();
        if (currentUser == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Veuillez-vous connecter ou créer un compte pour accéder à vos réservations");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new LoginPageController(primaryStage).show();
            }
            return;
        }
        showLoadingScreen();
        loadReservations(currentUser.getId());
    }

    private void showLoadingScreen() {
        ProgressIndicator loader = new ProgressIndicator();
        VBox container = new VBox(20, loader);
        container.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane(container);
        root.setStyle("-fx-background-color: #F9F9F9;");
        Scene scene = new Scene(root, 900, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadReservations(int userId) {
        Task<List<Reservation>> task = new Task<>() {
            @Override
            protected List<Reservation> call() {
                return reservationDao.getAllReservationByClientId(userId);
            }
        };
        task.setOnSucceeded(e -> displayReservations(task.getValue()));
        task.setOnFailed(e -> {
            Throwable ex = task.getException();
            new Alert(Alert.AlertType.ERROR, "Erreur lors du chargement : " + ex.getMessage())
                    .showAndWait();
        });
        executor.submit(task);
    }

    private void displayReservations(List<Reservation> reservations) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter dbFmt = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate today = LocalDate.now();

        // Partition into past and upcoming
        Map<Boolean, List<Reservation>> partition = reservations.stream()
                .collect(Collectors.partitioningBy(r -> {
                    LocalDate start = LocalDate.parse(r.getDateDebut(), dbFmt);
                    return !start.isBefore(today);
                }));

        List<Reservation> upcoming = partition.get(true);
        List<Reservation> past = partition.get(false);

        // Handler for upcoming
        ReservationView.ReservationActionHandler upcomingHandler = new ReservationView.ReservationActionHandler() {
            @Override
            public void onView(Reservation r) {
                new BookingPageController(primaryStage, r.getHebergement()).show();
            }
            @Override
            public void onCancel(Reservation r) {
                Alert conf = new Alert(Alert.AlertType.CONFIRMATION,
                        "Voulez-vous annuler la réservation de : " + r.getHebergement().getNom() + " ?");
                Optional<ButtonType> ans = conf.showAndWait();
                if (ans.isPresent() && ans.get() == ButtonType.OK) {
                    reservationDao.annulerReservation(r.getId());
                    show();
                }
            }
        };

        // Handler for past (evaluation)
        ReservationView.ReservationActionHandler pastHandler = new ReservationView.ReservationActionHandler() {
            @Override
            public void onView(Reservation r) {
                new BookingPageController(primaryStage, r.getHebergement());
            }
            @Override
            public void onCancel(Reservation r) {
                int clientId = UserSession.getInstance().getConnectedUser().getId();
                Optional<Avis> result = view.showEvaluationDialog(
                        r.getHebergement().getNom(),
                        r.getHebergement().getHid(),
                        clientId);
                result.ifPresent(avis -> {
                    avisDao.saveAvis(avis);
                    new Alert(Alert.AlertType.INFORMATION, "Merci pour votre évaluation !")
                            .showAndWait();
                });
            }
        };

        view.setReservations(upcoming, upcomingHandler);
        view.setPastReservations(past, pastHandler);

        Scene scene = view.getScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void navigate(Runnable showAction) {
        boolean wasFull = primaryStage.isFullScreen();
        double w = primaryStage.getWidth(), h = primaryStage.getHeight();
        showAction.run();
        primaryStage.setWidth(w);
        primaryStage.setHeight(h);
        primaryStage.setFullScreen(wasFull);
    }
}
