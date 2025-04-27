package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class AddReductionView {
    private final VBox root;
    private final Button submit;
    private final Label title;
    private final TextField codePromo;
    private final TextField pourcentage;

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

    public VBox getRoot() {
        return root;
    }
    public Button getBtnSubmit(){return submit;}
    public TextField getCodePromo() {return codePromo;}
    public TextField getPourcentage() {return pourcentage;}
    public void resetFields() {this.codePromo.setText(null);this.pourcentage.setText(null);}
}
