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
    private TextField firstNameField;
    private TextField lastNameField;

    public RegisterPageView() {
        createUI();
    }

    private void createUI() {
        // Barre de navigation
        navBarView = new NavBarView();
        HBox navBar = navBarView.getNavBar();

        // Conteneur principal
        VBox mainContainer = new VBox();
        mainContainer.setAlignment(Pos.CENTER);

        // Cartouche de formulaire
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

        // Boîte des champs
        VBox fieldsBox = new VBox(10);
        fieldsBox.setFillWidth(true);

        // — Prénom
        Label firstNameLabel = new Label("Prénom");
        firstNameField = new TextField();
        firstNameField.setPromptText("Votre prénom");
        firstNameField.setMaxWidth(Double.MAX_VALUE);
        firstNameField.setStyle("-fx-focus-color: black; -fx-faint-focus-color: transparent;");

        // — Nom
        Label lastNameLabel = new Label("Nom");
        lastNameField = new TextField();
        lastNameField.setPromptText("Votre nom");
        lastNameField.setMaxWidth(Double.MAX_VALUE);
        lastNameField.setStyle("-fx-focus-color: black; -fx-faint-focus-color: transparent;");

        // — Email
        Label emailLabel = new Label("Email");
        emailField = new TextField();
        emailField.setPromptText("Votre email");
        emailField.setMaxWidth(Double.MAX_VALUE);
        emailField.setStyle("-fx-focus-color: black; -fx-faint-focus-color: transparent;");

        // — Mot de passe
        Label passwordLabel = new Label("Mot de passe");
        passwordField = new PasswordField();
        passwordField.setPromptText("Votre mot de passe");
        passwordField.setMaxWidth(Double.MAX_VALUE);
        passwordField.setStyle("-fx-focus-color: black; -fx-faint-focus-color: transparent;");

        // — Confirmation du mot de passe
        Label confirmPasswordLabel = new Label("Confirmer le mot de passe");
        confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirmez votre mot de passe");
        confirmPasswordField.setMaxWidth(Double.MAX_VALUE);
        confirmPasswordField.setStyle("-fx-focus-color: black; -fx-faint-focus-color: transparent;");

        // Espace avant le bouton
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

        // Ajout de tous les champs dans l'ordre
        fieldsBox.getChildren().addAll(
                firstNameLabel, firstNameField,
                lastNameLabel, lastNameField,
                emailLabel, emailField,
                passwordLabel, passwordField,
                confirmPasswordLabel, confirmPasswordField,
                spacerBetween,
                createAccountButton
        );

        // Ligne du bas avec lien “Se connecter”
        HBox bottomLine = new HBox();
        bottomLine.setAlignment(Pos.CENTER_LEFT);
        bottomLine.setPadding(new Insets(5, 0, 0, 0));
        Label alreadyAccountLabel = new Label("Déjà un compte ?");
        alreadyAccountLabel.setStyle("-fx-text-fill: #555555;");
        loginLink = new Hyperlink("Se connecter");
        loginLink.setStyle("-fx-text-fill: #555555;");
        HBox.setHgrow(new Region(), Priority.ALWAYS);
        bottomLine.getChildren().addAll(alreadyAccountLabel, new Region(), loginLink);

        // Assemblage final
        formContainer.getChildren().addAll(registerTitle, fieldsBox, bottomLine);
        mainContainer.getChildren().add(formContainer);

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

    public TextField getFirstNameField() {return firstNameField;}

    public TextField getLastNameField() {return lastNameField;}
}
