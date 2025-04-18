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
import java.util.Map;
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

        // 💡 Ajoute cette ligne ici
        Map<Integer, Reservation> reservationMap = dbReservations.stream()
                .collect(Collectors.toMap(Reservation::getId, r -> r));

        List<ReservationView.Reservation> viewReservations = dbReservations.stream().map(res ->
                new ReservationView.Reservation(
                        res.getId(),
                        res.getHebergement().getNom(),
                        res.getDateDebut(),
                        res.getDateFin(),
                        res.getPrix() + "€",
                        res.getHebergement().getImage(),
                        res.getHebergement().getAdresse(),
                        res.getHebergement().getPrix() + "€"
                )
        ).collect(Collectors.toList());

        view.setReservations(viewReservations, new ReservationView.ReservationActionHandler() {
            @Override
            public void onView(ReservationView.Reservation reservation) {
                Reservation full = reservationMap.get(reservation.getReservationId());
                if (full != null) {
                    new BookingPageController(primaryStage, full.getHebergement());
                }
            }

            @Override
            public void onCancel(ReservationView.Reservation reservation) {
                Reservation full = reservationMap.get(reservation.getReservationId());
                if (full != null) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation d'annulation");
                    alert.setHeaderText(null);
                    alert.setContentText("Voulez-vous annuler la réservation de : " + full.getHebergement().getNom() + " ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        reservationDao.annulerReservation(full.getId());
                        show(); // rafraîchir l'affichage après suppression
                    }
                }
            }
        });

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
