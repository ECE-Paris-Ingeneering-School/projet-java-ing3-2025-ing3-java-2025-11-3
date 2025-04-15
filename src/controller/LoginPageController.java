package controller;

import javafx.stage.Stage;
import view.LoginPageView;
import view.ReservationView;
import view.SearchPageView;

public class LoginPageController {

    private Stage primaryStage;
    private LoginPageView view;

    public LoginPageController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new LoginPageView();
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        // Action sur le bouton "Connexion"
        view.getLoginButton().setOnAction(e -> {
            System.out.println("Tentative de connexion...");
            // TODO: Implémenter la logique de connexion
        });

        // Lien "S'inscrire" dans le formulaire
        view.getRegisterLink().setOnAction(e -> {
            new RegisterPageController(primaryStage).show();
        });

        // Navigation via la NavBar
        view.getNavBarView().getTitleLabel().setOnMouseClicked(e -> {
            new HomePageController(primaryStage).show();
        });

        view.getNavBarView().getSignInLabel().setOnMouseClicked(e -> {
            this.show(); // On est déjà sur cette page
        });

        view.getNavBarView().getRegisterLabel().setOnMouseClicked(e -> {
            new RegisterPageController(primaryStage).show();
        });

        view.getNavBarView().getRechercheLabel().setOnMouseClicked(e -> {
            new SearchPageController(primaryStage).show();
        });

        view.getNavBarView().getReservationsLabel().setOnMouseClicked(e -> {
            ReservationView reservationView = new ReservationView();
            new ReservationController(primaryStage, reservationView);
            primaryStage.setScene(reservationView.getScene());
        });
    }

    public void show() {
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
