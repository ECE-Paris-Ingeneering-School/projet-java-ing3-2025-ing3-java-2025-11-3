package controller;

import javafx.stage.Stage;
import view.HomePageView;
import view.ReservationView;
import view.LoginPageView;
import view.RegisterPageView;
import view.SearchPageView;

public class HomePageController {

    private Stage primaryStage;
    private HomePageView view;

    public HomePageController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new HomePageView();
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        // Si on clique sur "Sign in", on passe à la page de connexion
        view.getNavBarView().getSignInLabel().setOnMouseClicked(e -> {
            new LoginPageController(primaryStage).show();
        });

        // Si on clique sur "Register", on passe à la page d'inscription
        view.getNavBarView().getRegisterLabel().setOnMouseClicked(e -> {
            new RegisterPageController(primaryStage).show();
        });

        // Si on clique sur "Recherche", on passe à la page de recherche
        view.getNavBarView().getRechercheLabel().setOnMouseClicked(e -> {
            new SearchPageController(primaryStage).show();
        });

        // SI on clique sur "Réservations", on passe à la page de gestion des réservations
        view.getNavBarView().getReservationsLabel().setOnMouseClicked(e -> {
            ReservationView reservationView = new ReservationView();
            new ReservationController(reservationView);  // Instanciation du contrôleur des réservations
            primaryStage.setScene(reservationView.getScene());
        });

        // Cliquer sur le titre renvoie à la HomePage
        view.getNavBarView().getTitleLabel().setOnMouseClicked(e -> {
            new HomePageController(primaryStage).show();
        });
    }

    public void show() {
        primaryStage.setScene(view.getScene());
        primaryStage.show();
    }
}
