package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.Objects;

public class HomePageView {

    private Scene scene;
    private TextField searchField;
    private NavBarView navBarView;

    public HomePageView() {
        createUI();
    }

    private void createUI() {
        // Crée la NavBar
        navBarView = new NavBarView();
        HBox navBar = navBarView.getNavBar();

        // Zone centrale
        VBox centerBox = new VBox(30);
        centerBox.setAlignment(Pos.CENTER);

        Label mainMessage = new Label("Réservez l’hébergement de vos rêves\nen quelques clics");
        mainMessage.setFont(new Font("Arial", 28));
        mainMessage.setStyle("-fx-font-weight: bold; -fx-text-fill: #000000;");
        mainMessage.setWrapText(true);
        mainMessage.setTextAlignment(TextAlignment.CENTER);

        // Barre de recherche
        HBox searchContainer = new HBox(10);
        searchContainer.setAlignment(Pos.CENTER);
        searchContainer.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: #CCCCCC;" +
                        "-fx-border-radius: 25;" +
                        "-fx-background-radius: 25;" +
                        "-fx-padding: 8;"
        );

        searchField = new TextField();
        searchField.setPromptText("Recherche");

        // Charger l'image loupe (vérifie que /images/loupe.png existe dans resources)


        searchContainer.setPrefWidth(400);
        searchContainer.setMaxWidth(400);


        centerBox.getChildren().addAll(mainMessage, searchContainer);

        BorderPane root = new BorderPane();
        root.setTop(navBar);
        root.setCenter(centerBox);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #ffffff, #ffffff);");

        scene = new Scene(root, 1000, 600);

        // Responsive
        searchContainer.prefWidthProperty().bind(scene.widthProperty().multiply(0.4));
        searchField.prefWidthProperty().bind(searchContainer.widthProperty().subtract(40));
    }

    public Scene getScene() {
        return scene;
    }

    public TextField getSearchField() {
        return searchField;
    }

    public NavBarView getNavBarView() {
        return navBarView;
    }
}
