package controller;

import javafx.stage.Stage;
import modele.Admin;
import modele.User;
import view.NavBarView;

/**
 * Contrôleur pour la barre de navigation.
 * Gère les interactions de la barre de navigation selon l'état de connexion de l'utilisateur
 * et adapte les options visibles en fonction de son rôle (utilisateur ou administrateur).
 */
public class NavBarController {
    private final Stage stage;

    /**
     * Constructeur du NavBarController.
     * Initialise les événements de clic sur les éléments de la barre de navigation,
     * ajuste l'affichage selon l'utilisateur connecté (simple utilisateur ou administrateur).
     *
     * @param stage La fenêtre principale de l'application.
     * @param navBarView La vue associée à la barre de navigation.
     */
    public NavBarController(Stage stage, NavBarView navBarView) {
        this.stage = stage;

        navBarView.getTitleLabel().setOnMouseClicked(e ->
                new HomePageController(stage).show());

        navBarView.getRechercheLabel().setOnMouseClicked(e ->
                new SearchPageController(stage).show());

        navBarView.getContactLabel().setOnMouseClicked(e ->
                new ContactController(stage).show());

        navBarView.getReservationsLabel().setOnMouseClicked(e ->
                new ReservationController(stage).show());

        navBarView.getAdminLabel().setOnMouseClicked(e ->
                new AdminController(stage).show());

        User currentUser = UserSession.getInstance().getConnectedUser();

        if (currentUser != null) {
            navBarView.getSignInLabel().setText("Déconnexion");
            navBarView.getSignInLabel().setOnMouseClicked(e -> {
                UserSession.getInstance().clearSession();
                new HomePageController(stage).show();
            });

            System.out.println(currentUser instanceof Admin);

            if (currentUser instanceof Admin) {
                navBarView.getReservationsLabel().setVisible(false);
                navBarView.getReservationsLabel().setManaged(false);

                navBarView.getAdminLabel().setVisible(true);
                navBarView.getAdminLabel().setManaged(true);
            } else {
                navBarView.getReservationsLabel().setVisible(true);
                navBarView.getReservationsLabel().setManaged(true);

                navBarView.getAdminLabel().setVisible(false);
                navBarView.getAdminLabel().setManaged(false);
            }

        } else {
            navBarView.getSignInLabel().setText("Connexion");
            navBarView.getSignInLabel().setOnMouseClicked(e ->
                    new LoginPageController(stage).show());

            navBarView.getReservationsLabel().setVisible(false);
            navBarView.getReservationsLabel().setManaged(false);

            navBarView.getAdminLabel().setVisible(false);
            navBarView.getAdminLabel().setManaged(false);
        }
    }
}
