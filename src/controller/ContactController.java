package controller;

import javafx.stage.Stage;
import view.ContactView;

public class ContactController {

    private final Stage primaryStage;
    private final ContactView view;

    public ContactController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new ContactView();
        // Rattache la nav-bar pour que les clics y fonctionnent
        new NavBarController(primaryStage, view.getNavBarView());
    }

    public void show() {
        // Conserve taille et plein écran
        boolean fullScreen = primaryStage.isFullScreen();
        double width  = primaryStage.getWidth();
        double height = primaryStage.getHeight();

        primaryStage.setScene(view.getScene());
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setFullScreen(fullScreen);
        primaryStage.show();
    }
}
