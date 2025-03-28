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

public class NavBarView {

    private HBox navBar;

    // Titre
    private Label titleLabel;

    // Éléments de navigation
    private Label rechercheLabel;
    private Label reservationsLabel;
    private Label contactLabel;
    private Label signInLabel;
    private Label registerLabel;

    public NavBarView() {
        createNavBar();
    }

    private void createNavBar() {
        navBar = new HBox();
        navBar.setSpacing(30);
        navBar.setPadding(new Insets(15));
        navBar.setAlignment(Pos.CENTER_LEFT);
        navBar.setStyle("-fx-background-color: #FFFFFF;");

        // Effet d'ombre (DropShadow)
        DropShadow shadow = new DropShadow();
        shadow.setRadius(5.0);
        shadow.setOffsetX(0);
        shadow.setOffsetY(2.0);
        shadow.setColor(Color.color(0, 0, 0, 0.3));
        navBar.setEffect(shadow);

        // -- Titre à gauche
        titleLabel = new Label("Booking");
        titleLabel.setFont(new Font("Arial", 28));
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #000;");

        // -- Spacer pour pousser les éléments de droite
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // -- Options de navigation (labels)
        HBox navOptions = new HBox(20);
        navOptions.setAlignment(Pos.CENTER_RIGHT);

        rechercheLabel = new Label("Recherche");
        reservationsLabel = new Label("Réservations");
        contactLabel = new Label("Contact");
        signInLabel = new Label("Sign in");
        registerLabel = new Label("Register");

        // Ajout des labels dans le conteneur
        navOptions.getChildren().addAll(
                rechercheLabel, reservationsLabel, contactLabel,
                signInLabel, registerLabel
        );

        // -- Assemblage final
        navBar.getChildren().addAll(titleLabel, spacer, navOptions);
    }

    // -- GETTERS pour que les contrôleurs puissent écouter les événements
    public HBox getNavBar() {
        return navBar;
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public Label getRechercheLabel() {
        return rechercheLabel;
    }

    public Label getReservationsLabel() {
        return reservationsLabel;
    }

    public Label getContactLabel() {
        return contactLabel;
    }

    public Label getSignInLabel() {
        return signInLabel;
    }

    public Label getRegisterLabel() {
        return registerLabel;
    }
}
