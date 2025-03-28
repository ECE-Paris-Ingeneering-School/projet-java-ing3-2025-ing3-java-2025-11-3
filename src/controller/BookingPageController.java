package controller;

import javafx.stage.Stage;
import view.BookingPageView;

public class BookingPageController {

    private Stage primaryStage;
    private BookingPageView view;

    public BookingPageController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new BookingPageView();
        initController();
    }

    private void initController() {
        // Navigation NavBar
        view.getNavBarView().getTitleLabel().setOnMouseClicked(e -> new HomePageController(primaryStage).show());
        view.getNavBarView().getRechercheLabel().setOnMouseClicked(e -> new SearchPageController(primaryStage).show());
        // etc...

        // Bouton "Réserver"
        view.getReserverButton().setOnAction(e -> {
            System.out.println("Réservation en cours...");
            // TODO: Logique de réservation
        });
    }

    public void show() {
        primaryStage.setScene(view.getScene());
        primaryStage.show();
    }
}
