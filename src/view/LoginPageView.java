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
 * Vue pour la page de connexion.
 */
public class LoginPageView {

    private Scene scene;
    private TextField emailField;
    private PasswordField passwordField;
    private Button loginButton;
    private Hyperlink registerLink;

    public LoginPageView() {
        createUI();
    }

    private void createUI() {
        HBox navBar = NavBarView.createNavBar();

        VBox loginContainer = new VBox(15);
        loginContainer.setAlignment(Pos.CENTER);
        loginContainer.setPadding(new Insets(20));

        Label loginTitle = new Label("Connexion à votre compte");
        loginTitle.setFont(new Font("Arial", 24));
        loginTitle.setStyle("-fx-font-weight: bold;");

        Label emailLabel = new Label("Email");
        emailField = new TextField();
        emailField.setPromptText("Votre email");

        Label passwordLabel = new Label("Mot de passe");
        passwordField = new PasswordField();
        passwordField.setPromptText("Votre mot de passe");

        loginButton = new Button("Connexion");
        loginButton.setStyle("-fx-background-color: #000000; -fx-text-fill: #ffffff; -fx-padding: 8 16;");

        Hyperlink forgotPasswordLink = new Hyperlink("Mot de passe oublié ?");
        Label noAccountLabel = new Label("Pas de compte ?");
        registerLink = new Hyperlink("S'inscrire");

        VBox formBox = new VBox(10);
        formBox.setAlignment(Pos.CENTER_LEFT);
        formBox.getChildren().addAll(
                emailLabel, emailField,
                passwordLabel, passwordField,
                loginButton,
                forgotPasswordLink
        );

        HBox registerBox = new HBox(5);
        registerBox.setAlignment(Pos.CENTER);
        registerBox.getChildren().addAll(noAccountLabel, registerLink);

        loginContainer.getChildren().addAll(loginTitle, formBox, registerBox);

        BorderPane root = new BorderPane();
        root.setTop(navBar);
        root.setCenter(loginContainer);

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

    public Button getLoginButton() {
        return loginButton;
    }

    public Hyperlink getRegisterLink() {
        return registerLink;
    }
}
