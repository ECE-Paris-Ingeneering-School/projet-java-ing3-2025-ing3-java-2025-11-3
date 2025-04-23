package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Client;
import modele.User;

import java.util.List;

public class UsersListView {
    private final VBox root;
    private final TableView<Client> table;

    public UsersListView(List<Client> listeClients) {
        root = new VBox(10);
        root.setPadding(new Insets(20));

        Label title = new Label("Liste des utilisateurs");
        table = new TableView<>();
        table.getItems().setAll(listeClients);

        TableColumn<Client, Integer> idCol    = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Client, String> nomCol    = new TableColumn<>("Nom");
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Client, String> prenomCol = new TableColumn<>("Prénom");
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn<Client, String> emailCol  = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Client, String> pwdCol    = new TableColumn<>("Mot de passe");
        pwdCol.setCellValueFactory(new PropertyValueFactory<>("password"));

        table.getColumns().addAll(idCol, nomCol, prenomCol, emailCol, pwdCol);
        table.setPlaceholder(new Label("Aucun utilisateur"));

        root.getChildren().addAll(title, table);
    }

    public VBox getRoot() {
        return root;
    }
}
