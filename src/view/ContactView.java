package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ContactView {

    private final Scene scene;
    private final BorderPane root;
    private final NavBarView navBarView;
    private final Button debugAdminButton;

    public ContactView() {
        // racine
        root = new BorderPane();

        // navbar en haut
        navBarView = new NavBarView();
        root.setTop(navBarView.getNavBar());

        // contenu central
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

        // bouton debug pour accéder à la vue admin
        debugAdminButton = new Button("⚙️ Debug Admin");
        // (on attache l'action dans le controller)

        content.getChildren().addAll(title, text, debugAdminButton);
        root.setCenter(content);

        scene = new Scene(root, 900, 600);
    }

    public Scene getScene() {
        return scene;
    }

    public NavBarView getNavBarView() {
        return navBarView;
    }


    public Button getDebugAdminButton() {
        return debugAdminButton;
    }
}
