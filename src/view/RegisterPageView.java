package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class RegisterPageView {

    private Scene scene;
    private TextField emailField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private Button createAccountButton;
    private Hyperlink loginLink;
    private NavBarView navBarView;

    public RegisterPageView() {
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
        Label registerTitle = new Label("Créer un nouveau compte");
        registerTitle.setFont(new Font("Arial", 24));
        registerTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: #333333;");

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

        Label confirmPasswordLabel = new Label("Confirmer le mot de passe");
        confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirmez votre mot de passe");
        confirmPasswordField.setMaxWidth(Double.MAX_VALUE);
        confirmPasswordField.setStyle("-fx-focus-color: black; -fx-faint-focus-color: transparent;");

        // Espace vertical entre les champs et le bouton (10 px)
        Region spacerBetween = new Region();
        spacerBetween.setMinHeight(10);

        createAccountButton = new Button("Créer un compte");
        createAccountButton.setStyle(
                "-fx-background-color: #000000;" +
                        "-fx-text-fill: #ffffff;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 8 16;"
        );
        createAccountButton.setMaxWidth(Double.MAX_VALUE);

        fieldsBox.getChildren().addAll(emailLabel, emailField, passwordLabel, passwordField, confirmPasswordLabel, confirmPasswordField, spacerBetween, createAccountButton);

        // Ligne pour les liens de bas : "Déjà un compte ?" à gauche et "Se connecter" à droite
        HBox bottomLine = new HBox();
        bottomLine.setAlignment(Pos.CENTER_LEFT);
        bottomLine.setPadding(new Insets(5, 0, 0, 0));
        Label alreadyAccountLabel = new Label(" Déjà un compte ?");
        alreadyAccountLabel.setStyle("-fx-text-fill: #555555;");
        loginLink = new Hyperlink("Se connecter");
        loginLink.setStyle("-fx-text-fill: #555555;");
        loginLink.setOnMouseEntered(e -> loginLink.setStyle("-fx-text-fill: #555555;"));
        loginLink.setOnMouseExited(e -> loginLink.setStyle("-fx-text-fill: #555555;"));
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        bottomLine.getChildren().addAll(alreadyAccountLabel, spacer, loginLink);

        // Assemblage final du cartouche
        formContainer.getChildren().addAll(registerTitle, fieldsBox, bottomLine);
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

    public PasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public Button getCreateAccountButton() {
        return createAccountButton;
    }

    public Hyperlink getLoginLink() {
        return loginLink;
    }

    public NavBarView getNavBarView() {
        return navBarView;
    }
}
