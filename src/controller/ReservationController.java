package controller;

import Dao.ReservationDaoImpl;
import db.AzureDBConnector;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import Dao.ReservationDao;
import MODELE.Reservation;
import view.ReservationView;

import controller.UserSession;
import MODELE.User;

import java.awt.*;
import java.io.Console;
import java.io.Serial;
import java.util.ArrayList;
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


    public ReservationController(Stage primaryStage) {
        this.primaryStage = primaryStage;
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
        if (UserSession.getInstance().getConnectedUser() == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Accès refusé");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez-vous connecter ou créer un compte pour accéder à vos réservations");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new LoginPageController(primaryStage).show();
            }
            return;
        }

        User currentUser = UserSession.getInstance().getConnectedUser();

        // === UI: Affichage roue de chargement ===
        ProgressIndicator loadingSpinner = new ProgressIndicator();

        VBox loadingBox = new VBox(20, loadingSpinner);
        loadingBox.setAlignment(Pos.CENTER);

        BorderPane loadingRoot = new BorderPane();
        loadingRoot.setCenter(loadingBox);
        loadingRoot.setStyle("-fx-background-color: #F9F9F9;");

        Scene loadingScene = new Scene(loadingRoot, 900, 700);
        primaryStage.setScene(loadingScene);
        primaryStage.show();

        // === Thread/Task façon BookingPage ===
        new Thread(new Task<Void>() {
            private List<Reservation> dbReservations;
            private Map<Integer, Reservation> reservationMap;

            @Override
            protected Void call() {
                dbReservations = reservationDao.getAllReservationByClientId(currentUser.getId());
                reservationMap = dbReservations.stream()
                        .collect(Collectors.toMap(Reservation::getId, r -> r));
                return null;
            }

            @Override
            protected void succeeded() {
                List<ReservationView.Reservation> viewReservations = dbReservations.stream().map(res -> {
                    List<String> imageUrls = new ArrayList<>();
                    String image = String.valueOf(res.getHebergement().getImage());
                    if (image != null && !image.isEmpty()) {
                        imageUrls.add(image);
                    } else {
                        imageUrls.add("file:src/resources/larry.jpg");
                    }

                    return new ReservationView.Reservation(
                            res.getId(),
                            res.getHebergement().getNom(),
                            res.getDateDebut(),
                            res.getDateFin(),
                            res.getPrix() + "€",
                            imageUrls,
                            res.getHebergement().getAdresse(),
                            res.getHebergement().getPrix() + "€"
                    );
                }).collect(Collectors.toList());


                view.setReservations(viewReservations, new ReservationView.ReservationActionHandler() {
                    @Override
                    public void onView(ReservationView.Reservation reservation) {
                        Reservation full = reservationMap.get(reservation.getReservationId());
                        if (full != null) {
                            new BookingPageController(primaryStage, full.getHebergement()).show();
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
                                show(); // recharger la page
                            }
                        }
                    }
                });

                // Réaffichage normal
                boolean fullScreen = primaryStage.isFullScreen();
                double width = primaryStage.getWidth();
                double height = primaryStage.getHeight();
                primaryStage.setScene(view.getScene());
                primaryStage.setWidth(width);
                primaryStage.setHeight(height);
                primaryStage.setFullScreen(fullScreen);
                primaryStage.show();
            }

            @Override
            protected void failed() {
                Throwable ex = getException();
                Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Erreur lors du chargement : " + ex.getMessage());
                errorAlert.showAndWait();
            }
        }).start();
    }


}
