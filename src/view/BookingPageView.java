package view;

import modele.Options;
import modele.Hebergement;
import modele.Avis;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.List;

public class BookingPageView {

    private Scene scene;
    private BorderPane root;

    private NavBarView navBarView;
    private Button backButton;

    private Button favoriteButton;
    private Label headingLabel;
    private Label priceTagLabel;
    private Label bigPriceLabel;
    private Label smallTextLabel;
    private DatePicker dateArriveePicker;
    private DatePicker dateDepartPicker;
    private Button reserverButton;
    private TitledPane faqTitledPane;

    private Label avisTitle;
    private Label avisSubtitle;

    private int reservationId;

    public BookingPageView(Hebergement hebergement, List<Avis> avisList, List<Options> optionsList) {
        createUI(hebergement, avisList, optionsList);
    }

    private void createUI(Hebergement hebergement, List<Avis> avisList, List<Options> optionsList) {
        root = new BorderPane();

        // Barre de navigation en haut
        navBarView = new NavBarView();
        root.setTop(navBarView.getNavBar());
        reservationId = hebergement.getHid();

        // Scroll central
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(0));

        VBox mainContainer = new VBox(20);
        mainContainer.setPadding(new Insets(20));
        mainContainer.setAlignment(Pos.TOP_CENTER);

        // Bouton Retour sous la NavBar, aligné à gauche
        backButton = new Button("← Retour");
        backButton.setStyle("-fx-background-color: transparent; -fx-font-size: 14px;");
        HBox backBox = new HBox(backButton);
        backBox.setAlignment(Pos.CENTER_LEFT);
        mainContainer.getChildren().add(backBox);

        // Section principale (image + infos)
        HBox topSection = new HBox(30);
        topSection.setAlignment(Pos.CENTER);

        // Image principale
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
        StackPane.setMargin(favoriteButton, new Insets(10));

        // Box infos réservation
        VBox infoBox = new VBox(15);
        infoBox.setMaxWidth(400);
        infoBox.setAlignment(Pos.CENTER_LEFT);

        headingLabel = new Label(hebergement.getNom());
        headingLabel.setFont(new Font(22));

        priceTagLabel = new Label("Prix par nuit");
        priceTagLabel.setStyle("-fx-text-fill: #28a745; -fx-font-size: 14px;");

        bigPriceLabel = new Label(hebergement.getPrix() + "€");
        bigPriceLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");

        smallTextLabel = new Label(hebergement.getAdresse());
        smallTextLabel.setStyle("-fx-text-fill: #555555;");

        GridPane datesGrid = new GridPane();
        datesGrid.setHgap(20);
        dateArriveePicker = new DatePicker();
        dateDepartPicker = new DatePicker();
        datesGrid.add(new Label("Date d'arrivée"), 0, 0);
        datesGrid.add(dateArriveePicker, 0, 1);
        datesGrid.add(new Label("Date de départ"), 1, 0);
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
        mainContainer.getChildren().add(topSection);

        // Section Options
        VBox optionsSection = new VBox(10);
        optionsSection.setAlignment(Pos.CENTER);
        Label optionsTitle = new Label("Options");
        optionsTitle.setFont(new Font(20));
        FlowPane optionsFlow = new FlowPane(20, 20);
        optionsFlow.setPrefWrapLength(900);
        optionsFlow.setAlignment(Pos.CENTER);
        if (optionsList == null || optionsList.isEmpty()) {
            Label noOptions = new Label("Aucune option disponible pour cet hébergement.");
            noOptions.setStyle("-fx-font-style: italic; -fx-text-fill: #888;");
            optionsFlow.getChildren().add(noOptions);
        } else {
            for (Options opt : optionsList) {
                VBox card = new VBox(5);
                card.setPadding(new Insets(10));
                card.setStyle("-fx-background-color: #FAFAFA; -fx-border-color: #E5E5E5;");
                Label name = new Label(opt.getNom()); name.setStyle("-fx-font-weight: bold;");
                Label desc = new Label(opt.getDescription()); desc.setWrapText(true);
                card.getChildren().addAll(name, desc);
                optionsFlow.getChildren().add(card);
            }
        }
        optionsSection.getChildren().addAll(optionsTitle, optionsFlow);
        mainContainer.getChildren().add(optionsSection);

        // Section Avis
        VBox avisSection = new VBox(10);
        avisSection.setAlignment(Pos.CENTER);
        avisTitle = new Label("Avis"); avisTitle.setFont(new Font(20));
        avisSubtitle = new Label("Ce qu'en pensent les utilisateurs"); avisSubtitle.setStyle("-fx-text-fill: #555;");
        FlowPane avisFlow = new FlowPane(20, 20);
        avisFlow.setPrefWrapLength(900);
        avisFlow.setAlignment(Pos.CENTER);
        if (avisList.isEmpty()) {
            Label none = new Label("Aucun avis pour cet hébergement."); none.setStyle("-fx-font-style: italic; -fx-text-fill: #888;");
            avisFlow.getChildren().add(none);
        } else {
            for (Avis a : avisList) {
                VBox c = new VBox(5); c.setPadding(new Insets(10));
                c.setStyle("-fx-background-color: #FAFAFA; -fx-border-color: #E5E5E5;");
                Label note = new Label("Note : " + a.getNote() + "/5"); note.setStyle("-fx-font-weight: bold;");
                Label com = new Label(a.getCommentaire()); com.setWrapText(true);
                c.getChildren().addAll(note, com);
                avisFlow.getChildren().add(c);
            }
        }
        avisSection.getChildren().addAll(avisTitle, avisSubtitle, avisFlow);
        mainContainer.getChildren().add(avisSection);

        scrollPane.setContent(mainContainer);
        root.setCenter(scrollPane);
        scene = new Scene(root, 1200, 900);
    }

    // GETTERS
    public Scene getScene() { return scene; }
    public NavBarView getNavBarView() { return navBarView; }
    public Button getBackButton() { return backButton; }
    public Button getFavoriteButton() { return favoriteButton; }
    public Button getReserverButton() { return reserverButton; }
    public DatePicker getDateArriveePicker() { return dateArriveePicker; }
    public DatePicker getDateDepartPicker() { return dateDepartPicker; }
}
