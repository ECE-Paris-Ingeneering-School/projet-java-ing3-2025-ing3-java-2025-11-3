package controller;

import javafx.stage.Stage;
import view.LoginPageView;

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
            RegisterPageController registerController = new RegisterPageController(primaryStage);
            registerController.show();
        });
        // Navigation via la NavBar
        view.getNavBarView().getTitleLabel().setOnMouseClicked(e -> {
            HomePageController homeController = new HomePageController(primaryStage);
            homeController.show();
        });
        view.getNavBarView().getSignInLabel().setOnMouseClicked(e -> {
            // On est déjà sur la page connexion : on peut recharger la page
            this.show();
        });
        view.getNavBarView().getRegisterLabel().setOnMouseClicked(e -> {
            RegisterPageController registerController = new RegisterPageController(primaryStage);
            registerController.show();
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
