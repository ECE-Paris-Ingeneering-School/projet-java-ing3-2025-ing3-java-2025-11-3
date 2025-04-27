package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Hebergement;
import modele.Reservation;

import java.util.List;
/**
 * Vue affichant l'historique des réservations.
 * La logique d'action (suppression, modification, etc.) doit être gérée par le contrôleur.
 */
public class ReservationHistoryView {
    private final VBox root;
    private final TableView<Reservation> table;
/**
     * Construit une nouvelle instance de {@code ReservationHistoryView}.
     * Initialise la vue avec un titre et un tableau listant les réservations fournies.
     *
     * @param t la liste des réservations à afficher
     */
    public ReservationHistoryView(List<Reservation> t) {
        root = new VBox(10);
        root.setPadding(new Insets(20));

        Label title = new Label("Toutes les réservations passées");
        table = new TableView<>();
        table.getItems().setAll(t);

        TableColumn<Reservation, Integer> idCol        = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Reservation, String> debutCol     = new TableColumn<>("Date Début");
        debutCol.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));

        TableColumn<Reservation, String> finCol       = new TableColumn<>("Date Fin");
        finCol.setCellValueFactory(new PropertyValueFactory<>("dateFin"));

        TableColumn<Reservation, Integer> hebCol     = new TableColumn<>("ID Hébergement");
        hebCol.setCellValueFactory(new PropertyValueFactory<>("idHebergement"));

        TableColumn<Reservation, Integer> cliCol     = new TableColumn<>("ID Client");
        cliCol.setCellValueFactory(new PropertyValueFactory<>("idClient"));

        TableColumn<Reservation, Float> prixCol       = new TableColumn<>("Prix");
        prixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));

        table.getColumns().addAll(idCol, debutCol, finCol, hebCol, cliCol, prixCol);
        table.setPlaceholder(new Label("Aucune réservation"));

        root.getChildren().addAll(title, table);
    }
/**
     * Retourne la racine de cette vue d'historique de réservations.
     *
     * @return la racine de la vue
     */
    public VBox getRoot() {
        return root;
    }

    // méthode pour ajouter une liste d'hébergements
    /**
     * Définit la liste des réservations à afficher dans le tableau.
     *
     * @param reservations la liste des réservations à afficher
     */
    public void set(List<Reservation> reservations) {
        table.getItems().setAll(reservations);
    }
}
