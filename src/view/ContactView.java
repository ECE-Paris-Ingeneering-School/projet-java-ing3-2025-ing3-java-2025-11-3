package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.InputStream;

public class ContactView {

    private final Scene scene;
    private final BorderPane root;
    private final NavBarView navBarView;

    public ContactView() {
        // Construction de la vue
        root = new BorderPane();

        // NavBar en haut
        navBarView = new NavBarView();
        root.setTop(navBarView.getNavBar());

        // Contenu central
        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Contact");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");


        Label text = new Label(
                "Pour toute question ou assistance,\n" +
                        "écrivez-nous à : support@booking.com\n" +
                        "ou appelez : +33 1 23 45 67 89"
        );
        text.setStyle("-fx-font-size: 14px;");
        text.setWrapText(true);
        text.setMaxWidth(400);
        text.setAlignment(Pos.CENTER);

        content.getChildren().addAll(title, text);
        root.setCenter(content);

        scene = new Scene(root, 900, 600);
    }

    public Scene getScene() {
        return scene;
    }

    public NavBarView getNavBarView() {
        return navBarView;
    }
}
