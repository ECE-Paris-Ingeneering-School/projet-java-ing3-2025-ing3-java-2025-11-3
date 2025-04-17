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

import java.util.List;

public class BookingPageController {

    private final Stage primaryStage;
    private final BookingPageView view;
    private Hebergement hebergement;

    public BookingPageController(Stage primaryStage, Hebergement hebergement) {
        this.primaryStage = primaryStage;
        this.hebergement = hebergement;

        HebergementDao hebergementDao = new HebergementDaoImpl(new AzureDBConnector());
        List<Avis> avisList = hebergementDao.getAllAvis(hebergement.getHid());
        List<Options> optionsList = hebergementDao.getOption(hebergement.getHid());

        this.view = new BookingPageView(hebergement, avisList, optionsList);
        initController();
    }

    private void initController() {
        // NavBar
        view.getNavBarView().getTitleLabel().setOnMouseClicked(e -> new HomePageController(primaryStage).show());
        view.getNavBarView().getSignInLabel().setOnMouseClicked(e -> new LoginPageController(primaryStage).show());
        view.getNavBarView().getRegisterLabel().setOnMouseClicked(e -> new RegisterPageController(primaryStage).show());
        view.getNavBarView().getRechercheLabel().setOnMouseClicked(e -> new SearchPageController(primaryStage).show());
        view.getNavBarView().getReservationsLabel().setOnMouseClicked(e -> {
            ReservationView reservationView = new ReservationView();
            new ReservationController(primaryStage, reservationView);
            primaryStage.setScene(reservationView.getScene());
        });

        // Bouton Retour
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
