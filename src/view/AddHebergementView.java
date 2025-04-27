package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import modele.Options;

import java.util.ArrayList;
import java.util.List;

/**
 * Vue pour ajouter un hébergement avec sélection d'options.
 */
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

    private final VBox optionsBox;
    private final List<CheckBox> checkBoxes;

    /**
     * Construit la vue pour ajouter un hébergement avec les types et options spécifiés.
     *
     * @param types Liste des types d'hébergement disponibles.
     * @param options Liste des options disponibles.
     */
    public AddHebergementView(ArrayList<String> types, List<Options> options) {
        root = new VBox(10);
        root.setPadding(new Insets(20));

        title = new Label("Ajouter un logement");

        nomField = new TextField();
        nomField.setPromptText("Nom");

        typeBox = new ChoiceBox<>(FXCollections.observableArrayList(types));

        adresseField = new TextField();
        adresseField.setPromptText("Adresse");

        descriptionArea = new TextArea();
        descriptionArea.setPromptText("Description");

        prixField = new TextField();
        prixField.setPromptText("Prix");

        noteSpin = new Spinner<>(0, 5, 0);

        btnChooseImage = new Button("Choisir une image");
        imagePathField = new TextField();
        imagePathField.setPromptText("Aucun fichier sélectionné");
        imagePathField.setEditable(false);

        submit = new Button("Ajouter");

        optionsBox = new VBox(5);
        checkBoxes = new ArrayList<>();

        for (Options option : options) {
            CheckBox cb = new CheckBox(option.getNom());
            cb.setUserData(option);
            checkBoxes.add(cb);
            optionsBox.getChildren().add(cb);
        }

        root.getChildren().addAll(
                title,
                new Label("Nom"), nomField,
                new Label("Type"), typeBox,
                new Label("Adresse"), adresseField,
                new Label("Description"), descriptionArea,
                new Label("Prix"), prixField,
                new Label("Note"), noteSpin,
                new Label("Image"), btnChooseImage, imagePathField,
                new Label("Options disponibles"), optionsBox,
                submit
        );
    }

    public VBox getRoot() {
        return root;
    }

    public Button getBtnSubmit() {
        return submit;
    }

    public TextField getNomField() {
        return nomField;
    }

    public TextField getAdresseField() {
        return adresseField;
    }

    public TextArea getDescriptionArea() {
        return descriptionArea;
    }

    public TextField getPrixField() {
        return prixField;
    }

    public Spinner<Integer> getNoteSpin() {
        return noteSpin;
    }

    public String getType() {
        return typeBox.getValue();
    }

    public Button getBtnChooseImage() {
        return btnChooseImage;
    }

    public String getImagePath() {
        return imagePathField.getText();
    }

    public void setImagePath(String path) {
        imagePathField.setText(path);
    }

    public List<Options> getSelectedOptions() {
        List<Options> selected = new ArrayList<>();
        for (CheckBox cb : checkBoxes) {
            if (cb.isSelected()) {
                selected.add((Options) cb.getUserData());
            }
        }
        return selected;
    }
}
