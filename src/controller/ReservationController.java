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
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Controller for the reservations page: manages navigation, data loading, and user actions.
 */
public class ReservationController {

    private final Stage primaryStage;
    private final ReservationView view;
    private final ReservationDao reservationDao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final AvisDao avisDao ;

    /**
     * Bundle of data loaded from the database.
     */
    private static class DataBundle {
        final List<Reservation> reservations;
        final Map<Integer, Reservation> reservationMap;

        DataBundle(List<Reservation> reservations) {
            this.reservations = reservations;
            this.reservationMap = reservations.stream()
                    .collect(Collectors.toMap(Reservation::getId, r -> r));
        }
    }

    /**
     * Initializes controller with DAO and event handlers.
     *
     * @param primaryStage the main application stage
     */
    public ReservationController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new ReservationView();
        this.reservationDao = new ReservationDaoImpl(new AzureDBConnector());
        this.avisDao = new AvisDaoImpl(new AzureDBConnector());  // <— initialisation

        configureEventHandlers();
    }

    /**
     * Binds navigation actions to navbar items.
     */
    private void configureEventHandlers() {
        NavBarView nav = view.getNavBarView();

        nav.getSignInLabel().setOnMouseClicked(e -> navigate(() -> new LoginPageController(primaryStage).show()));
        nav.getRechercheLabel().setOnMouseClicked(e -> navigate(() -> new SearchPageController(primaryStage).show()));
        nav.getReservationsLabel().setOnMouseClicked(e -> navigate(this::show));
        nav.getTitleLabel().setOnMouseClicked(e -> navigate(() -> new HomePageController(primaryStage).show()));
    }

    /**
     * Shows the reservations scene, or prompts login if user is not authenticated.
     */
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

    /**
     * Displays a loading spinner while data is fetched.
     */
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

    /**
     * Fetches reservations asynchronously and displays them.
     *
     * @param userId the ID of the authenticated user
     */
    private void loadReservations(int userId) {
        Task<DataBundle> task = new Task<>() {
            @Override
            protected DataBundle call() {
                List<Reservation> list = reservationDao.getAllReservationByClientId(userId);
                return new DataBundle(list);
            }

            @Override
            protected void succeeded() {
                displayReservations(getValue());
            }

            @Override
            protected void failed() {
                Throwable ex = getException();
                Alert errorAlert = new Alert(Alert.AlertType.ERROR,
                        "Erreur lors du chargement : " + ex.getMessage());
                errorAlert.showAndWait();
            }
        };
        executor.submit(task);
    }

    /**
     * Populates the view with fetched reservations and binds item actions.
     *
     * @param data the loaded reservation data bundle
     */
    private void displayReservations(DataBundle data) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter dbFmt = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate today = LocalDate.now();

        List<ReservationView.Reservation> allViews = data.reservations.stream()
                .map(res -> {
                    LocalDate start = LocalDate.parse(res.getDateDebut(), dbFmt);
                    LocalDate end = LocalDate.parse(res.getDateFin(), dbFmt);

                    String arrival = start.format(fmt);
                    String departure = end.format(fmt);


                    String image = Optional.ofNullable(res.getHebergement().getImage())
                            .filter(list -> !list.isEmpty())
                            .map(ArrayList::getFirst)
                            .orElse("file:src/resources/larry.jpg");

                    return new ReservationView.Reservation(
                            res.getId(),
                            res.getHebergement().getNom(),
                            arrival,
                            departure,
                            res.getPrix() + "€",
                            image,
                            res.getHebergement().getAdresse(),
                            res.getHebergement().getPrix() + "€"
                    );
                })
                .toList();

        //filtrer reservations into past and upcoming
        List<ReservationView.Reservation> past = allViews.stream()
                .filter(rv -> LocalDate.parse(rv.getArrival(), fmt).isBefore(today))
                .collect(Collectors.toList());
        List<ReservationView.Reservation> upcoming = allViews.stream()
                .filter(rv -> !LocalDate.parse(rv.getArrival(), fmt).isBefore(today))
                .collect(Collectors.toList());

        ReservationView.ReservationActionHandler upcomingHandler = new ReservationView.ReservationActionHandler() {
            @Override
            public void onView(ReservationView.Reservation rv) {
                Reservation full = data.reservationMap.get(rv.getReservationId());
                if (full != null) new BookingPageController(primaryStage, full.getHebergement());
            }

            @Override
            public void onCancel(ReservationView.Reservation rv) {
                Reservation full = data.reservationMap.get(rv.getReservationId());
                if (full != null) {
                    Alert conf = new Alert(Alert.AlertType.CONFIRMATION,
                            "Voulez-vous annuler la réservation de : " + full.getHebergement().getNom() + " ?");
                    Optional<ButtonType> ans = conf.showAndWait();
                    if (ans.isPresent() && ans.get() == ButtonType.OK) {
                        reservationDao.annulerReservation(full.getId());
                        show();
                    }
                }
            }
        };

        ReservationView.ReservationActionHandler pastHandler =
                new ReservationView.ReservationActionHandler() {
                    @Override
                    public void onView(ReservationView.Reservation rv) {
                        Reservation full = data.reservationMap.get(rv.getReservationId());
                        if (full != null) new BookingPageController(primaryStage, full.getHebergement());
                    }
                    @Override
                    public void onCancel(ReservationView.Reservation rv) {
                        Reservation full = data.reservationMap.get(rv.getReservationId());
                        if (full == null) return;

                        int clientId = UserSession.getInstance().getConnectedUser().getId();

                        //print avis DANS la console
                        System.out.println(full.getHebergement().getHid());

                        Optional<Avis> result = view.showEvaluationDialog(
                                full.getHebergement().getNom(),
                                full.getHebergement().getHid(),
                                clientId
                        );

                        result.ifPresent(avis -> {
                            avisDao.saveAvis(avis);

                            new Alert(Alert.AlertType.INFORMATION,
                                    "Merci pour votre évaluation !").showAndWait();
                        });
                    }
                };

        view.setReservations(upcoming, upcomingHandler);
        view.setPastReservations(past, pastHandler);

        boolean wasFull = primaryStage.isFullScreen();
        double w = primaryStage.getWidth(), h = primaryStage.getHeight();
        primaryStage.setScene(view.getScene());
        primaryStage.setWidth(w);
        primaryStage.setHeight(h);
        primaryStage.setFullScreen(wasFull);
        primaryStage.show();
    }

    /**
     * Helper to preserve window state across navigation.
     *
     * @param showAction action that sets and shows the new scene
     */
    private void navigate(Runnable showAction) {
        boolean wasFull = primaryStage.isFullScreen();
        double w = primaryStage.getWidth();
        double h = primaryStage.getHeight();
        showAction.run();
        primaryStage.setWidth(w);
        primaryStage.setHeight(h);
        primaryStage.setFullScreen(wasFull);
    }
}
