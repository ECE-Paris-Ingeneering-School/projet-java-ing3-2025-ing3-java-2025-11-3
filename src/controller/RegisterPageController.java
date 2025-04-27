package controller;

import dao.UserDao;
import dao.UserDaoImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import modele.User;
import db.AzureDBConnector;
import javafx.stage.Stage;
import view.RegisterPageView;

import java.util.regex.Pattern;

/**
 * Contrôleur de la page d'inscription.
 * Gère la création d'un compte utilisateur, la validation des champs saisis,
 * ainsi que la navigation vers la page de connexion ou la page d'accueil après inscription réussie.
 */
public class RegisterPageController {

    private Stage primaryStage;
    private RegisterPageView view;

    /**
     * Constructeur du RegisterPageController.
     * Initialise la vue d'inscription et configure les gestionnaires d'événements.
     *
     * @param primaryStage La fenêtre principale de l'application.
     */
    public RegisterPageController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new RegisterPageView();
        attachEventHandlers();
    }

    /**
     * Attache les événements utilisateurs aux éléments interactifs de la vue :
     * - Création du compte utilisateur après validation.
     * - Redirection vers la page de connexion.
     */
    private void attachEventHandlers() {
        new NavBarController(primaryStage, view.getNavBarView());

        Button createButton = view.getCreateAccountButton();

        createButton.setOnAction(e -> {
            String firstName     = view.getFirstNameField().getText();
            String lastName      = view.getLastNameField().getText();
            String email         = view.getEmailField().getText();
            String password      = view.getPasswordField().getText();
            String passwordConfirm = view.getConfirmPasswordField().getText();

            if (firstName == null || firstName.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Champ requis", "Veuillez saisir votre prénom.");
                return;
            }
            if (lastName == null || lastName.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Champ requis", "Veuillez saisir votre nom.");
                return;
            }
            if (email == null || email.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Champ requis", "Veuillez saisir votre adresse email.");
                return;
            }
            if (!isValidEmail(email)) {
                showAlert(Alert.AlertType.WARNING, "Format invalide", "Veuillez saisir une adresse email valide.");
                return;
            }
            if (password == null || password.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Champ requis", "Veuillez saisir un mot de passe.");
                return;
            }
            if (passwordConfirm == null || passwordConfirm.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Champ requis", "Veuillez confirmer votre mot de passe.");
                return;
            }
            if (!password.equals(passwordConfirm)) {
                showAlert(Alert.AlertType.ERROR, "Mot de passe", "Les mots de passe ne correspondent pas.");
                return;
            }
            // (Optionnel) Vérifier la robustesse du mot de passe
            if (password.length() < 8) {
                showAlert(Alert.AlertType.WARNING, "Mot de passe", "Le mot de passe doit contenir au moins 8 caractères.");
                return;
            }

            createButton.setDisable(true);

            try {
                User newUser = new User(firstName, lastName, email, password);
                UserDao userDao = new UserDaoImpl(new AzureDBConnector());
                userDao.ajouterUser(newUser);

                UserSession.getInstance().setConnectedUser(newUser);
                new HomePageController(primaryStage).show();
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Erreur lors de l'inscription",
                        "Impossible de créer votre compte pour le moment. Veuillez réessayer plus tard.");
            } finally {
                createButton.setDisable(false);
            }
        });

        // Lien "Se connecter"
        view.getLoginLink().setOnAction(e -> {
            new LoginPageController(primaryStage).show();
        });

    }

    /**
     * Vérifie si une adresse email est au format valide à l'aide d'une expression régulière.
     *
     * @param email L'adresse email à valider.
     * @return true si l'email est valide, false sinon.
     */
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(regex, email);
    }

    /**
     * Affiche une boîte d'alerte avec un titre et un contenu spécifiés.
     *
     * @param type Le type d'alerte (INFORMATION, WARNING, ERROR, etc.).
     * @param title Le titre de la boîte d'alerte.
     * @param content Le message de contenu de la boîte d'alerte.
     */
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Affiche la page d'inscription.
     * Préserve les dimensions et l'état plein écran de la fenêtre principale.
     */
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
