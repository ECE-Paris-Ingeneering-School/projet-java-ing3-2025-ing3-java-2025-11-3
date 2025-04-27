package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Vue permettant d'ajouter une offre de réduction avec un code promo et un pourcentage.
 * Cette vue inclut des champs pour saisir le code promo et le pourcentage, ainsi qu'un bouton de soumission.
 */
public class AddReductionView {

    private final VBox root;
    private final Button submit;
    private final Label title;
    private final TextField codePromo;
    private final TextField pourcentage;

    /**
     * Constructeur pour initialiser la vue d'ajout d'une offre de réduction avec les champs code promo et pourcentage.
     */
    public AddReductionView() {
        root = new VBox(10);
        root.setPadding(new Insets(20));

        title = new Label("Ajouter une offre de réduction");

        codePromo = new TextField();
        codePromo.setPromptText("Code promo");

        pourcentage = new TextField();
        pourcentage.setPromptText("pourcentage(0-100)");

        submit = new Button("Ajouter");

        root.getChildren().addAll(
                title,
                new Label("Code promo"), codePromo,
                new Label("Pourcentage"), pourcentage,
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
     * Retourne le bouton de soumission pour ajouter l'offre de réduction.
     *
     * @return Le bouton de soumission.
     */
    public Button getBtnSubmit() {
        return submit;
    }

    /**
     * Retourne le champ de texte pour le code promo.
     *
     * @return Le champ de texte pour le code promo.
     */
    public TextField getCodePromo() {
        return codePromo;
    }

    /**
     * Retourne le champ de texte pour le pourcentage de réduction.
     *
     * @return Le champ de texte pour le pourcentage.
     */
    public TextField getPourcentage() {
        return pourcentage;
    }

    /**
     * Réinitialise les champs de texte code promo et pourcentage à leur état initial (vide).
     */
    public void resetFields() {
        this.codePromo.setText(null);
        this.pourcentage.setText(null);
    }
}
