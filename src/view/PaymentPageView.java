package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.effect.BlurType;

public class PaymentPageView {
    private final Scene scene;

    private TextField cardNumberField;
    private TextField expiryField;
    private TextField cvvField;
    private TextField promoCodeField;
    private Button applyPromoButton;
    private Button confirmButton;
    private Label priceLabel;
    private Button cancelButton;        // ← nouveau

    public PaymentPageView(double initialPrice) {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #f0f2f5;");

        VBox card = new VBox(20);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(30));
        card.setMaxWidth(500);
        card.setMaxHeight(500);
        card.setEffect(new DropShadow(BlurType.GAUSSIAN,
                Color.rgb(0,0,0,0.1),
                10, 0, 0, 4));
        card.setBackground(new Background(new BackgroundFill(
                Color.WHITE, new CornerRadii(10), Insets.EMPTY
        )));

        Label title = new Label("Paiement sécurisé");
        title.setFont(Font.font(24));
        title.setTextFill(Color.web("#333"));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);

        grid.add(new Label("Numéro de carte:"), 0, 0);
        cardNumberField = new TextField();
        cardNumberField.setPromptText("1234 5678 9012 3456");
        styleTextField(cardNumberField);
        grid.add(cardNumberField, 1, 0);

        grid.add(new Label("Date d’expiration:"), 0, 1);
        expiryField = new TextField();
        expiryField.setPromptText("MM/AA");
        styleTextField(expiryField);
        grid.add(expiryField, 1, 1);

        grid.add(new Label("CVV:"), 0, 2);
        cvvField = new TextField();
        cvvField.setPromptText("***");
        styleTextField(cvvField);
        grid.add(cvvField, 1, 2);

        grid.add(new Label("Code promo :"), 0, 3);
        promoCodeField = new TextField();
        promoCodeField.setPromptText("EXAMPLE2025");
        styleTextField(promoCodeField);
        grid.add(promoCodeField, 1, 3);
        applyPromoButton = new Button("Appliquer");
        styleSecondaryButton(applyPromoButton);
        grid.add(applyPromoButton, 2, 3);

        priceLabel = new Label("Montant à payer");
        priceLabel.setFont(Font.font(18));
        priceLabel.setTextFill(Color.web("#333"));

        confirmButton = new Button("Valider le paiement");
        stylePrimaryButton(confirmButton);


        cancelButton = new Button("Annuler");
        styleSecondaryButton(cancelButton);

        HBox actionBox = new HBox(10, cancelButton, confirmButton);
        actionBox.setAlignment(Pos.CENTER);

        card.getChildren().addAll(
                title,
                grid,
                priceLabel,
                actionBox
        );
        root.getChildren().add(card);

        scene = new Scene(root, 600, 450);
    }

    private void styleTextField(TextField tf) {
        tf.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #ccc;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;" +
                        "-fx-padding: 8 10;"
        );
    }

    private void stylePrimaryButton(Button btn) {
        btn.setFont(Font.font(14));
        btn.setTextFill(Color.WHITE);
        btn.setBackground(new Background(new BackgroundFill(
                Color.BLACK, new CornerRadii(5), Insets.EMPTY
        )));
        btn.setPadding(new Insets(10, 20, 10, 20));
    }

    private void styleSecondaryButton(Button btn) {
        btn.setFont(Font.font(14));
        btn.setTextFill(Color.web("#333"));
        btn.setBackground(new Background(new BackgroundFill(
                Color.TRANSPARENT, new CornerRadii(5), Insets.EMPTY
        )));
        btn.setBorder(new Border(new BorderStroke(
                Color.web("#333"),
                BorderStrokeStyle.SOLID,
                new CornerRadii(5),
                new BorderWidths(1)
        )));
        btn.setPadding(new Insets(8, 16, 8, 16));
    }

    public Scene getScene() { return scene; }
    public TextField getCardNumberField() { return cardNumberField; }
    public TextField getExpiryField()    { return expiryField; }
    public TextField getCvvField()       { return cvvField; }
    public TextField getPromoCodeField() { return promoCodeField; }
    public Button    getApplyPromoButton(){ return applyPromoButton; }
    public Button    getConfirmButton()  { return confirmButton; }
    public Label     getPriceLabel()     { return priceLabel; }
    public Button getCancelButton() { return cancelButton; }
}
