package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.time.LocalDate;
import MODELE.Hebergement;

public class SearchPageView {

    private Scene scene;
    private BorderPane root;
    private NavBarView navBarView;

    private VBox filtersBox;
    private VBox rightContainer;
    private HBox searchRow;
    private TextField searchField;
    private Button sortPriceButton, sortRatingButton;
    private Button debugProductButton;  // Bouton Debug en haut
    private FlowPane lodgingFlowPane;

    private CheckBox maisonCheck, appartementCheck, autreCheck;
    private Slider prixSlider;
    private Spinner<Integer> personnesSpinner, nuitsSpinner;
    private DatePicker dateArriveePicker;

    public SearchPageView() {
        createUI();
    }

    private void createUI() {
        root = new BorderPane();
        navBarView = new NavBarView();
        root.setTop(navBarView.getNavBar());

        // -- Zone de filtres à gauche
        filtersBox = new VBox(10);
        filtersBox.setPadding(new Insets(15));
        filtersBox.setStyle("-fx-background-color: #F8F8F8;");
        filtersBox.setPrefWidth(250);
        filtersBox.setAlignment(Pos.TOP_LEFT);

        maisonCheck = new CheckBox("Maison");
        appartementCheck = new CheckBox("Appartement");
        autreCheck = new CheckBox("Autre");
        prixSlider = new Slider(0, 500, 100);
        personnesSpinner = new Spinner<>(1, 10, 1);
        nuitsSpinner = new Spinner<>(1, 30, 1);
        dateArriveePicker = new DatePicker(LocalDate.now());

        filtersBox.getChildren().addAll(
                new Label("Filtres"),
                maisonCheck, appartementCheck, autreCheck,
                new Label("Prix max:"), prixSlider,
                new Label("Arrivée:"), dateArriveePicker,
                new Label("Personnes:"), personnesSpinner,
                new Label("Nuits:"), nuitsSpinner
        );

        // -- Zone de droite (barre de recherche + Tri + bouton debug + logements)
        rightContainer = new VBox(20);
        rightContainer.setPadding(new Insets(15));
        rightContainer.setAlignment(Pos.TOP_LEFT);
        VBox.setVgrow(rightContainer, Priority.ALWAYS);

        // -- Ligne recherche + tri + BOUTON DEBUG
        searchRow = new HBox(10);
        searchRow.setAlignment(Pos.CENTER_LEFT);

        searchField = new TextField();
        searchField.setPromptText("Rechercher...");
        searchField.setPrefWidth(300);

        sortPriceButton = new Button("Trier par prix");
        sortRatingButton = new Button("Trier par note");

        debugProductButton = new Button("DEBUG PRODUIT");
        debugProductButton.setStyle("-fx-background-color: #ffcccc; -fx-font-weight: bold;");

        searchRow.getChildren().addAll(searchField, sortPriceButton, sortRatingButton, debugProductButton);

        // -- FlowPane pour les logements
        lodgingFlowPane = new FlowPane();
        lodgingFlowPane.setHgap(20);
        lodgingFlowPane.setVgap(20);
        lodgingFlowPane.setPadding(new Insets(10));
        lodgingFlowPane.setAlignment(Pos.TOP_LEFT);
        lodgingFlowPane.setPrefWrapLength(1000);
        VBox.setVgrow(lodgingFlowPane, Priority.ALWAYS);

        // -- Ajout d'items de logements via le controleur
        /*for (int i = 0; i < 30; i++) {
            lodgingFlowPane.getChildren().add(createLodgingItem(i + 1));
        }*/

        rightContainer.getChildren().addAll(searchRow, lodgingFlowPane);

        // -- Disposition principale
        HBox mainContent = new HBox(30);
        mainContent.getChildren().addAll(filtersBox, rightContainer);
        HBox.setHgrow(rightContainer, Priority.ALWAYS);

        root.setCenter(mainContent);
        scene = new Scene(root, 1200, 800);
    }

    public VBox createLodgingItem(Hebergement hebergement) {
        System.out.println("Creating lodging item for: " + hebergement.getNom());

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

    // -- Getters pour la barre de recherche, le tri et le debug
    public TextField getSearchField() {
        return searchField;
    }

    public Button getSortPriceButton() {
        return sortPriceButton;
    }

    public Button getSortRatingButton() {
        return sortRatingButton;
    }

    public Button getDebugProductButton() {
        return debugProductButton;
    }

    // -- Getters pour les filtres
    public CheckBox getMaisonCheck() {
        return maisonCheck;
    }

    public CheckBox getAppartementCheck() {
        return appartementCheck;
    }

    public CheckBox getAutreCheck() {
        return autreCheck;
    }

    public Slider getPrixSlider() {
        return prixSlider;
    }

    public Spinner<Integer> getPersonnesSpinner() {
        return personnesSpinner;
    }

    public Spinner<Integer> getNuitsSpinner() {
        return nuitsSpinner;
    }

    public DatePicker getDateArriveePicker() {
        return dateArriveePicker;
    }

    public FlowPane getLodgingFlowPane() {
        return lodgingFlowPane;
    }
}
