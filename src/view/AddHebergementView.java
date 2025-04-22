package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class AddHebergementView {
    private final VBox root;

    public AddHebergementView() {
        root = new VBox(10);
        root.setPadding(new Insets(20));

        Label title = new Label("Ajouter un logement");

        TextField nomField        = new TextField();
        nomField.setPromptText("Nom");

        ChoiceBox<Integer> typeBox = new ChoiceBox<>(FXCollections.observableArrayList(0,1,2,3));
        typeBox.setValue(0);

        TextField adresseField    = new TextField();
        adresseField.setPromptText("Adresse");

        TextArea descriptionArea  = new TextArea();
        descriptionArea.setPromptText("Description");

        TextField prixField       = new TextField();
        prixField.setPromptText("Prix");

        Spinner<Integer> noteSpin = new Spinner<>(0, 5, 0);

        // Bouton d'ajout
        Button submit = new Button("Ajouter");

        root.getChildren().addAll(
                title,
                new Label("Nom"), nomField,
                new Label("Type"), typeBox,
                new Label("Adresse"), adresseField,
                new Label("Description"), descriptionArea,
                new Label("Prix"), prixField,
                new Label("Note"), noteSpin,
                submit
        );
    }

    public VBox getRoot() {
        return root;
    }
}
