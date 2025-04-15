package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class BookingPageView {

    private Scene scene;
    private BorderPane root;

    private NavBarView navBarView;

    // Section principale (image + infos)
    private Button favoriteButton;
    private Label headingLabel;
    private Label priceTagLabel;
    private Label bigPriceLabel;
    private Label smallTextLabel;
    private DatePicker dateArriveePicker;
    private DatePicker dateDepartPicker;
    private Button reserverButton;
    private TitledPane faqTitledPane;

    // Deux images en dessous
    private ImageView featureImg1, featureImg2;

    // Section Avis
    private Label avisTitle;
    private Label avisSubtitle;

    public BookingPageView() {
        createUI();
    }

    private void createUI() {
        root = new BorderPane();
        navBarView = new NavBarView();
        root.setTop(navBarView.getNavBar());

        // Contenu principal scrollable
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(0));

        // Conteneur vertical global, centré
        VBox mainContainer = new VBox(30);
        mainContainer.setPadding(new Insets(20));
        mainContainer.setAlignment(Pos.CENTER);
        scrollPane.setContent(mainContainer);

        // 1) Section principale : image + infos, centrée
        HBox topSection = new HBox(30);
        topSection.setAlignment(Pos.CENTER);  // Centre l'image et l'infoBox horizontalement

        // -- Image principale
        StackPane imagePane = new StackPane();
        imagePane.setMaxWidth(500);
        imagePane.setMaxHeight(500);

        ImageView mainImage = new ImageView(new Image("file:src/resources/larry.jpeg"));
        mainImage.setFitWidth(500);
        mainImage.setFitHeight(500);
        mainImage.setPreserveRatio(false);

        favoriteButton = new Button("♡");
        favoriteButton.setStyle("-fx-background-color: #F5F5F5; -fx-border-radius: 50%; -fx-font-size: 16px;");

        imagePane.getChildren().addAll(mainImage, favoriteButton);
        StackPane.setAlignment(favoriteButton, Pos.TOP_LEFT);
        StackPane.setMargin(favoriteButton, new Insets(10,0,0,10));

        // -- Infos à droite
        VBox infoBox = new VBox(15);
        infoBox.setAlignment(Pos.CENTER);  // Centre verticalement le texte dans l'infoBox
        infoBox.setMaxWidth(400);

        headingLabel = new Label("Text Heading");
        headingLabel.setFont(new Font(22));

        priceTagLabel = new Label("Prix par nuit");
        priceTagLabel.setStyle("-fx-text-fill: #28a745; -fx-font-size: 14px;");

        bigPriceLabel = new Label("$50");
        bigPriceLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");

        smallTextLabel = new Label("Text");
        smallTextLabel.setStyle("-fx-text-fill: #555555;");

        // Dates
        GridPane datesGrid = new GridPane();
        datesGrid.setAlignment(Pos.CENTER);
        datesGrid.setHgap(20);
        dateArriveePicker = new DatePicker();
        dateDepartPicker = new DatePicker();

        Label dateArriveeLabel = new Label("Date d'arrivée");
        Label dateDepartLabel = new Label("Date de départ");

        datesGrid.add(dateArriveeLabel, 0, 0);
        datesGrid.add(dateArriveePicker, 0, 1);
        datesGrid.add(dateDepartLabel, 1, 0);
        datesGrid.add(dateDepartPicker, 1, 1);

        reserverButton = new Button("Réserver");
        reserverButton.setStyle("-fx-background-color: #000; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");

        // Accordion (FAQ)
        faqTitledPane = new TitledPane("Title",
                new Label("Answer the frequently asked question in a simple sentence,\n" +
                        "a longish paragraph, or even in a list."));
        faqTitledPane.setExpanded(false);

        infoBox.getChildren().addAll(
                headingLabel,
                priceTagLabel,
                bigPriceLabel,
                smallTextLabel,
                datesGrid,
                reserverButton,
                faqTitledPane
        );

        topSection.getChildren().addAll(imagePane, infoBox);

        // 2) Deux images en dessous, centrées
        HBox featureImagesRow = new HBox(20);
        featureImagesRow.setAlignment(Pos.CENTER);

        featureImg1 = new ImageView(new Image("file:src/resources/homepage.jpg"));
        featureImg1.setFitWidth(400);
        featureImg1.setFitHeight(300);

        featureImg2 = new ImageView(new Image("file:src/resources/homepage.jpeg"));
        featureImg2.setFitWidth(400);
        featureImg2.setFitHeight(300);

        featureImagesRow.getChildren().addAll(featureImg1, featureImg2);

        // 3) Section Avis (centrée)
        VBox avisSection = new VBox(15);
        avisSection.setAlignment(Pos.CENTER);

        avisTitle = new Label("Avis");
        avisTitle.setFont(new Font(20));
        avisSubtitle = new Label("Ce qu'en pensent les ienclis");
        avisSubtitle.setStyle("-fx-text-fill: #555;");

        // FlowPane pour disposer les avis
        FlowPane avisFlow = new FlowPane(20, 20);
        avisFlow.setPrefWrapLength(900);
        avisFlow.setAlignment(Pos.CENTER);

        for (int i = 0; i < 3; i++) {
            VBox card = new VBox(5);
            card.setPadding(new Insets(10));
            card.setStyle("-fx-background-color: #FAFAFA; -fx-border-color: #E5E5E5;");
            card.setPrefWidth(250);

            Label quoteLabel = new Label("\"MASHALLAH\"");
            quoteLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            Label nameLabel = new Label("INCROYABLE");
            Label descLabel = new Label("SARTEK LE DEGRADE");
            descLabel.setStyle("-fx-text-fill: #777; -fx-font-size: 12px;");

            // Petite image de profil
            ImageView avatar = new ImageView(new Image("file:src/resources/avatar_40.png"));
            avatar.setFitWidth(40);
            avatar.setFitHeight(40);

            HBox userRow = new HBox(10, avatar, new VBox(nameLabel, descLabel));
            card.getChildren().addAll(quoteLabel, userRow);
            avisFlow.getChildren().add(card);
        }

        avisSection.getChildren().addAll(avisTitle, avisSubtitle, avisFlow);

        // On assemble tout dans le conteneur principal
        mainContainer.getChildren().addAll(topSection, featureImagesRow, avisSection);

        root.setCenter(scrollPane);
        scene = new Scene(root, 1200, 900);
    }

    // GETTERS
    public Scene getScene() {
        return scene;
    }

    public NavBarView getNavBarView() {
        return navBarView;
    }

    public Button getFavoriteButton() {
        return favoriteButton;
    }

    public Button getReserverButton() {
        return reserverButton;
    }

    public DatePicker getDateArriveePicker() {
        return dateArriveePicker;
    }

    public DatePicker getDateDepartPicker() {
        return dateDepartPicker;
    }
}
