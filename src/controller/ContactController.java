// src/main/java/controller/ContactController.java
package controller;

import javafx.stage.Stage;
import view.ContactView;
import controller.AdminController;    // ← Ajout de cet import !

/**
 * Contrôleur pour la page de contact.
 */
public class ContactController {

    private final Stage primaryStage;
    private final ContactView view;

    /**
     * Constructeur du contrôleur ContactController.
     * Initialise la vue de contact et la barre de navigation.
     *
     * @param primaryStage La fenêtre principale de l'application.
     */
    public ContactController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new ContactView();

        new NavBarController(primaryStage, view.getNavBarView());
    }

    /**
     * Affiche la vue de contact en conservant les dimensions et l'état de la fenêtre.
     */
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
