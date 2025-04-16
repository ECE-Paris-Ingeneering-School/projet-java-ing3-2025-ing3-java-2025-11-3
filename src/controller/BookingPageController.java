package controller;

import javafx.stage.Stage;
import view.BookingPageView;
import view.ReservationView;
import MODELE.Hebergement;


public class BookingPageController {

    private Stage primaryStage;
    private BookingPageView view;
    private Hebergement hebergement; // à ajouter

    public BookingPageController(Stage primaryStage, Hebergement hebergement) {
        this.primaryStage = primaryStage;
        this.hebergement = hebergement;
        this.view = new BookingPageView(hebergement);
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
