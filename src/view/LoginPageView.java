package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class LoginPageView {

    private Scene scene;
    private TextField emailField;
    private PasswordField passwordField;
    private Button loginButton;
    private Hyperlink registerLink;
    private NavBarView navBarView;

    public LoginPageView() {
        createUI();
    }

    private void createUI() {
        // Barre de navigation
        navBarView = new NavBarView();
        HBox navBar = navBarView.getNavBar();

        // Conteneur principal pour centrer le cartouche
        VBox mainContainer = new VBox();
        mainContainer.setAlignment(Pos.CENTER);

        // Cartouche de formulaire avec padding uniforme
        VBox formContainer = new VBox(20);
        formContainer.setAlignment(Pos.TOP_LEFT);
        formContainer.setPadding(new Insets(30));
        formContainer.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: #cccccc;" +
                        "-fx-border-radius: 10;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);" +
                        "-fx-max-width: 400;"
        );

        // Titre
        Label loginTitle = new Label("Connexion à votre compte");
        loginTitle.setFont(new Font("Arial", 24));
        loginTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: #333333;");

        // Champs et bouton
        VBox fieldsBox = new VBox(10);
        fieldsBox.setFillWidth(true);

        Label emailLabel = new Label("Email");
        emailField = new TextField();
        emailField.setPromptText("Votre email");
        emailField.setMaxWidth(Double.MAX_VALUE);
        emailField.setStyle("-fx-focus-color: black; -fx-faint-focus-color: transparent;");

        Label passwordLabel = new Label("Mot de passe");
        passwordField = new PasswordField();
        passwordField.setPromptText("Votre mot de passe");
        passwordField.setMaxWidth(Double.MAX_VALUE);
        passwordField.setStyle("-fx-focus-color: black; -fx-faint-focus-color: transparent;");

        // Espace vertical réduit entre le champ de mot de passe et le bouton (10 px)
        Region spacerBetween = new Region();
        spacerBetween.setMinHeight(10);

        loginButton = new Button("Connexion");
        loginButton.setStyle(
                "-fx-background-color: #000000;" +
                        "-fx-text-fill: #ffffff;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 8 16;"
        );
        loginButton.setMaxWidth(Double.MAX_VALUE);

        fieldsBox.getChildren().addAll(emailLabel, emailField, passwordLabel, passwordField, spacerBetween, loginButton);

        // Ligne 1 : "Mot de passe oublié ?" aligné à gauche
        HBox forgotPasswordBox = new HBox();
        forgotPasswordBox.setAlignment(Pos.CENTER_LEFT);
        Hyperlink forgotPasswordLink = new Hyperlink("Mot de passe oublié ?");
        forgotPasswordLink.setStyle("-fx-text-fill: #555555;");
        // Forcer le hover à rester noir
        forgotPasswordLink.setOnMouseEntered(e -> forgotPasswordLink.setStyle("-fx-text-fill: #555555;"));
        forgotPasswordLink.setOnMouseExited(e -> forgotPasswordLink.setStyle("-fx-text-fill: #555555;"));
        forgotPasswordBox.getChildren().add(forgotPasswordLink);

        // Ligne 2 : "Pas de compte ?" en haut à gauche, et "S'inscrire" sur une ligne en dessous, aligné à droite
        HBox registerBox = new HBox();
        registerBox.setAlignment(Pos.CENTER_LEFT);
        registerBox.setPadding(new Insets(5, 0, 0, 0));
        Label noAccountLabel = new Label(" Pas de compte ?");
        noAccountLabel.setStyle("-fx-text-fill: #555555;");
        registerLink = new Hyperlink("S'inscrire");
        registerLink.setStyle("-fx-text-fill: #555555;");
        registerLink.setOnMouseEntered(e -> registerLink.setStyle("-fx-text-fill: #555555;"));
        registerLink.setOnMouseExited(e -> registerLink.setStyle("-fx-text-fill: #555555;"));
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        registerBox.getChildren().addAll(noAccountLabel, spacer, registerLink);

        // Assemblage final du cartouche
        formContainer.getChildren().addAll(loginTitle, fieldsBox, forgotPasswordBox, registerBox);
        mainContainer.getChildren().add(formContainer);

        // Layout principal
        BorderPane root = new BorderPane();
        root.setTop(navBar);
        root.setCenter(mainContainer);
        root.setStyle("-fx-background-color: #F9F9F9;");

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

    public NavBarView getNavBarView() {
        return navBarView;
    }
}
