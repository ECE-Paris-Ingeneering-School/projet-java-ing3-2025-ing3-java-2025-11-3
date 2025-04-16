package controller;

import db.AzureDBConnector;
import javafx.stage.Stage;
import view.BookingPageView;
import view.ReservationView;
import MODELE.Hebergement;
import MODELE.Avis;
import Dao.AvisDaoImpl;

import java.util.List;


public class BookingPageController {

    private Stage primaryStage;
    private BookingPageView view;
    private Hebergement hebergement; // à ajouter
    private AvisDaoImpl avisDao; // à ajouter

    public BookingPageController(Stage primaryStage, Hebergement hebergement) {
        this.primaryStage = primaryStage;
        this.hebergement = hebergement;

        this.avisDao = new AvisDaoImpl(new AzureDBConnector()); // à ajouter

        List<Avis> avisList = avisDao.getAvisByHebergementId(hebergement.getHid()); // méthode à ajouter
        this.view = new BookingPageView(hebergement, avisList);

        initController();
    }

    private void initController() {
        // Navigation via NavBar
        view.getNavBarView().getTitleLabel().setOnMouseClicked(e -> new HomePageController(primaryStage).show());
        view.getNavBarView().getSignInLabel().setOnMouseClicked(e -> new LoginPageController(primaryStage).show());
        view.getNavBarView().getRegisterLabel().setOnMouseClicked(e -> new RegisterPageController(primaryStage).show());
        view.getNavBarView().getRechercheLabel().setOnMouseClicked(e -> new SearchPageController(primaryStage).show());
        view.getNavBarView().getReservationsLabel().setOnMouseClicked(e -> {
            ReservationView reservationView = new ReservationView();
            new ReservationController(primaryStage, reservationView);
            primaryStage.setScene(reservationView.getScene());
        });

        // Bouton "Réserver"
        view.getReserverButton().setOnAction(e -> {
            System.out.println("Réservation en cours...");
            // TODO: Logique de réservation
        });
    }

    public void show() {
        primaryStage.setScene(view.getScene());
        primaryStage.show();
    }
}
