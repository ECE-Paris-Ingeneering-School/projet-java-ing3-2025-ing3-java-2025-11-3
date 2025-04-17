package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import MODELE.Hebergement; // Assure-toi que ce modèle existe bien
import MODELE.Avis; // Assure-toi que ce modèle existe bien

import java.util.List;

public class BookingPageView {

    private Scene scene;
    private BorderPane root;

    private NavBarView navBarView;

    private Button favoriteButton;
    private Label headingLabel;
    private Label priceTagLabel;
    private Label bigPriceLabel;
    private Label smallTextLabel;
    private DatePicker dateArriveePicker;
    private DatePicker dateDepartPicker;
    private Button reserverButton;
    private TitledPane faqTitledPane;

    private ImageView featureImg1, featureImg2;

    private Label avisTitle;
    private Label avisSubtitle;

    public BookingPageView(Hebergement hebergement, List<Avis> avisList) {
        createUI(hebergement, avisList);
    }

    private void createUI(Hebergement hebergement, List<Avis> avisList) {
        root = new BorderPane();
        navBarView = new NavBarView();
        root.setTop(navBarView.getNavBar());

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(0));

        VBox mainContainer = new VBox(30);
        mainContainer.setPadding(new Insets(20));
        mainContainer.setAlignment(Pos.CENTER);
        scrollPane.setContent(mainContainer);

        HBox topSection = new HBox(30);
        topSection.setAlignment(Pos.CENTER);

        StackPane imagePane = new StackPane();
        imagePane.setMaxWidth(500);
        imagePane.setMaxHeight(500);

        ImageView mainImage = new ImageView(new Image("file:src/resources/" + hebergement.getImage()));
        mainImage.setFitWidth(500);
        mainImage.setFitHeight(500);
        mainImage.setPreserveRatio(false);

        favoriteButton = new Button("♡");
        favoriteButton.setStyle("-fx-background-color: #F5F5F5; -fx-border-radius: 50%; -fx-font-size: 16px;");
        imagePane.getChildren().addAll(mainImage, favoriteButton);
        StackPane.setAlignment(favoriteButton, Pos.TOP_LEFT);
        StackPane.setMargin(favoriteButton, new Insets(10,0,0,10));

        VBox infoBox = new VBox(15);
        infoBox.setAlignment(Pos.CENTER);
        infoBox.setMaxWidth(400);

        headingLabel = new Label(hebergement.getNom());
        headingLabel.setFont(new Font(22));

        priceTagLabel = new Label("Prix par nuit");
        priceTagLabel.setStyle("-fx-text-fill: #28a745; -fx-font-size: 14px;");

        bigPriceLabel = new Label(hebergement.getPrix() + "€");
        bigPriceLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");

        smallTextLabel = new Label(hebergement.getAdresse());
        smallTextLabel.setStyle("-fx-text-fill: #555555;");

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

        faqTitledPane = new TitledPane("FAQ", new Label("Des infos utiles pour votre séjour..."));
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

       /* HBox featureImagesRow = new HBox(20);
        featureImagesRow.setAlignment(Pos.CENTER);

        featureImg1 = new ImageView(new Image("file:src/resources/homepage.jpg"));
        featureImg1.setFitWidth(400);
        featureImg1.setFitHeight(300);

        featureImg2 = new ImageView(new Image("file:src/resources/homepage.jpeg"));
        featureImg2.setFitWidth(400);
        featureImg2.setFitHeight(300);

        featureImagesRow.getChildren().addAll(featureImg1, featureImg2);*/

        VBox avisSection = new VBox(15);
        avisSection.setAlignment(Pos.CENTER);

        avisTitle = new Label("Avis");
        avisTitle.setFont(new Font(20));
        avisSubtitle = new Label("Ce qu'en pensent les utilisateurs");
        avisSubtitle.setStyle("-fx-text-fill: #555;");

        FlowPane avisFlow = new FlowPane(20, 20);
        avisFlow.setPrefWrapLength(900);
        avisFlow.setAlignment(Pos.CENTER);

        if (avisList.isEmpty()) {
            Label noAvisLabel = new Label("Aucun avis pour cet hébergement.");
            noAvisLabel.setStyle("-fx-font-style: italic; -fx-text-fill: #888;");
            avisFlow.getChildren().add(noAvisLabel);
        } else {
            for (Avis avis : avisList) {
                VBox card = new VBox(5);
                card.setPadding(new Insets(10));
                card.setStyle("-fx-background-color: #FAFAFA; -fx-border-color: #E5E5E5;");
                card.setPrefWidth(250);

                Label noteLabel = new Label("Note : " + avis.getNote() + "/5");
                noteLabel.setStyle("-fx-font-weight: bold;");

                Label commentaireLabel = new Label(avis.getCommentaire());
                commentaireLabel.setWrapText(true);
                commentaireLabel.setStyle("-fx-text-fill: #555;");

                card.getChildren().addAll(noteLabel, commentaireLabel);
                avisFlow.getChildren().add(card);
            }
        }

        avisSection.getChildren().addAll(avisTitle, avisSubtitle, avisFlow);

        mainContainer.getChildren().addAll(topSection, avisSection);

        root.setCenter(scrollPane);
        scene = new Scene(root, 1200, 900);
    }

    // GETTERS
    public Scene getScene() { return scene; }
    public NavBarView getNavBarView() { return navBarView; }
    public Button getFavoriteButton() { return favoriteButton; }
    public Button getReserverButton() { return reserverButton; }
    public DatePicker getDateArriveePicker() { return dateArriveePicker; }
    public DatePicker getDateDepartPicker() { return dateDepartPicker; }
}
