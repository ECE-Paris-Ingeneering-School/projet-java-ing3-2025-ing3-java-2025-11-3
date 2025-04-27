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
    private final TextField descriptionField;

    public AddOptionView() {
        root = new VBox(10);
        root.setPadding(new Insets(20));

        title = new Label("Ajouter une option");

        nomField        = new TextField();
        nomField.setPromptText("Nom");

        descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        submit = new Button("Ajouter");


        root.getChildren().addAll(
                title,
                new Label("Nom"), nomField,
                new Label("Description"), descriptionField,
                submit
        );
    }

    public VBox getRoot() {
        return root;
    }
    public Button getBtnSubmit(){return submit;}
    public TextField getNomField() {return nomField;}
    public TextField getDescriptionField() {return descriptionField;}
    public void resetFields() {this.nomField.setText(null);this.descriptionField.setText(null);}
}
