package controller;

import Dao.HebergementDaoImpl;
import Dao.ReservationDaoImpl;
import MODELE.Options;
import MODELE.Avis;
import MODELE.Hebergement;
import MODELE.Reservation;
import db.AzureDBConnector;
import Dao.HebergementDao;
import javafx.scene.control.DateCell;
import javafx.stage.Stage;
import view.BookingPageView;
import view.ReservationView;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import Dao.ReservationDao;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookingPageController {

    private final Stage primaryStage;
    private BookingPageView view;

    public BookingPageController(Stage primaryStage, Hebergement hebergement) {
        this.primaryStage = primaryStage;

        ProgressIndicator progressIndicator = new ProgressIndicator();
        VBox loadingScreen = new VBox(progressIndicator);
        loadingScreen.setAlignment(Pos.CENTER);
        Scene loadingScene = new Scene(loadingScreen, 400, 200);
        primaryStage.setScene(loadingScene);
        primaryStage.show();

        new Thread(new Task<Void>() {
            private List<Avis> avisList;
            private List<Options> optionsList;
            private List<Reservation> reservationList;

            @Override
            protected Void call() throws Exception {
                HebergementDao hebergementDao = new HebergementDaoImpl(new AzureDBConnector());
                avisList = hebergementDao.getAllAvis(hebergement.getHid());
                optionsList = hebergementDao.getOption(hebergement.getHid());

                ReservationDao reservationDao = new ReservationDaoImpl(new AzureDBConnector());
                reservationList = reservationDao.getAllReservationByHebergementId(hebergement.getHid());
                return null;
            }


            @Override
            protected void succeeded() {
                view = new BookingPageView(hebergement, avisList, optionsList);
                initController();

                Set<LocalDate> datesReservees = new HashSet<>();

                for (Reservation res : reservationList) {
                    LocalDate debut = LocalDate.parse(res.getDateDebut());
                    LocalDate fin = LocalDate.parse(res.getDateFin());
                    LocalDate current = debut;

                    while (!current.isAfter(fin)) {
                        datesReservees.add(current);
                        current = current.plusDays(1);
                    }
                }

                view.getDateArriveePicker().setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        if (date.isBefore(LocalDate.now()) || datesReservees.contains(date)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                });

                view.getDateDepartPicker().setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        if (date.isBefore(LocalDate.now()) || datesReservees.contains(date)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                });

                primaryStage.setScene(view.getScene());
                primaryStage.show();

            }

            @Override
            protected void failed() {
                System.err.println("Erreur lors du chargement des données : " + getException());
            }
        }).start();
    }

    private void initController() {
        view.getNavBarView().getTitleLabel().setOnMouseClicked(e -> new HomePageController(primaryStage).show());
        view.getNavBarView().getSignInLabel().setOnMouseClicked(e -> new LoginPageController(primaryStage).show());
        view.getNavBarView().getRegisterLabel().setOnMouseClicked(e -> new RegisterPageController(primaryStage).show());
        view.getNavBarView().getRechercheLabel().setOnMouseClicked(e -> new SearchPageController(primaryStage).show());
        view.getNavBarView().getReservationsLabel().setOnMouseClicked(e -> {new ReservationController(primaryStage).show();});

        view.getBackButton().setOnAction(e -> new SearchPageController(primaryStage).show());

        // Bouton "Réserver"
        view.getReserverButton().setOnAction(e -> {
            System.out.println("Réservation en cours...");
            // TODO: implémenter la logique de réservation
        });
    }

    public void show() {
        primaryStage.setScene(view.getScene());
        primaryStage.show();
    }
}
