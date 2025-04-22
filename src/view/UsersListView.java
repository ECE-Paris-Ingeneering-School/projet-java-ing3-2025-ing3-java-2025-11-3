package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.User;

public class UsersListView {
    private final VBox root;

    public UsersListView() {
        root = new VBox(10);
        root.setPadding(new Insets(20));

        Label title = new Label("Liste des utilisateurs");
        TableView<User> table = new TableView<>();

        TableColumn<User, Integer> idCol    = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> nomCol    = new TableColumn<>("Nom");
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<User, String> prenomCol = new TableColumn<>("Prénom");
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn<User, String> emailCol  = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<User, String> pwdCol    = new TableColumn<>("Mot de passe");
        pwdCol.setCellValueFactory(new PropertyValueFactory<>("password"));

        table.getColumns().addAll(idCol, nomCol, prenomCol, emailCol, pwdCol);
        table.setPlaceholder(new Label("Aucun utilisateur"));

        root.getChildren().addAll(title, table);
    }

    public VBox getRoot() {
        return root;
    }
}
