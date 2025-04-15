package controller;

import javafx.stage.Stage;
import view.SearchPageView;

public class SearchPageController {

    private Stage primaryStage;
    private SearchPageView view;

    public SearchPageController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new SearchPageView();
        attachEventHandlers();
        initSearchInteractions();
    }

    private void attachEventHandlers() {
        // -- Navigation via NavBar
        view.getNavBarView().getTitleLabel().setOnMouseClicked(e -> new HomePageController(primaryStage).show());
        view.getNavBarView().getSignInLabel().setOnMouseClicked(e -> new LoginPageController(primaryStage).show());
        view.getNavBarView().getRegisterLabel().setOnMouseClicked(e -> new RegisterPageController(primaryStage).show());
        view.getNavBarView().getRechercheLabel().setOnMouseClicked(e -> this.show());

        // -- Bouton DEBUG PRODUIT en haut (redirect vers BookingPage)
        view.getDebugProductButton().setOnAction(e -> {
            BookingPageController bookingCtrl = new BookingPageController(primaryStage);
            bookingCtrl.show();
        });
    }

    private void initSearchInteractions() {
        view.getMaisonCheck().setOnAction(e -> updateResults());
        view.getAppartementCheck().setOnAction(e -> updateResults());
        view.getAutreCheck().setOnAction(e -> updateResults());
        view.getPrixSlider().valueProperty().addListener((obs, oldVal, newVal) -> updateResults());
        view.getPersonnesSpinner().valueProperty().addListener((obs, oldVal, newVal) -> updateResults());
        view.getNuitsSpinner().valueProperty().addListener((obs, oldVal, newVal) -> updateResults());
        view.getDateArriveePicker().valueProperty().addListener((obs, oldVal, newVal) -> updateResults());

        view.getSearchField().setOnAction(e -> updateResults());
        view.getSortPriceButton().setOnAction(e -> updateResults());
        view.getSortRatingButton().setOnAction(e -> updateResults());
    }

    private void updateResults() {
        System.out.println("Mise à jour des résultats selon les filtres, tri, recherche...");
        // TODO : Implémenter la logique de filtrage
    }

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
