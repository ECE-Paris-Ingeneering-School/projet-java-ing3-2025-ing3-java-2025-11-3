package controller;

import Dao.UserDao;
import Dao.UserDaoImpl;
import MODELE.User;
import db.AzureDBConnector;
import javafx.stage.Stage;
import view.RegisterPageView;
import view.ReservationView;
import view.SearchPageView;

public class RegisterPageController {

    private Stage primaryStage;
    private RegisterPageView view;

    public RegisterPageController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new RegisterPageView();
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        // Action sur le bouton "Créer un compte"
        view.getCreateAccountButton().setOnAction(e -> {
            System.out.println("Tentative de création de compte...");
            //Récupération des champs remplis
            String mail = view.getEmailField().getText();
            String mdp = view.getPasswordField().getText();
            String mdpConfirm = view.getConfirmPasswordField().getText();

            //Vérification du mdp
            if(!mdp.equals(mdpConfirm)){
                // TODO: afficher texte rouge -> veuillez entrer same mdp
                System.out.println("Veuillez-entrer le même mdp");
            }
            else{
                User newUser=new User("thouvenin","come",mail,mdp) ;//<- a completer quand ajout attribut manquant fait
                UserDaoImpl userDao = new UserDaoImpl(new AzureDBConnector());
                userDao.ajouterUser(newUser);
                //Stocke l'user connecté
                UserSession.getInstance().setConnectedUser(newUser);
                new HomePageController(primaryStage).show();
            }
        });

        // Lien "Se connecter" dans le formulaire
        view.getLoginLink().setOnAction(e -> {
            new LoginPageController(primaryStage).show();
        });

        // Navigation via la NavBar
        view.getNavBarView().getTitleLabel().setOnMouseClicked(e -> {
            new HomePageController(primaryStage).show();
        });

        view.getNavBarView().getSignInLabel().setOnMouseClicked(e -> {
            new LoginPageController(primaryStage).show();
        });

        view.getNavBarView().getRegisterLabel().setOnMouseClicked(e -> {
            this.show(); // On est déjà sur cette page
        });

        view.getNavBarView().getRechercheLabel().setOnMouseClicked(e -> {
            new SearchPageController(primaryStage).show();
        });


        view.getNavBarView().getReservationsLabel().setOnMouseClicked(e-> {
            new ReservationController(primaryStage).show();
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
