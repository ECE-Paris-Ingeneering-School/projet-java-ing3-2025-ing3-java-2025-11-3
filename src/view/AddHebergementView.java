package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import modele.Options;

import java.util.ArrayList;
import java.util.List;

/**
 * Vue permettant d'ajouter un hébergement avec la possibilité de sélectionner des options.
 * Cette vue inclut des champs pour le nom, le type, l'adresse, la description, le prix, la note,
 * une image et des options disponibles.
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
     * Constructeur pour initialiser la vue d'ajout d'hébergement avec les types et options spécifiés.
     *
     * @param types Liste des types d'hébergement disponibles.
     * @param options Liste des options disponibles à sélectionner pour l'hébergement.
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

    /**
     * Retourne le conteneur racine de la vue sous forme de {@link ScrollPane}.
     *
     * @return Un {@link ScrollPane} contenant la vue racine.
     */
    public ScrollPane getRoot() {
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToHeight(true);
        return scrollPane;
    }

    /**
     * Retourne le bouton de soumission pour ajouter l'hébergement.
     *
     * @return Le bouton de soumission.
     */
    public Button getBtnSubmit() {
        return submit;
    }

    /**
     * Retourne le champ de texte pour le nom de l'hébergement.
     *
     * @return Le champ de texte pour le nom.
     */
    public TextField getNomField() {
        return nomField;
    }

    /**
     * Retourne le champ de texte pour l'adresse de l'hébergement.
     *
     * @return Le champ de texte pour l'adresse.
     */
    public TextField getAdresseField() {
        return adresseField;
    }

    /**
     * Retourne la zone de texte pour la description de l'hébergement.
     *
     * @return La zone de texte pour la description.
     */
    public TextArea getDescriptionArea() {
        return descriptionArea;
    }

    /**
     * Retourne le champ de texte pour le prix de l'hébergement.
     *
     * @return Le champ de texte pour le prix.
     */
    public TextField getPrixField() {
        return prixField;
    }

    /**
     * Retourne le composant {@link Spinner} pour la note de l'hébergement.
     *
     * @return Le composant {@link Spinner} pour la note.
     */
    public Spinner<Integer> getNoteSpin() {
        return noteSpin;
    }

    /**
     * Retourne le type d'hébergement sélectionné dans la {@link ChoiceBox}.
     *
     * @return Le type d'hébergement sélectionné.
     */
    public String getType() {
        return typeBox.getValue();
    }

    /**
     * Retourne le bouton pour choisir une image pour l'hébergement.
     *
     * @return Le bouton pour choisir une image.
     */
    public Button getBtnChooseImage() {
        return btnChooseImage;
    }

    /**
     * Retourne le chemin de l'image sélectionnée.
     *
     * @return Le chemin de l'image.
     */
    public String getImagePath() {
        return imagePathField.getText();
    }

    /**
     * Définit le chemin de l'image sélectionnée.
     *
     * @param path Le chemin de l'image à définir.
     */
    public void setImagePath(String path) {
        imagePathField.setText(path);
    }

    /**
     * Retourne la liste des options sélectionnées pour l'hébergement.
     *
     * @return La liste des options sélectionnées.
     */
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
