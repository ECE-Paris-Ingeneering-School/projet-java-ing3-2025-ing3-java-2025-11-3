package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class AdminView {

    private final Scene scene;
    private final BorderPane root;

    // Les items de la sidebar
    private final Label logementsLabel;
    private final Label utilisateursLabel;
    private final Label reservationsPasseesLabel;
    private final Label reservationsUtilisateurLabel;
    private final Label ajouterLogementLabel;
    private final Label supprimerLogementLabel;
    private final Button retourButton;

    // Zone centrale où on injectera nos vues stub
    private final StackPane contentPane;

    public AdminView() {
        root = new BorderPane();

        // --- Sidebar ---
        VBox sidebar = new VBox(15);
        sidebar.setPrefWidth(200);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #F5F5F5;");

        Label title = new Label("Booking Pro");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        sidebar.getChildren().add(title);

        // Création des labels
        logementsLabel               = createMenuLabel("Logements");
        utilisateursLabel            = createMenuLabel("Utilisateurs");
        reservationsPasseesLabel     = createMenuLabel("Réservations passées");
        reservationsUtilisateurLabel = createMenuLabel("Réservations par utilisateur");
        ajouterLogementLabel         = createMenuLabel("Ajouter logement");
        supprimerLogementLabel       = createMenuLabel("Supprimer logement");

        VBox menuBox = new VBox(10,
                logementsLabel,
                utilisateursLabel,
                reservationsPasseesLabel,
                reservationsUtilisateurLabel,
                ajouterLogementLabel,
                supprimerLogementLabel
        );
        menuBox.setPadding(new Insets(20, 0, 0, 0));
        sidebar.getChildren().add(menuBox);

        // Spacer
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        sidebar.getChildren().add(spacer);

        // Bouton retour
        retourButton = new Button("← Retour au site");
        retourButton.setMaxWidth(Double.MAX_VALUE);
        sidebar.getChildren().add(retourButton);

        root.setLeft(sidebar);

        // --- Content area ---
        contentPane = new StackPane();
        contentPane.setStyle("-fx-background-color: white;");
        root.setCenter(contentPane);

        scene = new Scene(root, 1200, 800);
    }

    private Label createMenuLabel(String text) {
        Label lbl = new Label(text);
        lbl.setMaxWidth(Double.MAX_VALUE);
        lbl.setStyle("-fx-font-size: 14px;");
        lbl.setAlignment(Pos.CENTER_LEFT);
        return lbl;
    }

    // Getters
    public Scene getScene() { return scene; }
    public Label getLogementsLabel()               { return logementsLabel; }
    public Label getUtilisateursLabel()            { return utilisateursLabel; }
    public Label getReservationsPasseesLabel()     { return reservationsPasseesLabel; }
    public Label getReservationsUtilisateurLabel() { return reservationsUtilisateurLabel; }
    public Label getAjouterLogementLabel()         { return ajouterLogementLabel; }
    public Label getSupprimerLogementLabel()       { return supprimerLogementLabel; }
    public Button getRetourButton()                { return retourButton; }
    public StackPane getContentPane()              { return contentPane; }
}
