package controller;

import Dao.HebergementDaoImpl;
import MODELE.Options;
import MODELE.Avis;
import MODELE.Hebergement;
import db.AzureDBConnector;
import Dao.HebergementDao;
import javafx.stage.Stage;
import view.BookingPageView;
import view.ReservationView;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import java.util.List;

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

            @Override
            protected Void call() throws Exception {
                HebergementDao hebergementDao = new HebergementDaoImpl(new AzureDBConnector());
                avisList = hebergementDao.getAllAvis(hebergement.getHid());
                optionsList = hebergementDao.getOption(hebergement.getHid());
                return null;
            }

            @Override
            protected void succeeded() {
                view = new BookingPageView(hebergement, avisList, optionsList);
                initController();

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
        view.getNavBarView().getReservationsLabel().setOnMouseClicked(e -> {
            ReservationView reservationView = new ReservationView();
            new ReservationController(primaryStage, reservationView);
            primaryStage.setScene(reservationView.getScene());
        });


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
