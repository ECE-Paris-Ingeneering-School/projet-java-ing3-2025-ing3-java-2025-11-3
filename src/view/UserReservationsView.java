package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Reservation;
import javafx.scene.layout.HBox;
public class UserReservationsView {
    private final VBox root;

    public UserReservationsView() {
        root = new VBox(10);
        root.setPadding(new Insets(20));

        Label title = new Label("Réservations d’un utilisateur");
        ComboBox<String> userSelect = new ComboBox<>(
                FXCollections.observableArrayList("Utilisateur #1", "Utilisateur #2")
        );
        userSelect.setPromptText("Sélectionnez un utilisateur");
        Button loadBtn = new Button("Charger");

        TableView<Reservation> table = new TableView<>();
        TableColumn<Reservation, Integer> idCol    = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Reservation, String> debutCol = new TableColumn<>("Date Début");
        debutCol.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));

        TableColumn<Reservation, String> finCol   = new TableColumn<>("Date Fin");
        finCol.setCellValueFactory(new PropertyValueFactory<>("dateFin"));

        TableColumn<Reservation, Float> prixCol   = new TableColumn<>("Prix");
        prixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));

        table.getColumns().addAll(idCol, debutCol, finCol, prixCol);
        table.setPlaceholder(new Label("Sélectionnez un utilisateur"));

        HBox controls = new HBox(10, userSelect, loadBtn);
        root.getChildren().addAll(title, controls, table);
    }

    public VBox getRoot() {
        return root;
    }
}
