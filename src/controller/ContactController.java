// src/main/java/controller/ContactController.java
package controller;

import javafx.stage.Stage;
import view.ContactView;
import view.ReservationView;

public class ContactController {

    private Stage primaryStage;
    private ContactView view;

    public ContactController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new ContactView();
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        new NavBarController(primaryStage, view.getNavBarView());
    }

    public void show() {
        // préserver taille et plein écran
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
