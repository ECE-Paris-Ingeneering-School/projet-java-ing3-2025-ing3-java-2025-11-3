package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class PaymentPageView {
    private Scene scene;

    // Champs de saisie
    private TextField cardNumberField;
    private TextField expiryField;
    private TextField cvvField;
    private TextField promoCodeField;

    // Boutons
    private Button applyPromoButton;
    private Button confirmButton;

    // Affichage du prix (mis à jour si promo)
    private Label priceLabel;

    public PaymentPageView(double initialPrice) {
        // Layout principal
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        // Titre
        Label title = new Label("Paiement");
        title.setFont(new Font(24));

        // Grille des champs
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Numéro de carte
        grid.add(new Label("Numéro de carte :"), 0, 0);
        cardNumberField = new TextField();
        grid.add(cardNumberField, 1, 0);

        // Date d'expiration
        grid.add(new Label("Date d'expiration :"), 0, 1);
        expiryField = new TextField();
        expiryField.setPromptText("MM/AA");
        grid.add(expiryField, 1, 1);

        // CVV
        grid.add(new Label("CVV :"), 0, 2);
        cvvField = new TextField();
        cvvField.setPromptText("3 chiffres");
        grid.add(cvvField, 1, 2);

        // Code promo + bouton
        grid.add(new Label("Code promo :"), 0, 3);
        promoCodeField = new TextField();
        grid.add(promoCodeField, 1, 3);
        applyPromoButton = new Button("Appliquer");
        grid.add(applyPromoButton, 2, 3);

        // Étiquette du prix
        priceLabel = new Label(String.format("Montant à payer : %.2f €", initialPrice));
        priceLabel.setFont(new Font(18));

        // Bouton de confirmation
        confirmButton = new Button("Valider le paiement");
        confirmButton.setDisable(false);

        root.getChildren().addAll(title, grid, priceLabel, confirmButton);
        scene = new Scene(root, 600, 400);
    }

    // Getters
    public Scene getScene() { return scene; }
    public TextField getCardNumberField() { return cardNumberField; }
    public TextField getExpiryField() { return expiryField; }
    public TextField getCvvField() { return cvvField; }
    public TextField getPromoCodeField() { return promoCodeField; }
    public Button getApplyPromoButton() { return applyPromoButton; }
    public Button getConfirmButton() { return confirmButton; }
    public Label getPriceLabel() { return priceLabel; }
}
