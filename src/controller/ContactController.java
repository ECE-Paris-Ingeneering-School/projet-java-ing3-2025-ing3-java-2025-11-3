// src/main/java/controller/ContactController.java
package controller;

import javafx.stage.Stage;
import view.ContactView;
import controller.AdminController;    // ← Ajout de cet import !

public class ContactController {

    private final Stage primaryStage;
    private final ContactView view;

    public ContactController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new ContactView();

        new NavBarController(primaryStage, view.getNavBarView());
    }

    public void show() {
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
