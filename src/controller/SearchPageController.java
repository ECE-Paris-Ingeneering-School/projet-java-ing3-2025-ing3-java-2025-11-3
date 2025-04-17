package controller;

import Dao.HebergementDao;
import Dao.HebergementDaoImpl;
import MODELE.Hebergement;
import db.AzureDBConnector;
import view.SearchPageView;
import javafx.stage.Stage;
import view.SearchPageView;
import view.ReservationView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SearchPageController {

    private final Stage primaryStage;
    private final SearchPageView view;
    private final HebergementDao dao;

    public SearchPageController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new SearchPageView();
        this.dao = new HebergementDaoImpl(new AzureDBConnector());

        attachEventHandlers();
        initSearchInteractions();
        updateResults();
    }

    private void attachEventHandlers() {
        view.getNavBarView().getTitleLabel().setOnMouseClicked(e -> new HomePageController(primaryStage).show());
        view.getNavBarView().getSignInLabel().setOnMouseClicked(e -> new LoginPageController(primaryStage).show());
        view.getNavBarView().getRegisterLabel().setOnMouseClicked(e -> new RegisterPageController(primaryStage).show());
        view.getNavBarView().getRechercheLabel().setOnMouseClicked(e -> this.show());
        view.getNavBarView().getReservationsLabel().setOnMouseClicked(e -> {
            ReservationView reservationView = new ReservationView();
            new ReservationController(primaryStage, reservationView);
            primaryStage.setScene(reservationView.getScene());
        });



        //view.getDebugProductButton().setOnAction(e -> new BookingPageController(primaryStage).show());
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
        view.getLodgingFlowPane().getChildren().clear();

        ArrayList<Hebergement> hebergements = dao.getAllHebergements();

        boolean filtreMaison = view.getMaisonCheck().isSelected();
        boolean filtreAppart = view.getAppartementCheck().isSelected();
        boolean filtreAutre = view.getAutreCheck().isSelected();
        boolean anyTypeFilter = filtreMaison || filtreAppart || filtreAutre;

        int prixMax = (int) view.getPrixSlider().getValue();
        String rechercheTexte = view.getSearchField().getText().toLowerCase();

        hebergements.removeIf(h -> {
            boolean typeOk = true;
            if (anyTypeFilter) {
                typeOk = (filtreMaison && h.getType() == 1)
                        || (filtreAppart && h.getType() == 2)
                        || (filtreAutre && h.getType() != 1 && h.getType() != 2);
            }

            boolean prixOk = h.getPrix() <= prixMax;

            boolean rechercheOk = rechercheTexte.isEmpty()
                    || h.getNom().toLowerCase().contains(rechercheTexte);

            return !(typeOk && prixOk && rechercheOk);
        });

        if (view.getSortPriceButton().isFocused()) {
            hebergements.sort((a, b) -> Integer.compare(a.getPrix(), b.getPrix()));
        } else if (view.getSortRatingButton().isFocused()) {
            hebergements.sort((a, b) -> Integer.compare(b.getNote(), a.getNote()));
        }

        for (Hebergement h : hebergements) {
            VBox lodgingItem = view.createLodgingItem(h);
            lodgingItem.setOnMouseClicked(e -> new BookingPageController(primaryStage, h).show());
            view.getLodgingFlowPane().getChildren().add(lodgingItem);
        }
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