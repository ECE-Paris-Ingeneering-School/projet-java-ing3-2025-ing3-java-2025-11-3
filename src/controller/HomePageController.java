package controller;

import view.HomePageView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

/**
 * Contrôleur de la page d'accueil.
 * Gère la logique et les événements pour la HomePageView.
 */
public class HomePageController {

    private Stage primaryStage;
    private HomePageView view;

    public HomePageController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new HomePageView();
        attachEvents();
    }

    private void attachEvents() {
        // Exemple : attacher un événement sur un bouton
        // si HomePageView avait un bouton.
        // view.getMonBouton().setOnAction((ActionEvent e) -> {
        //     System.out.println("Bouton cliqué !");
        // });
    }

    public void show() {
        primaryStage.setScene(view.getScene());
        primaryStage.show();
    }
}
