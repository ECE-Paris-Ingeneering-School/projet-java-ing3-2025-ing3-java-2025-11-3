package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

/**
 * Vue pour ajouter un hébergement.
 * Contient tous les champs nécessaires pour saisir les informations d'un logement.
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

    /**
     * Construit la vue pour ajouter un hébergement avec les types spécifiés.
     *
     * @param types Liste des types d'hébergement disponibles.
     */
    public AddHebergementView(ArrayList<String> types) {
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

    /**
     * Retourne le conteneur principal de la vue.
     *
     * @return VBox racine.
     */
    public VBox getRoot() {
        return root;
    }

    /**
     * Retourne le bouton de soumission.
     *
     * @return Bouton pour ajouter un hébergement.
     */
    public Button getBtnSubmit() {
        return submit;
    }

    /**
     * Retourne le champ de saisie du nom.
     *
     * @return Champ de texte pour le nom.
     */
    public TextField getNomField() {
        return nomField;
    }

    /**
     * Retourne le champ de saisie de l'adresse.
     *
     * @return Champ de texte pour l'adresse.
     */
    public TextField getAdresseField() {
        return adresseField;
    }

    /**
     * Retourne le champ de saisie de la description.
     *
     * @return Zone de texte pour la description.
     */
    public TextArea getDescriptionArea() {
        return descriptionArea;
    }

    /**
     * Retourne le champ de saisie du prix.
     *
     * @return Champ de texte pour le prix.
     */
    public TextField getPrixField() {
        return prixField;
    }

    /**
     * Retourne le sélecteur de note.
     *
     * @return Spinner pour la note (0-5).
     */
    public Spinner<Integer> getNoteSpin() {
        return noteSpin;
    }

    /**
     * Retourne le type d'hébergement sélectionné.
     *
     * @return Type sélectionné dans la ChoiceBox.
     */
    public String getType() {
        return typeBox.getValue();
    }

    /**
     * Retourne le bouton pour choisir une image.
     *
     * @return Bouton de sélection d'image.
     */
    public Button getBtnChooseImage() {
        return btnChooseImage;
    }

    /**
     * Retourne le chemin de l'image sélectionnée.
     *
     * @return Chemin de l'image sous forme de texte.
     */
    public String getImagePath() {
        return imagePathField.getText();
    }

    /**
     * Définit le chemin de l'image sélectionnée.
     *
     * @param path Chemin du fichier image.
     */
    public void setImagePath(String path) {
        imagePathField.setText(path);
    }

}
