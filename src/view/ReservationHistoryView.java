package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Reservation;

public class ReservationHistoryView {
    private final VBox root;

    public ReservationHistoryView() {
        root = new VBox(10);
        root.setPadding(new Insets(20));

        Label title = new Label("Toutes les réservations passées");
        TableView<Reservation> table = new TableView<>();

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

    public VBox getRoot() {
        return root;
    }
}
