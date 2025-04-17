import db.AzureDBConnector;
import javafx.application.Application;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Test de la connexion à la base Azure SQL


        // Lancement de l'interface JavaFX
        Application.launch(MyJavaFXApp.class, args);
    }
}
