package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


/**
 * La classe {@code ContactView} représente la vue de la page de contact de l'application.
 * Elle organise une interface graphique simple avec une barre de navigation en haut
 * et des informations de contact affichées au centre de la fenêtre.
 */
public class ContactView {

    private final Scene scene;
    private final BorderPane root;
    private final NavBarView navBarView;

    /**
     * Construit une nouvelle instance de {@code ContactView}.
     * Initialise la structure principale de la vue avec une barre de navigation
     * et un contenu central contenant les informations de contact.
     */
    public ContactView() {
        // racine
        root = new BorderPane();

        // navbar en haut
        navBarView = new NavBarView();
        root.setTop(navBarView.getNavBar());

        // contenu central
        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Contact");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label text = new Label(
                "Pour toute question ou assistance,\n" +
                        "écrivez-nous à : support@booking.com\n" +
                        "ou appelez : +33 1 23 45 67 89"
        );
        text.setStyle("-fx-font-size: 14px;");
        text.setWrapText(true);
        text.setMaxWidth(400);
        text.setAlignment(Pos.CENTER);

        content.getChildren().addAll(title, text);
        root.setCenter(content);

        scene = new Scene(root, 900, 600);
    }
    /**
     * Retourne la scène principale de cette vue de contact.
     *
     * @return la scène contenant l'interface graphique de la page de contact
     */
    public Scene getScene() {
        return scene;
    }
    /**
     * Retourne l'instance de la barre de navigation utilisée dans cette vue.
     *
     * @return la barre de navigation {@code NavBarView}
     */
    public NavBarView getNavBarView() {
        return navBarView;
    }
}
