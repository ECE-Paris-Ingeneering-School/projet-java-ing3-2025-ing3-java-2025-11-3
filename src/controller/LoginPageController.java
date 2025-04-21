package controller;

import dao.UserDaoImpl;
import db.AzureDBConnector;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.LoginPageView;

import javafx.util.Duration;

import java.util.regex.Pattern;

public class LoginPageController {

    private Stage primaryStage;
    private LoginPageView view;
    private UserDaoImpl userDao;

    public LoginPageController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new LoginPageView();
        this.userDao = new UserDaoImpl(new AzureDBConnector());
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        Button loginButton = view.getLoginButton();

        loginButton.setOnAction(e -> {
            String email = view.getEmailField().getText();
            String password = view.getPasswordField().getText();

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

            loginButton.setDisable(true);

            Task<Boolean> loginTask = new Task<>() {
                @Override
                protected Boolean call() throws Exception {
                    return userDao.connexionUser(email, password);
                }
            };

            loginButton.disableProperty().bind(loginTask.runningProperty());

            loginTask.setOnSucceeded(ev -> {
                boolean success = loginTask.getValue();
                if (success) {
                    UserSession.getInstance().setConnectedUser(userDao.getUserByEmail(email));
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

            new Thread(loginTask).start();
        });

        view.getRegisterLink().setOnAction(e -> {
            new RegisterPageController(primaryStage).show();
        });
        view.getNavBarView().getTitleLabel().setOnMouseClicked(e -> {
            new HomePageController(primaryStage).show();
        });
        view.getNavBarView().getSignInLabel().setOnMouseClicked(e -> {
            this.show();
        });
        view.getNavBarView().getRechercheLabel().setOnMouseClicked(e -> {
            new SearchPageController(primaryStage).show();
        });
        view.getNavBarView().getReservationsLabel().setOnMouseClicked(e -> {
            new ReservationController(primaryStage).show();
        });
    }

    /** Vérifie le format de l’email via une regex simple */
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(regex, email);
    }

    /** Affiche une boîte d’alerte */
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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
