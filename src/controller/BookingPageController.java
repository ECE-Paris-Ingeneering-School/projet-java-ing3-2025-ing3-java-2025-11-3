package controller;

import Dao.HebergementDaoImpl;
import MODELE.Options;
import db.AzureDBConnector;
import javafx.stage.Stage;
import view.BookingPageView;
import view.ReservationView;
import MODELE.Hebergement;
import MODELE.Avis;
import Dao.HebergementDao;

import java.util.List;

public class BookingPageController {

    private final Stage primaryStage;
    private final BookingPageView view;
    private Hebergement hebergement; // à ajouter

    public BookingPageController(Stage primaryStage, Hebergement hebergement) {
        this.primaryStage = primaryStage;
        this.hebergement = hebergement;

        HebergementDaoImpl hebergementDao = new HebergementDaoImpl(new AzureDBConnector());

        List<Avis> avisList = hebergementDao.getAllAvis(hebergement.getHid());
        List<Options> optionsList = hebergementDao.getOption(hebergement.getHid());

        this.view = new BookingPageView(hebergement, avisList, optionsList);

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
