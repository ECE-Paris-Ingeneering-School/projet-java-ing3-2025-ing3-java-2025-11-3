package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ReservationView {

    private Scene scene;
    private NavBarView navBarView;

    public ReservationView() {
        createUI();
    }

    private void createUI() {
        // Barre de navigation (similaire à RegisterPageView)
        navBarView = new NavBarView();
        HBox navBar = navBarView.getNavBar();

        // Conteneur principal pour centrer le contenu
        VBox mainContainer = new VBox();
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setSpacing(20);
        mainContainer.setPadding(new Insets(20));

        // Titre de la page
        Label titleLabel = new Label("Mes réservations");
        titleLabel.setFont(new Font("Arial", 28));
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #333333;");

        mainContainer.getChildren().add(titleLabel);

        // Layout principal avec BorderPane
        BorderPane root = new BorderPane();
        root.setTop(navBar);
        root.setCenter(mainContainer);
        root.setStyle("-fx-background-color: #F9F9F9;");

        scene = new Scene(root, 800, 600);
    }

    public Scene getScene() {
        return scene;
    }

    public NavBarView getNavBarView() {
        return navBarView;
    }
}
