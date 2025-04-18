package controller;

import Dao.ReservationDaoImpl;
import db.AzureDBConnector;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import Dao.ReservationDao;
import MODELE.Reservation;
import view.ReservationView;

import controller.UserSession;
import MODELE.User;

import java.io.Console;
import java.io.Serial;
import java.util.List;
import java.util.stream.Collectors;


import java.util.Optional;

/**
 * Contrôleur pour la page de gestion des réservations.
 * La barre de navigation permet de naviguer entre les différentes pages de l'application.
 */
public class ReservationController {

    private Stage primaryStage;
    private ReservationView view;
    private ReservationDao reservationDao;


    public ReservationController(Stage primaryStage){
        this.primaryStage= primaryStage;
        this.view = new ReservationView();
        this.reservationDao = new ReservationDaoImpl(new AzureDBConnector());
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        // Navigation vers la page de connexion
        view.getNavBarView().getSignInLabel().setOnMouseClicked(e -> {
            new LoginPageController(primaryStage).show();
        });

        // Navigation vers la page d'inscription
        view.getNavBarView().getRegisterLabel().setOnMouseClicked(e -> {
            new RegisterPageController(primaryStage).show();
        });

        // Navigation vers la page de recherche
        view.getNavBarView().getRechercheLabel().setOnMouseClicked(e -> {
            new SearchPageController(primaryStage).show();
        });

        // Navigation vers la page de réservations
        view.getNavBarView().getReservationsLabel().setOnMouseClicked(e -> {
            this.show();
        });

        // Retour à la page d'accueil
        view.getNavBarView().getTitleLabel().setOnMouseClicked(e -> {
            new HomePageController(primaryStage).show();
        });
    }

    public void show() {
        if(UserSession.getInstance().getConnectedUser() == null ) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Accès refusé");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez-vous connecter ou créer un compte pour accéder à vos réservations");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new LoginPageController(primaryStage).show();
                return;
            } else if (result.isPresent() && result.get() == ButtonType.CANCEL) {
                return;
            }
        }

        User currentUser = UserSession.getInstance().getConnectedUser();

        List<Reservation> dbReservations = reservationDao.getAllReservationByClientId(currentUser.getId());

        List<view.ReservationView.Reservation> viewReservations = dbReservations.stream().map(res ->
                new view.ReservationView.Reservation(
                        res.getHebergement().getNom(),
                        res.getDateDebut(),
                        res.getDateFin(),
                        res.getPrix() + "€",
                        res.getHebergement().getImage() != null ? String.valueOf(res.getHebergement().getImage()) : "", // image si dispo
                        res.getHebergement().getAdresse(),
                        res.getHebergement().getPrix() + "€"
                )
        ).collect(Collectors.toList());

        view.setReservations(viewReservations);

        // 👉 Tu dois ajouter cette partie :
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
