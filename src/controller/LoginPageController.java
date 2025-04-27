package controller;

import dao.UserDaoImpl;
import db.AzureDBConnector;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.LoginPageView;

import java.util.regex.Pattern;

/**
 * Contrôleur de la page de connexion.
 * Gère les interactions entre la vue (LoginPageView) et le modèle (UserDaoImpl)
 * pour l'authentification de l'utilisateur.
 */
public class LoginPageController {

    /** Fenêtre principale de l'application. */
    private Stage primaryStage;
    /** Vue associée à la page de connexion. */
    private LoginPageView view;
    /** DAO pour l'accès aux utilisateurs et à la base de données. */
    private UserDaoImpl userDao;

    /**
     * Initialise le contrôleur avec la fenêtre principale.
     *
     * @param primaryStage la fenêtre principale où la scène sera affichée
     */
    public LoginPageController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new LoginPageView();
        this.userDao = new UserDaoImpl(new AzureDBConnector());

        new NavBarController(primaryStage, view.getNavBarView());

        attachEventHandlers();
    }

    /**
     * Attache les gestionnaires d'événements aux différents éléments de la vue.
     */
    private void attachEventHandlers() {
        Button loginButton = view.getLoginButton();

        loginButton.setOnAction(e -> {
            String email = view.getEmailField().getText();
            String password = view.getPasswordField().getText();

            // Validation des champs
            if (email == null || email.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Champ requis", "Veuillez saisir votre adresse email.");
                return;
            }
            if (!isValidEmail(email)) {
                showAlert(Alert.AlertType.WARNING, "Format invalide", "Veuillez saisir une adresse email valide.");
                return;
            }
            if (password == null || password.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Champ requis", "Veuillez saisir votre mot de passe.");
                return;
            }

            // Suppression de toute liaison précédente sur le bouton pour le rendre inactif
            loginButton.disableProperty().unbind();

            // Création de la tâche de connexion
            Task<Boolean> loginTask = new Task<>() {
                @Override
                protected Boolean call() throws Exception {
                    return userDao.connexionUser(email, password);
                }
            };

            // Liaison de la propriété de désactivation du bouton à l'état de la tâche (si en cours alors désactiver)
            loginButton.disableProperty().bind(loginTask.runningProperty());

            loginTask.setOnSucceeded(ev -> {
                boolean success = loginTask.getValue();
                if (success) {
                    UserSession.getInstance().setConnectedUser(
                            userDao.getUserByEmail(email)
                    );
                    new HomePageController(primaryStage).show();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Échec de la connexion",
                            "Email ou mot de passe incorrect.");
                }
            });

            loginTask.setOnFailed(ev -> {
                showAlert(Alert.AlertType.ERROR, "Erreur serveur",
                        "Une erreur est survenue lors de la connexion, réessayez plus tard.");
            });

            // Démarrage de la tâche dans un thread séparé
            new Thread(loginTask).start();
        });

        // Lien vers la page d'inscription
        view.getRegisterLink().setOnAction(e -> {
            new RegisterPageController(primaryStage).show();
        });
    }

    /**
     * Vérifie le format de l'adresse e-mail via une expression régulière.
     *
     * @param email la chaîne à tester
     * @return true si le format est valide, false sinon
     */
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(regex, email);
    }

    /**
     * Affiche une boîte de dialogue d'alerte.
     *
     * @param type    le type d'alerte (INFORMATION, WARNING, ERROR, etc.)
     * @param title   le titre de la fenêtre d'alerte
     * @param content le message à afficher
     */
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Affiche cette vue dans la fenêtre principale,
     * en conservant la taille et l'état plein écran.
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
