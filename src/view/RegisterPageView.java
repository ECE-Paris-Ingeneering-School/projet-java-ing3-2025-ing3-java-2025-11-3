package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Vue pour la page d'inscription.
 */
public class RegisterPageView {

    private Scene scene;
    private TextField emailField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private Button createAccountButton;
    private Hyperlink loginLink;

    public RegisterPageView() {
        createUI();
    }

    private void createUI() {
        HBox navBar = NavBarView.createNavBar();

        VBox registerContainer = new VBox(15);
        registerContainer.setAlignment(Pos.CENTER);
        registerContainer.setPadding(new Insets(20));

        Label registerTitle = new Label("Créer un nouveau compte");
        registerTitle.setFont(new Font("Arial", 24));
        registerTitle.setStyle("-fx-font-weight: bold;");

        Label emailLabel = new Label("Email");
        emailField = new TextField();
        emailField.setPromptText("Votre email");

        Label passwordLabel = new Label("Mot de passe");
        passwordField = new PasswordField();
        passwordField.setPromptText("Votre mot de passe");

        Label confirmPasswordLabel = new Label("Confirmer le mot de passe");
        confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirmez votre mot de passe");

        createAccountButton = new Button("Créer un compte");
        createAccountButton.setStyle("-fx-background-color: #000000; -fx-text-fill: #ffffff; -fx-padding: 8 16;");

        Label alreadyAccountLabel = new Label("Déjà un compte ?");
        loginLink = new Hyperlink("Se connecter");

        VBox formBox = new VBox(10);
        formBox.setAlignment(Pos.CENTER_LEFT);
        formBox.getChildren().addAll(
                emailLabel, emailField,
                passwordLabel, passwordField,
                confirmPasswordLabel, confirmPasswordField,
                createAccountButton
        );

        HBox loginBox = new HBox(5);
        loginBox.setAlignment(Pos.CENTER);
        loginBox.getChildren().addAll(alreadyAccountLabel, loginLink);

        registerContainer.getChildren().addAll(registerTitle, formBox, loginBox);

        BorderPane root = new BorderPane();
        root.setTop(navBar);
        root.setCenter(registerContainer);

        scene = new Scene(root, 800, 500);
    }

    public Scene getScene() {
        return scene;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public PasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public Button getCreateAccountButton() {
        return createAccountButton;
    }

    public Hyperlink getLoginLink() {
        return loginLink;
    }
}
