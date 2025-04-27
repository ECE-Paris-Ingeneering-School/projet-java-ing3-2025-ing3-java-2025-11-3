package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import javafx.stage.FileChooser;
import java.io.File;

import java.util.ArrayList;

public class AddHebergementView {
    private final VBox root;
    private final Button submit;
    private final Label title;
    private final TextField nomField;
    private final ChoiceBox<String> typeBox;
    private final TextField adresseField;
    private final TextArea descriptionArea;
    private final TextField prixField;
    private final Spinner<Integer> noteSpin;

    private final Button btnChooseImage;
    private final TextField imagePathField;


    public AddHebergementView(ArrayList<String> types) {
        root = new VBox(10);
        root.setPadding(new Insets(20));

        title = new Label("Ajouter un logement");

        nomField        = new TextField();
        nomField.setPromptText("Nom");

        typeBox = new ChoiceBox<>(FXCollections.observableArrayList(types));
        //typeBox.setValue(0);

        adresseField    = new TextField();
        adresseField.setPromptText("Adresse");

        descriptionArea  = new TextArea();
        descriptionArea.setPromptText("Description");

        prixField       = new TextField();
        prixField.setPromptText("Prix");

        noteSpin = new Spinner<>(0, 5, 0);

        // juste après noteSpin = new Spinner<>(...)
        btnChooseImage   = new Button("Choisir une image");
        imagePathField   = new TextField();
        imagePathField.setPromptText("Aucun fichier sélectionné");
        imagePathField.setEditable(false);

        // Bouton d'ajout
        submit = new Button("Ajouter");

        root.getChildren().addAll(
                title,
                new Label("Nom"), nomField,
                new Label("Type"), typeBox,
                new Label("Adresse"), adresseField,
                new Label("Description"), descriptionArea,
                new Label("Prix"), prixField,
                new Label("Note"), noteSpin,
                new Label("Image"), btnChooseImage, imagePathField,
                submit
        );
    }

    public VBox getRoot() {
        return root;
    }
    public Button getBtnSubmit(){return submit;}

    public TextField getNomField() {return nomField;}

    //public ChoiceBox<Integer> getTypeBox() {return typeBox;}

    public TextField getAdresseField() {return adresseField;}

    public TextArea getDescriptionArea() {return descriptionArea;}

    public TextField getPrixField() {return prixField;}

    public Spinner<Integer> getNoteSpin() {return noteSpin;}

    public String getType() {return typeBox.getValue();}

    public Button getBtnChooseImage() { return btnChooseImage; }

    public String getImagePath() { return imagePathField.getText(); }

    public void setImagePath(String path) { imagePathField.setText(path); }

}
