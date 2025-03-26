package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Barre de navigation commune.
 * Aucune logique (événements) ici, seulement l'UI.
 */
public class NavBarView {

    public static HBox createNavBar() {
        HBox navBar = new HBox();
        navBar.setSpacing(30);
        navBar.setPadding(new Insets(15));
        navBar.setAlignment(Pos.CENTER_LEFT);
        navBar.setStyle("-fx-background-color: #FFFFFF;");

        // Ombre portée
        DropShadow shadow = new DropShadow();
        shadow.setRadius(5.0);
        shadow.setOffsetX(0);
        shadow.setOffsetY(2.0);
        shadow.setColor(Color.color(0, 0, 0, 0.3));
        navBar.setEffect(shadow);

        // Titre
        Label titleLabel = new Label("Booking");
        titleLabel.setFont(new Font("Arial", 28));
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #000;");

        // Menu de droite
        HBox navOptions = new HBox(20);
        navOptions.setAlignment(Pos.CENTER_RIGHT);

        Label rechercheLabel = new Label("Recherche");
        Label reservationsLabel = new Label("Réservations");
        Label contactLabel = new Label("Contact");
        Label signInLabel = new Label("Sign in");
        Label registerLabel = new Label("Register");
        navOptions.getChildren().addAll(rechercheLabel, reservationsLabel, contactLabel, signInLabel, registerLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        navBar.getChildren().addAll(titleLabel, spacer, navOptions);

        return navBar;
    }
}
