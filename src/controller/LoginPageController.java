package controller;

import view.LoginPageView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

/**
 * Contrôleur de la page de connexion.
 */
public class LoginPageController {

    private Stage primaryStage;
    private LoginPageView view;

    public LoginPageController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new LoginPageView();
        attachEvents();
    }

    private void attachEvents() {
        // Bouton "Connexion"
        view.getLoginButton().setOnAction((ActionEvent e) -> {
            System.out.println("Tentative de connexion...");
            // TODO: logique de connexion
        });

        // Lien "S'inscrire"
        view.getRegisterLink().setOnAction((ActionEvent e) -> {
            RegisterPageController registerController = new RegisterPageController(primaryStage);
            registerController.show();
        });
    }

    public void show() {
        primaryStage.setScene(view.getScene());
        primaryStage.show();
    }
}
