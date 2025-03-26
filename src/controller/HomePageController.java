package controller;

import javafx.stage.Stage;
import view.HomePageView;

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
            LoginPageController loginController = new LoginPageController(primaryStage);
            loginController.show();
        });
        // Si on clique sur "Register", on passe à la page d'inscription
        view.getNavBarView().getRegisterLabel().setOnMouseClicked(e -> {
            RegisterPageController registerController = new RegisterPageController(primaryStage);
            registerController.show();
        });
        // Si on clique sur le titre, on recharge la HomePage
        view.getNavBarView().getTitleLabel().setOnMouseClicked(e -> {
            HomePageController homeController = new HomePageController(primaryStage);
            homeController.show();
        });
    }

    public void show() {
        // Récupérer et réappliquer la taille et le mode plein écran
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
