package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class AddHebergementView {
    private final VBox root;
    private final Button submit;
    private final Label title;
    private final TextField nomField;
    private final ChoiceBox<Integer> typeBox;
    private final TextField adresseField;
    private final TextArea descriptionArea;
    private final TextField prixField;
    private final Spinner<Integer> noteSpin;

    public AddHebergementView() {
        root = new VBox(10);
        root.setPadding(new Insets(20));

        title = new Label("Ajouter un logement");

        nomField        = new TextField();
        nomField.setPromptText("Nom");

        typeBox = new ChoiceBox<>(FXCollections.observableArrayList(0,1,2,3));
        typeBox.setValue(0);

        adresseField    = new TextField();
        adresseField.setPromptText("Adresse");

        descriptionArea  = new TextArea();
        descriptionArea.setPromptText("Description");

        prixField       = new TextField();
        prixField.setPromptText("Prix");

        noteSpin = new Spinner<>(0, 5, 0);

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
                submit
        );
    }

    public VBox getRoot() {
        return root;
    }
    public Button getBtnSubmit(){return submit;}

    public TextField getNomField() {return nomField;}

    public ChoiceBox<Integer> getTypeBox() {return typeBox;}

    public TextField getAdresseField() {return adresseField;}

    public TextArea getDescriptionArea() {return descriptionArea;}

    public TextField getPrixField() {return prixField;}

    public Spinner<Integer> getNoteSpin() {return noteSpin;}

}
