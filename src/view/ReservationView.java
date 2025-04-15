package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ReservationView extends BorderPane {

    private NavBarView navBarView;

    public ReservationView() {
        createView();
    }

    private void createView() {
        // Ajoute la NavBar en haut
        navBarView = new NavBarView();
        this.setTop(navBarView.getNavBar());

        // Centre un label avec le texte "Mes réservations"
        Label label = new Label("Mes réservations");
        label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333;");
        BorderPane.setAlignment(label, Pos.CENTER);
        this.setCenter(label);

        // Ajoute un padding global si nécessaire
        this.setPadding(new Insets(20));
    }

    /**
     * Retourne une Scene contenant cette vue.
     * Utilisez createScene() pour éviter de surcharger Node.getScene() qui est final.
     */
    public Scene createScene() {
        return new Scene(this, 1200, 800);
    }

    public NavBarView getNavBarView() {
        return navBarView;
    }
}
