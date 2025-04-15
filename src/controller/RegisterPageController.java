package controller;

import javafx.stage.Stage;
import view.RegisterPageView;
import view.ReservationView;
import view.SearchPageView;

public class RegisterPageController {

    private Stage primaryStage;
    private RegisterPageView view;

    public RegisterPageController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new RegisterPageView();
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        // Action sur le bouton "Créer un compte"
        view.getCreateAccountButton().setOnAction(e -> {
            System.out.println("Tentative de création de compte...");
            // TODO: Implémenter la logique d'inscription
        });

        // Lien "Se connecter" dans le formulaire
        view.getLoginLink().setOnAction(e -> {
            new LoginPageController(primaryStage).show();
        });

        // Navigation via la NavBar
        view.getNavBarView().getTitleLabel().setOnMouseClicked(e -> {
            new HomePageController(primaryStage).show();
        });

        view.getNavBarView().getSignInLabel().setOnMouseClicked(e -> {
            new LoginPageController(primaryStage).show();
        });

        view.getNavBarView().getRegisterLabel().setOnMouseClicked(e -> {
            this.show(); // On est déjà sur cette page
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
