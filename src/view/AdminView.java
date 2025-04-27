package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

/**
 * Vue principale pour l'interface administrateur. Cette vue inclut une barre de navigation,
 * une barre latérale avec des options de menu et une zone centrale pour afficher les différentes
 * vues administratives (logements, utilisateurs, réductions, etc.).
 */
public class AdminView {

    private final Scene scene;
    private final BorderPane root;

    // Les items de la sidebar
    private final Label logementsLabel;
    private final Label utilisateursLabel;
    private final Label reservationsPasseesLabel;
    private final Label reservationsUtilisateurLabel;
    private final Label ajouterLogementLabel, ajouterOptionLabel;
    private final NavBarView navBarView;
    private final Label ajouterReductionLabel;

    // Zone centrale où on injectera nos vues stub
    private final StackPane contentPane;

    /**
     * Constructeur pour initialiser la vue administrateur, en configurant la barre de navigation,
     * la barre latérale et la zone centrale de contenu.
     */
    public AdminView() {
        root = new BorderPane();

        navBarView = new NavBarView();
        navBarView.getTitleLabel().setText("Booking Pro");

        root.setTop(navBarView.getNavBar());

        // --- Sidebar ---
        VBox sidebar = new VBox(15);
        sidebar.setPrefWidth(200);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #F5F5F5;");

        logementsLabel               = createMenuLabel("Logements");
        ajouterLogementLabel         = createMenuLabel("Ajouter logement");
        ajouterOptionLabel           = createMenuLabel("Ajouter option");
        utilisateursLabel            = createMenuLabel("Utilisateurs");
        reservationsPasseesLabel     = createMenuLabel("Réservations passées");
        reservationsUtilisateurLabel = createMenuLabel("Réservations par utilisateur");
        ajouterReductionLabel        = createMenuLabel("Ajouter réduction");

        VBox menuBox = new VBox(10,
                logementsLabel,
                ajouterLogementLabel,
                ajouterOptionLabel,
                utilisateursLabel,
                reservationsPasseesLabel,
                reservationsUtilisateurLabel,
                ajouterReductionLabel
        );
        menuBox.setPadding(new Insets(20, 0, 0, 0));
        sidebar.getChildren().add(menuBox);

        // Spacer
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        sidebar.getChildren().add(spacer);

        root.setLeft(sidebar);

        // --- Content area ---
        contentPane = new StackPane();
        contentPane.setStyle("-fx-background-color: white;");
        root.setCenter(contentPane);

        scene = new Scene(root, 1200, 800);
    }

    /**
     * Crée une étiquette de menu avec un texte spécifié et une mise en forme de base.
     *
     * @param text Le texte à afficher sur l'étiquette.
     * @return L'étiquette de menu créée.
     */
    private Label createMenuLabel(String text) {
        Label lbl = new Label(text);
        lbl.setMaxWidth(Double.MAX_VALUE);
        lbl.setStyle("-fx-font-size: 14px;");
        lbl.setAlignment(Pos.CENTER_LEFT);
        return lbl;
    }

    // Getters

    /**
     * Retourne la scène de l'interface administrateur.
     *
     * @return La scène principale de la vue administrateur.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Retourne l'étiquette correspondant aux logements dans la barre latérale.
     *
     * @return L'étiquette pour la gestion des logements.
     */
    public Label getLogementsLabel() {
        return logementsLabel;
    }

    /**
     * Retourne l'étiquette correspondant aux utilisateurs dans la barre latérale.
     *
     * @return L'étiquette pour la gestion des utilisateurs.
     */
    public Label getUtilisateursLabel() {
        return utilisateursLabel;
    }

    /**
     * Retourne l'étiquette correspondant aux réservations passées dans la barre latérale.
     *
     * @return L'étiquette pour les réservations passées.
     */
    public Label getReservationsPasseesLabel() {
        return reservationsPasseesLabel;
    }

    /**
     * Retourne l'étiquette correspondant aux réservations par utilisateur dans la barre latérale.
     *
     * @return L'étiquette pour les réservations par utilisateur.
     */
    public Label getReservationsUtilisateurLabel() {
        return reservationsUtilisateurLabel;
    }

    /**
     * Retourne l'étiquette correspondant à l'ajout de logement dans la barre latérale.
     *
     * @return L'étiquette pour l'ajout d'un logement.
     */
    public Label getAjouterLogementLabel() {
        return ajouterLogementLabel;
    }

    /**
     * Retourne la zone centrale de contenu où les vues administratives seront injectées.
     *
     * @return La zone centrale de contenu.
     */
    public StackPane getContentPane() {
        return contentPane;
    }

    /**
     * Retourne la vue de la barre de navigation.
     *
     * @return La vue de la barre de navigation.
     */
    public NavBarView getNavBarView() {
        return navBarView;
    }

    /**
     * Retourne l'étiquette correspondant à l'ajout d'option dans la barre latérale.
     *
     * @return L'étiquette pour l'ajout d'option.
     */
    public Label getAjouterOptionLabel() {
        return ajouterOptionLabel;
    }

    /**
     * Retourne l'étiquette correspondant à l'ajout de réduction dans la barre latérale.
     *
     * @return L'étiquette pour l'ajout de réduction.
     */
    public Label getAjouterReductionLabel() {
        return ajouterReductionLabel;
    }
}
