// src/main/java/controller/ContactController.java
package controller;

import javafx.stage.Stage;
import view.ContactView;
import view.ReservationView;

public class ContactController {

    private Stage primaryStage;
    private ContactView view;

    public ContactController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new ContactView();
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        // Titre -> HomePage
        view.getNavBarView().getTitleLabel().setOnMouseClicked(e -> {
            HomePageController homeController = new HomePageController(primaryStage);
            homeController.show();
        });

        // Recherche
        view.getNavBarView().getRechercheLabel().setOnMouseClicked(e -> {
            SearchPageController searchController = new SearchPageController(primaryStage);
            searchController.show();
        });

        // Réservations
        view.getNavBarView().getReservationsLabel().setOnMouseClicked(e -> {
            ReservationView rv = new ReservationView();
            new ReservationController(primaryStage, rv);
            primaryStage.setScene(rv.getScene());
        });

        // Contact (recharge la page Contact)
        view.getNavBarView().getContactLabel().setOnMouseClicked(e -> {
            ContactController contactController = new ContactController(primaryStage);
            contactController.show();
        });

        // Sign in
        view.getNavBarView().getSignInLabel().setOnMouseClicked(e -> {
            LoginPageController loginController = new LoginPageController(primaryStage);
            loginController.show();
        });

        // Register
        view.getNavBarView().getRegisterLabel().setOnMouseClicked(e -> {
            RegisterPageController registerController = new RegisterPageController(primaryStage);
            registerController.show();
        });
    }

    public void show() {
        // préserver taille et plein écran
        boolean fullScreen = primaryStage.isFullScreen();
        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();

        primaryStage.setScene(view.getScene());
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setFullScreen(fullScreen);
        primaryStage.show();
    }
}
