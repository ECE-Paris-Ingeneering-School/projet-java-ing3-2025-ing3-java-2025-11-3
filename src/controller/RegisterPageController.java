package controller;

import javafx.stage.Stage;
import view.RegisterPageView;

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
            LoginPageController loginController = new LoginPageController(primaryStage);
            loginController.show();
        });
        // Navigation via la NavBar
        view.getNavBarView().getTitleLabel().setOnMouseClicked(e -> {
            HomePageController homeController = new HomePageController(primaryStage);
            homeController.show();
        });
        view.getNavBarView().getSignInLabel().setOnMouseClicked(e -> {
            LoginPageController loginController = new LoginPageController(primaryStage);
            loginController.show();
        });
        view.getNavBarView().getRegisterLabel().setOnMouseClicked(e -> {
            // On est déjà sur la page inscription : on peut recharger la page
            this.show();
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
