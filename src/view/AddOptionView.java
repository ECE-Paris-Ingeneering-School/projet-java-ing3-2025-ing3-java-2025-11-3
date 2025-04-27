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
    private final ComboBox<String> hebergementSelect;

    public AddOptionView(ArrayList<String> hSelect) {
        root = new VBox(10);
        root.setPadding(new Insets(20));

        title = new Label("Ajouter une option");

        hebergementSelect = new ComboBox<>(
                FXCollections.observableArrayList(hSelect)
        );
        hebergementSelect.setPromptText("Sélectionnez un hebergement");


        nomField        = new TextField();
        nomField.setPromptText("Nom");

        descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        submit = new Button("Ajouter");


        root.getChildren().addAll(
                title,
                hebergementSelect,
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
    public String getHebergementSelect() {return hebergementSelect.getValue();}
    public void resetFields() {this.nomField.setText(null);this.descriptionField.setText(null);}
}
