// src/main/java/view/NavBarView.java
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
    private Label titleLabel;
    private Label rechercheLabel;
    private Label reservationsLabel;
    private Label contactLabel;
    private Label signInLabel;

    public NavBarView() {
        createNavBar();
    }

    private void createNavBar() {
        navBar = new HBox(30);
        navBar.setPadding(new Insets(15));
        navBar.setAlignment(Pos.CENTER_LEFT);
        navBar.setStyle("-fx-background-color: #FFFFFF;");
        navBar.setEffect(new DropShadow(5, 0, 2, Color.color(0, 0, 0, 0.3)));

        // -- Titre
        titleLabel = new Label("Book");
        titleLabel.setFont(new Font("Arial", 28));
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #000;");

        // -- Spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // -- Les items de nav
        rechercheLabel    = new Label("Recherche");
        reservationsLabel = new Label("Réservations");
        contactLabel      = new Label("Contact");
        signInLabel       = new Label("Sign in");

        HBox navOptions = new HBox(20,
                rechercheLabel,
                reservationsLabel,
                contactLabel,
                signInLabel
        );
        navOptions.setAlignment(Pos.CENTER_RIGHT);

        navBar.getChildren().addAll(titleLabel, spacer, navOptions);
    }

    public HBox getNavBar()             { return navBar; }
    public Label getTitleLabel()        { return titleLabel; }
    public Label getRechercheLabel()    { return rechercheLabel; }
    public Label getReservationsLabel() { return reservationsLabel; }
    public Label getContactLabel()      { return contactLabel; }
    public Label getSignInLabel()       { return signInLabel; }
}
