package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Vue permettant d'ajouter une option.
 * Cette vue inclut des champs pour le nom et la description de l'option, ainsi qu'un bouton de soumission.
 */
public class AddOptionView {

    private final VBox root;
    private final Button submit;
    private final Label title;
    private final TextField nomField;
    private final TextField descriptionField;

    /**
     * Constructeur pour initialiser la vue d'ajout d'option avec les champs nom et description.
     */
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

    /**
     * Retourne le conteneur racine de la vue.
     *
     * @return Le conteneur racine de la vue sous forme de {@link VBox}.
     */
    public VBox getRoot() {
        return root;
    }

    /**
     * Retourne le bouton de soumission pour ajouter l'option.
     *
     * @return Le bouton de soumission.
     */
    public Button getBtnSubmit() {
        return submit;
    }

    /**
     * Retourne le champ de texte pour le nom de l'option.
     *
     * @return Le champ de texte pour le nom.
     */
    public TextField getNomField() {
        return nomField;
    }

    /**
     * Retourne le champ de texte pour la description de l'option.
     *
     * @return Le champ de texte pour la description.
     */
    public TextField getDescriptionField() {
        return descriptionField;
    }

    /**
     * Réinitialise les champs de texte nom et description à leur état initial (vide).
     */
    public void resetFields() {
        this.nomField.setText(null);
        this.descriptionField.setText(null);
    }
}
