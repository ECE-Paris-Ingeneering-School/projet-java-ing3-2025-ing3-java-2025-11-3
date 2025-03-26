package controller;

import view.RegisterPageView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

/**
 * Contrôleur de la page d'inscription.
 */
public class RegisterPageController {

    private Stage primaryStage;
    private RegisterPageView view;

    public RegisterPageController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new RegisterPageView();
        attachEvents();
    }

    private void attachEvents() {
        // Bouton "Créer un compte"
        view.getCreateAccountButton().setOnAction((ActionEvent e) -> {
            System.out.println("Tentative d'inscription...");
            // TODO: logique d'inscription
        });

        // Lien "Se connecter"
        view.getLoginLink().setOnAction((ActionEvent e) -> {
            LoginPageController loginController = new LoginPageController(primaryStage);
            loginController.show();
        });
    }

    public void show() {
        primaryStage.setScene(view.getScene());
        primaryStage.show();
    }
}
