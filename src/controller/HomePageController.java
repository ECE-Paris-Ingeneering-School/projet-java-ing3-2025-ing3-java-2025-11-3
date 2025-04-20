package controller;

import view.HomePageView;
import view.NavBarView;
import javafx.stage.Stage;

/**
 * Controller for the home page, managing navigation and search from the navbar.
 */
public class HomePageController {

    private final Stage primaryStage;
    private final HomePageView view;

    /**
     * @param primaryStage the main application window
     */
    public HomePageController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new HomePageView();
        configureEventHandlers();
    }

    /**
     * Binds UI events to navigation actions.
     */
    private void configureEventHandlers() {
        NavBarView nav = view.getNavBarView();

        nav.getSignInLabel().setOnMouseClicked(e -> navigate(() -> new LoginPageController(primaryStage).show()));
        nav.getReservationsLabel().setOnMouseClicked(e -> navigate(() -> new ReservationController(primaryStage).show()));
        nav.getTitleLabel().setOnMouseClicked(e -> navigate(this::show));
        nav.getRechercheLabel().setOnMouseClicked(e -> navigate(() -> new SearchPageController(primaryStage).show()));

        view.getSearchField().setOnAction(e -> {
            String query = view.getSearchField().getText().trim();
            if (!query.isEmpty()) {
                navigate(() -> new SearchPageController(primaryStage, query).show());
            }
        });
    }

    /**
     * Centralizes window-state preservation and navigation.
     *
     * @param showAction the action that will set and show the new scene
     */
    private void navigate(Runnable showAction) {
        boolean wasFullScreen = primaryStage.isFullScreen();
        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();

        showAction.run();

        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setFullScreen(wasFullScreen);
    }

    /**
     * Displays the home page scene.
     */
    public void show() {
        primaryStage.setScene(view.getScene());
        primaryStage.show();
    }
}
