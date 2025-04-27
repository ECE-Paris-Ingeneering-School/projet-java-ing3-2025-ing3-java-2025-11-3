package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import modele.Hebergement;
import java.time.LocalDate;

public class SearchPageView {

    private Scene scene;
    private BorderPane root;
    private NavBarView navBarView;

    private VBox filtersBox;
    private VBox rightContainer;
    private HBox searchRow;
    private TextField searchField;
    private Button sortPriceButton, sortRatingButton;
    private FlowPane lodgingFlowPane;

    private CheckBox hotelCheck, aubergeCheck,campingCheck ,maisonCheck, appartementCheck, autreCheck;
    private Slider prixSlider;
    private DatePicker dateArriveePicker,dateDepartePicker;

    public SearchPageView() {
        createUI();
    }

    private void createUI() {
        // racine et barre de navigation
        root = new BorderPane();
        navBarView = new NavBarView();
        root.setTop(navBarView.getNavBar());

        // -- Zone de filtres à gauche
        filtersBox = new VBox(10);
        filtersBox.setPadding(new Insets(15));
        filtersBox.setStyle("-fx-background-color: #F8F8F8;");
        filtersBox.setPrefWidth(250);
        filtersBox.setAlignment(Pos.TOP_LEFT);


        hotelCheck = new CheckBox("Hôtel");
        aubergeCheck = new CheckBox("Auberge");
        campingCheck = new CheckBox("Camping");
        maisonCheck = new CheckBox("Maison");
        appartementCheck = new CheckBox("Appartement");
        autreCheck = new CheckBox("Autre");
        prixSlider = new Slider(0, 500, 100);
        dateArriveePicker = new DatePicker(LocalDate.now());
        dateDepartePicker = new DatePicker();

        maisonCheck.setSelected(false);
        appartementCheck.setSelected(false);
        campingCheck.setSelected(false);
        hotelCheck.setSelected(false);
        autreCheck.setSelected(false);
        prixSlider.setValue(500);

        filtersBox.getChildren().addAll(
                new Label("Filtres"),
                maisonCheck,campingCheck,hotelCheck,aubergeCheck, appartementCheck, autreCheck,
                new Label("Prix max:"), prixSlider,
                new Label("Arrivée:"), dateArriveePicker,
                new Label("Départ:"), dateDepartePicker
        );

        // -- Zone de droite (recherche + tri + logements)
        rightContainer = new VBox(20);
        rightContainer.setPadding(new Insets(15));
        rightContainer.setAlignment(Pos.TOP_LEFT);
        VBox.setVgrow(rightContainer, Priority.ALWAYS);

        // Ligne de recherche et de tri
        searchRow = new HBox(10);
        searchRow.setAlignment(Pos.CENTER_LEFT);
        searchField = new TextField();
        searchField.setPromptText("Rechercher...");
        searchField.setPrefWidth(300);
        sortPriceButton = new Button("Trier par prix");
        sortRatingButton = new Button("Trier par note");
        searchRow.getChildren().addAll(searchField, sortPriceButton, sortRatingButton);

        // FlowPane pour afficher les logements
        lodgingFlowPane = new FlowPane();
        lodgingFlowPane.setHgap(20);
        lodgingFlowPane.setVgap(20);
        lodgingFlowPane.setPadding(new Insets(10));
        lodgingFlowPane.setAlignment(Pos.TOP_LEFT);
        lodgingFlowPane.setPrefWrapLength(1000);
        VBox.setVgrow(lodgingFlowPane, Priority.ALWAYS);

        rightContainer.getChildren().addAll(searchRow, lodgingFlowPane);

        // -- Disposition principale
        HBox mainContent = new HBox(30);
        mainContent.getChildren().addAll(filtersBox, rightContainer);
        HBox.setHgrow(rightContainer, Priority.ALWAYS);

        // ** On encapsule mainContent dans un ScrollPane pour le défilement vertical **
        ScrollPane contentScrollPane = new ScrollPane(mainContent);
        contentScrollPane.setFitToWidth(true);
        contentScrollPane.setFitToHeight(true);
        contentScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        contentScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        root.setCenter(contentScrollPane);

        // création de la scène
        scene = new Scene(root, 1200, 800);
    }

    public VBox createLodgingItem(Hebergement hebergement) {
        VBox box = new VBox(5);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-border-color: #CCC; -fx-background-color: #FAFAFA;");

        ImageView iv = new ImageView(new Image("file:src/resources/" + hebergement.getImageFilename()));
        iv.setFitWidth(200);
        iv.setFitHeight(250);

        box.getChildren().addAll(iv, new Label(hebergement.getNom()));
        return box;
    }

    public Scene getScene() {
        return scene;
    }

    public NavBarView getNavBarView() {
        return navBarView;
    }

    // -- Getters pour les contrôles de recherche et de tri
    public TextField getSearchField() {
        return searchField;
    }

    public Button getSortPriceButton() {
        return sortPriceButton;
    }

    public Button getSortRatingButton() {
        return sortRatingButton;
    }

    // -- Getters pour les filtres
    public CheckBox getMaisonCheck() {
        return maisonCheck;
    }

    public CheckBox getAppartementCheck() {
        return appartementCheck;
    }

    public CheckBox getCampingCheck() {
        return campingCheck;
    }

    public CheckBox getHotelCheck() {
        return hotelCheck;
    }

    public CheckBox getAubergeCheck() {
        return aubergeCheck;
    }

    public CheckBox getAutreCheck() {
        return autreCheck;
    }

    public Slider getPrixSlider() {
        return prixSlider;
    }

    public DatePicker getDateArriveePicker() {
        return dateArriveePicker;
    }

    public DatePicker getDateDepartPicker() {return dateDepartePicker;}

    public FlowPane getLodgingFlowPane() {
        return lodgingFlowPane;
    }
}
