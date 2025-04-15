package controller;

import javafx.stage.Stage;
import view.ReservationView;
import view.HomePageView;
import view.LoginPageView;
import view.RegisterPageView;
import view.SearchPageView;

/**
 * Contrôleur pour la page de gestion des réservations.
 * La barre de navigation permet de naviguer entre les différentes pages de l'application.
 */
public class ReservationController {

    private Stage primaryStage;
    private ReservationView view;

    public ReservationController(Stage primaryStage, ReservationView view) {
        this.primaryStage = primaryStage;
        this.view = view;
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        // Navigation vers la page de connexion
        view.getNavBarView().getSignInLabel().setOnMouseClicked(e -> {
            new LoginPageController(primaryStage).show();
        });

        // Navigation vers la page d'inscription
        view.getNavBarView().getRegisterLabel().setOnMouseClicked(e -> {
            new RegisterPageController(primaryStage).show();
        });

        // Navigation vers la page de recherche
        view.getNavBarView().getRechercheLabel().setOnMouseClicked(e -> {
            new SearchPageController(primaryStage).show();
        });

        // Navigation vers la page de réservations
        view.getNavBarView().getReservationsLabel().setOnMouseClicked(e -> {
            ReservationView newReservationView = new ReservationView();
            new ReservationController(primaryStage, newReservationView);
            primaryStage.setScene(newReservationView.getScene());
        });

        // Retour à la page d'accueil
        view.getNavBarView().getTitleLabel().setOnMouseClicked(e -> {
            new HomePageController(primaryStage).show();
        });
    }

    public void show() {
        primaryStage.setScene(view.getScene());
        primaryStage.show();
    }
}
