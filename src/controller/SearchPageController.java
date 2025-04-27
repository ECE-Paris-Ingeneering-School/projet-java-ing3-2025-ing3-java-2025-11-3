package controller;

import dao.HebergementDao;
import dao.HebergementDaoImpl;
import db.AzureDBConnector;
import javafx.geometry.Pos;
import modele.Hebergement;
import view.SearchPageView;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;

/**
 * Controller for the search page, handling user interactions and data loading.
 */
public class SearchPageController {

    private final Stage primaryStage;
    private final SearchPageView view;
    private final HebergementDao dao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * Creates a SearchPageController with no initial query.
     *
     * @param primaryStage the main application stage
     */
    public SearchPageController(Stage primaryStage) {
        this(primaryStage, null);
    }

    /**
     * Creates a SearchPageController with an optional initial search query.
     *
     * @param primaryStage the main application stage
     * @param initialQuery prefilled search text (may be null)
     */
    public SearchPageController(Stage primaryStage, String initialQuery) {
        this.primaryStage = primaryStage;
        this.view = new SearchPageView();
        this.dao = new HebergementDaoImpl(new AzureDBConnector());

        if (initialQuery != null && !initialQuery.isBlank()) {
            view.getSearchField().setText(initialQuery);
        }

        configureEventHandlers();
        updateResults();
    }

    /**
     * Binds UI events to controller actions.
     */
    private void configureEventHandlers() {
        new NavBarController(primaryStage, view.getNavBarView());

        // Filters and search triggers
        view.getMaisonCheck().setOnAction(e -> updateResults());
        view.getAppartementCheck().setOnAction(e -> updateResults());
        view.getCampingCheck().setOnAction(e -> updateResults());
        view.getHotelCheck().setOnAction(e -> updateResults());
        view.getAubergeCheck().setOnAction(e -> updateResults());
        view.getAutreCheck().setOnAction(e -> updateResults());
        view.getPrixSlider().valueProperty().addListener((obs, oldVal, newVal) -> updateResults());
        view.getDateArriveePicker().valueProperty().addListener((obs, oldVal, newVal) -> updateResults());

        view.getSearchField().setOnAction(e -> updateResults());
        view.getSortPriceButton().setOnAction(e -> updateResults());
        view.getSortRatingButton().setOnAction(e -> updateResults());
    }

    /**
     * Clears previous results, shows a loader, and fetches filtered accommodations asynchronously.
     */
    private void updateResults() {
        ProgressIndicator loader = new ProgressIndicator();
        var pane = view.getLodgingFlowPane();
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().setAll(loader);

        Task<List<Hebergement>> searchTask = new Task<>() {
            @Override
            protected List<Hebergement> call() throws Exception {
                return fetchFilteredHebergements();
            }

            @Override
            protected void succeeded() {
                displayResults(getValue());
            }

            @Override
            protected void failed() {
                System.err.println("Erreur lors du chargement des données : " + getException());
            }
        };

        executor.submit(searchTask);
    }

    /**
     * Retrieves and sorts the list of accommodations based on UI filters and sort buttons.
     *
     * @return a list of filtered and sorted Hebergement objects
     */
    public List<Hebergement> fetchFilteredHebergements() {
        List<String> types = new ArrayList<>();
        if (view.getMaisonCheck().isSelected())   types.add("MAISON");
        if (view.getAppartementCheck().isSelected()) types.add("APPARTEMENT");
        if (view.getCampingCheck().isSelected())  types.add("CAMPING");
        if (view.getHotelCheck().isSelected())    types.add("HOTEL");
        if (view.getAubergeCheck().isSelected())  types.add("AUBERGE");
        if (view.getAutreCheck().isSelected())    types.add("AUTRE");

        int prixMax = (int) view.getPrixSlider().getValue();
        String recherche = view.getSearchField().getText().toLowerCase().trim();

        List<Hebergement> result = dao.getFilteredHebergements(types, prixMax, recherche);

        if (view.getSortPriceButton().isFocused()) {
            result.sort(Comparator.comparingInt(Hebergement::getPrix));
        } else if (view.getSortRatingButton().isFocused()) {
            result.sort(Comparator.comparingInt(Hebergement::getNote).reversed());
        }
        return result;
    }


    /**
     * Populates the view with the provided list of accommodations.
     *
     * @param hebergements list of accommodations to display
     */
    private void displayResults(List<Hebergement> hebergements) {
        var pane = view.getLodgingFlowPane();
        pane.setAlignment(Pos.TOP_LEFT);
        pane.getChildren().clear();
        hebergements.forEach(h -> {
            VBox item = view.createLodgingItem(h);
            item.setOnMouseClicked(e -> new BookingPageController(primaryStage, h));
            pane.getChildren().add(item);
        });
    }

    /**
     * Displays the search page scene, preserving window dimensions and full-screen state.
     */
    public void show() {
        boolean fullScreen = primaryStage.isFullScreen();
        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();

        primaryStage.setScene(view.getScene());
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setFullScreen(fullScreen);
        primaryStage.show();
    }
}
