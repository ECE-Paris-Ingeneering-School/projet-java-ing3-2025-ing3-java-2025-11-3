package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Reservation;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Vue affichant les réservations d'un utilisateur spécifique.
 * La logique d'action (chargement des réservations) doit être gérée par le contrôleur.
 */
public class UserReservationsView {
    private final VBox root;
    private final TableView<Reservation> table;
    private final Button loadBtn;
    private final ComboBox<String> userSelect;

    /**
     * Construit une nouvelle instance de {@code UserReservationsView}.
     * Initialise la vue avec un titre et un tableau listant les réservations d'un utilisateur.
     *
     * @param usersName la liste des noms d'utilisateurs à afficher
     */
    public UserReservationsView(ArrayList<String> usersName) {
        root = new VBox(10);
        root.setPadding(new Insets(20));

        Label title = new Label("Réservations d’un utilisateur");
        userSelect = new ComboBox<>(
                FXCollections.observableArrayList(usersName)
        );
        userSelect.setPromptText("Sélectionnez un utilisateur");
        loadBtn = new Button("Charger");

        table = new TableView<>();

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

    /**
     * Retourne la racine de cette vue de réservations d'utilisateur.
     *
     * @return la racine de la vue
     */
    public VBox getRoot() {
        return root;
    }


    /**
     * Retourne le tableau affichant les réservations.
     *
     * @return le tableau des réservations
     */
    public Button getLoadBtn() {
        return loadBtn;
    }

    /**
     * Retourne la liste des utilisateurs sélectionnés.
     *
     * @return la liste des utilisateurs
     */
    public String getSelectedUser() {
        return userSelect.getValue();
    }

    /**
     * Définit la liste des réservations à afficher dans le tableau.
     *
     * @param reservations la liste des réservations à afficher
     */
    public void setTable(List<Reservation> reservations){
        table.getItems().clear();
        table.getItems().addAll(reservations);
    }
}
