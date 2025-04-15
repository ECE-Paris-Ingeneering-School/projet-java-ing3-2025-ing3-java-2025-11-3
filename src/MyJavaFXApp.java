import controller.HomePageController;
import javafx.application.Application;
import javafx.stage.Stage;

public class MyJavaFXApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Booking App");
        // Instanciation et lancement de la HomePage via son contrôleur
        HomePageController homeController = new HomePageController(primaryStage);
        homeController.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
