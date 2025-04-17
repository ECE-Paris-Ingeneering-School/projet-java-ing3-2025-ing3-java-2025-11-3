import controller.HomePageController;
import controller.LoginPageController;
import javafx.application.Application;
import javafx.stage.Stage;

public class MyJavaFXApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Booking App");
        // Instanciation et lancement de la HomePage via son contrôleur
        HomePageController homePageController = new HomePageController(primaryStage);
        homePageController.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
