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

/**
 * Vue représentant la page d'accueil de l'application.
 * Contient un message principal, une barre de recherche et une image d'illustration.
 */
public class HomePageView {

    private Scene scene;
    private TextField searchField;
    private NavBarView navBarView;

    /**
     * Construit la page d'accueil et initialise son interface graphique.
     */
    public HomePageView() {
        createUI();
    }

    /**
     * Crée tous les éléments graphiques de la page d'accueil
     * et configure leur disposition.
     */
    private void createUI() {
        navBarView = new NavBarView();
        HBox navBar = navBarView.getNavBar();

        VBox centerBox = new VBox(30);
        centerBox.setAlignment(Pos.CENTER);

        Label mainMessage = new Label("Réservez l’hébergement de vos rêves\nen quelques clics");
        mainMessage.setFont(new Font("Arial", 28));
        mainMessage.setStyle("-fx-font-weight: bold; -fx-text-fill: #000000;");
        mainMessage.setWrapText(true);
        mainMessage.setTextAlignment(TextAlignment.CENTER);

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
        searchContainer.getChildren().add(searchField);
        searchContainer.setPrefWidth(400);
        searchContainer.setMaxWidth(400);

        Image image = new Image("file:src/resources/homepage.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        centerBox.getChildren().addAll(mainMessage, searchContainer, imageView);

        BorderPane root = new BorderPane();
        root.setTop(navBar);
        root.setCenter(centerBox);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #ffffff, #ffffff);");

        scene = new Scene(root, 1000, 600);

        searchContainer.prefWidthProperty().bind(scene.widthProperty().multiply(0.4));
        searchField.prefWidthProperty().bind(searchContainer.widthProperty().subtract(40));
    }

    /**
     * Retourne la scène principale de la page d'accueil.
     *
     * @return La scène contenant l'ensemble de la vue.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Retourne le champ de recherche.
     *
     * @return Champ de texte permettant la recherche.
     */
    public TextField getSearchField() {
        return searchField;
    }

    /**
     * Retourne la barre de navigation associée à la page.
     *
     * @return Instance de NavBarView.
     */
    public NavBarView getNavBarView() {
        return navBarView;
    }
}
