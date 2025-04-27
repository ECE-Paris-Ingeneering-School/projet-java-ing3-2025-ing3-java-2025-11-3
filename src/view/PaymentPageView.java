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
/**
 * La classe {@code PaymentPageView} représente la vue de la page de paiement de l'application.
 * Elle organise une interface graphique simple avec des champs pour entrer les informations de paiement
 * et un bouton pour valider le paiement.
 */
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

    /**
     * Construit une nouvelle instance de {@code PaymentPageView}.
     * Initialise l'interface utilisateur et affiche le montant initial à payer.
     *
     * @param initialPrice le prix initial du paiement
     */
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
    /**
     * Applique un style standard aux champs de texte.
     *
     * @param tf le champ de texte à styliser
     */
    private void styleTextField(TextField tf) {
        tf.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #ccc;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;" +
                        "-fx-padding: 8 10;"
        );
    }
    /**
     * Applique un style primaire aux boutons principaux (ex: bouton de validation).
     *
     * @param btn le bouton à styliser
     */
    private void stylePrimaryButton(Button btn) {
        btn.setFont(Font.font(14));
        btn.setTextFill(Color.WHITE);
        btn.setBackground(new Background(new BackgroundFill(
                Color.BLACK, new CornerRadii(5), Insets.EMPTY
        )));
        btn.setPadding(new Insets(10, 20, 10, 20));
    }
    /**
     * Applique un style secondaire aux boutons secondaires (ex: bouton d'annulation, bouton appliquer).
     *
     * @param btn le bouton à styliser
     */
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
    /**
     * Retourne la scène principale de la page de paiement.
     *
     * @return la {@code Scene} affichant l'interface de paiement
     */
    public Scene getScene() { return scene; }

    /**
     * Retourne le champ de saisie du numéro de carte bancaire.
     *
     * @return le champ {@code TextField} pour le numéro de carte
     */
    public TextField getCardNumberField() { return cardNumberField; }
    /**
     * Retourne le champ de saisie de la date d'expiration de la carte.
     *
     * @return le champ {@code TextField} pour la date d'expiration
     */
    public TextField getExpiryField()    { return expiryField; }
    /**
     * Retourne le champ de saisie du code CVV de la carte.
     *
     * @return le champ {@code TextField} pour le code CVV
     */
    public TextField getCvvField()       { return cvvField; }
    /**
     * Retourne le champ de saisie du code promotionnel.
     *
     * @return le champ {@code TextField} pour le code promo
     */
    public TextField getPromoCodeField() { return promoCodeField; }
    /**
     * Retourne le bouton d'application du code promotionnel.
     *
     * @return le bouton {@code Button} pour appliquer le code promo
     */
    public Button    getApplyPromoButton(){ return applyPromoButton; }
    /**
     * Retourne le bouton de validation du paiement.
     *
     * @return le bouton {@code Button} pour valider le paiement
     */
    public Button    getConfirmButton()  { return confirmButton; }
    /**
     * Retourne l'étiquette affichant le montant à payer.
     *
     * @return l'étiquette {@code Label} pour le montant
     */
    public Label     getPriceLabel()     { return priceLabel; }
    /**
     * Retourne le bouton d'annulation du paiement.
     *
     * @return le bouton {@code Button} pour annuler le paiement
     */
    public Button getCancelButton() { return cancelButton; }
}
