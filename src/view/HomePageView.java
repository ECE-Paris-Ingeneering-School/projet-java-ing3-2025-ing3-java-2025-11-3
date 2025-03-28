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

        // Message principal
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
        // Ajoute la zone de saisie à la barre de recherche
        searchContainer.getChildren().add(searchField);
        searchContainer.setPrefWidth(400);
        searchContainer.setMaxWidth(400);

        // Chargement de l'image à afficher sous la barre de recherche
        // Remplacez le chemin "/images/monImage.png" par le chemin réel de votre image dans votre projet
        Image image = new Image("file:/Users/elishabajemon/IdeaProjects/projet-java-ing3-2025-ing3-java-2025-11-3/src/resources/homepage.png"); ImageView imageView = new ImageView(image); imageView.setFitWidth(300); // ajustez la taille selon vos besoins imageView.setPreserveRatio(true);
        imageView = new ImageView(image);
        imageView.setFitWidth(300); // ajustez la largeur de l'image si nécessaire
        imageView.setPreserveRatio(true);

        // Ajoute le message principal, la barre de recherche et l'image dans le conteneur central
        centerBox.getChildren().addAll(mainMessage, searchContainer, imageView);

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
