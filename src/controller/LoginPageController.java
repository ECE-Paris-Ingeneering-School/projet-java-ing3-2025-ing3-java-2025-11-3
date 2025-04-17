package controller;

import Dao.UserDaoImpl;
import db.AzureDBConnector;
import javafx.stage.Stage;
import view.LoginPageView;
import view.ReservationView;
import view.SearchPageView;

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
        // Action sur le bouton "Connexion"
        view.getLoginButton().setOnAction(e -> {
            // verif existence user
            // puis forwartd sur page recherche
            String mail = view.getEmailField().getText();
            System.out.println("Tentative de connexion...");
            if(userDao.connexionUser(view.getEmailField().getText(),view.getPasswordField().getText())){
             System.out.println("Connexion etablie");
             new HomePageController(primaryStage).show();
            }
            else {System.out.println("Erreur connexion, utilisateur introuvable");}
        });

        // Lien "S'inscrire" dans le formulaire
        view.getRegisterLink().setOnAction(e -> {
            new RegisterPageController(primaryStage).show();
        });

        // Navigation via la NavBar
        view.getNavBarView().getTitleLabel().setOnMouseClicked(e -> {
            new HomePageController(primaryStage).show();
        });

        view.getNavBarView().getSignInLabel().setOnMouseClicked(e -> {
            this.show(); // On est déjà sur cette page
        });

        view.getNavBarView().getRegisterLabel().setOnMouseClicked(e -> {
            new RegisterPageController(primaryStage).show();
        });

        view.getNavBarView().getRechercheLabel().setOnMouseClicked(e -> {
            new SearchPageController(primaryStage).show();
        });

        view.getNavBarView().getReservationsLabel().setOnMouseClicked(e -> {
            ReservationView reservationView = new ReservationView();
            new ReservationController(primaryStage, reservationView);
            primaryStage.setScene(reservationView.getScene());
        });
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
