package controller;

import javafx.stage.Stage;
import view.*;
import javafx.scene.layout.Region;


public class AdminController {

    private final Stage primaryStage;
    private final AdminView view;

    public AdminController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new AdminView();

        // liaisons menu → affichage du stub correspondant
        view.getLogementsLabel().setOnMouseClicked(e -> showSection(new HebergementListView().getRoot()));
        view.getUtilisateursLabel().setOnMouseClicked(e -> showSection(new UsersListView().getRoot()));
        view.getReservationsPasseesLabel().setOnMouseClicked(e -> showSection(new ReservationHistoryView().getRoot()));
        view.getReservationsUtilisateurLabel().setOnMouseClicked(e -> showSection(new UserReservationsView().getRoot()));
        view.getAjouterLogementLabel().setOnMouseClicked(e -> showSection(new AddHebergementView().getRoot()));
        view.getSupprimerLogementLabel().setOnMouseClicked(e -> showSection(new RemoveHebergementView().getRoot()));

        // lieu de retour vers la homepage
        view.getRetourButton().setOnAction(e -> new HomePageController(primaryStage).show());
    }

    public void show() {
        primaryStage.setScene(view.getScene());
        primaryStage.show();
        // par défaut on charge la liste des logements
        showSection(new HebergementListView().getRoot());
    }

    private void showSection(Region sectionRoot) {
        view.getContentPane().getChildren().setAll(sectionRoot);
    }
}
