package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class AddOptionView {
    private final VBox root;
    private final Button submit;
    private final Label title;
    private final TextField nomField;

    public AddOptionView() {
        root = new VBox(10);
        root.setPadding(new Insets(20));

        title = new Label("Ajouter une option");

        nomField        = new TextField();
        nomField.setPromptText("Nom");

        submit = new Button("Ajouter");

        root.getChildren().addAll(
                title,
                new Label("Nom"), nomField,
                submit
        );
    }

    public VBox getRoot() {
        return root;
    }
    public Button getBtnSubmit(){return submit;}
    public TextField getNomField() {return nomField;}
}
