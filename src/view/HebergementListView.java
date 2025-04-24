package view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import modele.Hebergement;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class HebergementListView {
    private final VBox root;
    private final TableView<Hebergement> table;

    public HebergementListView(List<Hebergement> hebergements) {
        root = new VBox(10);
        root.setPadding(new Insets(20));

        Label title = new Label("Liste des hébergements");
        table = new TableView<>();
        table.getItems().setAll(hebergements);

        // Configuration des colonnes pour la TableView

        TableColumn<Hebergement, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("hid"));

        TableColumn<Hebergement, String> nomCol = new TableColumn<>("Nom");
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Hebergement, Integer> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Hebergement, String> addrCol = new TableColumn<>("Adresse");
        addrCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        TableColumn<Hebergement, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Hebergement, Integer> prixCol = new TableColumn<>("Prix");
        prixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));

        TableColumn<Hebergement, Integer> noteCol = new TableColumn<>("Note");
        noteCol.setCellValueFactory(new PropertyValueFactory<>("note"));

        TableColumn<Hebergement, String> imagesCol = new TableColumn<>("Images");
        imagesCol.setCellValueFactory(cell ->
                new SimpleStringProperty(
                        cell.getValue().getImage() == null
                                ? ""
                                : String.join(", ", cell.getValue().getImage())
                )
        );

        // Colonne pour le nombre d'options
        TableColumn<Hebergement, Integer> optionsCol = new TableColumn<>("Nb Options");
        optionsCol.setCellValueFactory(cell ->
                new SimpleIntegerProperty(
                        cell.getValue().getOptions() == null
                                ? 0
                                : cell.getValue().getOptions().size()
                ).asObject()
        );

        // Colonne pour le nombre d'avis
        TableColumn<Hebergement, Integer> avisCol = new TableColumn<>("Nb Avis");
        avisCol.setCellValueFactory(cell ->
                new SimpleIntegerProperty(
                        cell.getValue().getAvis() == null
                                ? 0
                                : cell.getValue().getAvis().size()
                ).asObject()
        );

        // Ajout des colonnes à la TableView
        table.getColumns().addAll(
                idCol, nomCol, typeCol, addrCol, descCol,
                prixCol, noteCol, imagesCol, optionsCol, avisCol
        );

        // Message quand la TableView est vide
        table.setPlaceholder(new Label("Aucun hébergement"));

        root.getChildren().addAll(title, table);
    }

    public VBox getRoot() {
        return root;
    }
}
