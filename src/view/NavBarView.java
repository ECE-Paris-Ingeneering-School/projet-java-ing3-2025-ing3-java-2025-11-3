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
/**
 * La classe {@code NavBarView} représente une barre de navigation horizontale
 * utilisée dans l'application. Elle contient un titre ainsi que plusieurs options de navigation
 * telles que "Recherche", "Réservations", "Admin", "Contact" et "Sign in".
 */
public class NavBarView {

    private HBox navBar;
    private Label titleLabel;
    private Label rechercheLabel;
    private Label reservationsLabel;
    private Label contactLabel;
    private Label signInLabel;
    private Label adminLabel;
    /**
     * Construit une nouvelle instance de {@code NavBarView}.
     * Initialise et configure la barre de navigation ainsi que ses éléments.
     */
    public NavBarView() {
        createNavBar();
    }
    /**
     * Construit et organise les composants graphiques de la barre de navigation.
     * Cette méthode est appelée lors de l'initialisation de l'instance.
     */
    private void createNavBar() {
        navBar = new HBox(30);
        navBar.setPadding(new Insets(15));
        navBar.setAlignment(Pos.CENTER_LEFT);
        navBar.setStyle("-fx-background-color: #FFFFFF;");
        navBar.setEffect(new DropShadow(5, 0, 2, Color.color(0, 0, 0, 0.3)));

        // -- Titre
        titleLabel = new Label("Booking");
        titleLabel.setFont(new Font("Arial", 28));
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #000;");

        // -- Spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // -- Les items de nav
        rechercheLabel    = new Label("Recherche");
        reservationsLabel = new Label("Réservations");
        adminLabel        = new Label("Admin");
        contactLabel      = new Label("Contact");
        signInLabel       = new Label("Sign in");

        HBox navOptions = new HBox(20,
                rechercheLabel,
                reservationsLabel,
                adminLabel,
                contactLabel,
                signInLabel
        );
        navOptions.setAlignment(Pos.CENTER_RIGHT);

        navBar.getChildren().addAll(titleLabel, spacer, navOptions);
    }
    /**
     * Retourne la barre de navigation principale.
     *
     * @return le conteneur {@code HBox} représentant la barre de navigation
     */
    public HBox getNavBar()             { return navBar; }
    /**
     * Retourne le label du titre de la barre de navigation.
     *
     * @return le label {@code Label} représentant le titre
     */
    public Label getTitleLabel()        { return titleLabel; }
    /**
     * Retourne le label de la recherche.
     *
     * @return le label {@code Label} représentant la recherche
     */
    public Label getRechercheLabel()    { return rechercheLabel; }
    /**
     * Retourne le label des réservations.
     *
     * @return le label {@code Label} représentant les réservations
     */
    public Label getReservationsLabel() { return reservationsLabel; }
    /**
     * Retourne le label de contact.
     *
     * @return le label {@code Label} représentant le contact
     */
    public Label getContactLabel()      { return contactLabel; }
    /**
     * Retourne le label de connexion.
     *
     * @return le label {@code Label} représentant la connexion
     */
    public Label getSignInLabel()       { return signInLabel; }
    /**
     * Retourne le label d'administration.
     *
     * @return le label {@code Label} représentant l'administration
     */
    public Label getAdminLabel()        {return adminLabel;}

}

