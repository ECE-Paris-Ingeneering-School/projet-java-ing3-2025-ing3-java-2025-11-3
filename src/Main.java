import db.AzureDBConnector;
import javafx.application.Application;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Test de la connexion à la base Azure SQL
        AzureDBConnector azureDBConnector = AzureDBConnector.getInstance();
        try (Connection conn = AzureDBConnector.getConnection()) {
            System.out.println("✅ Connexion établie à la base Azure SQL !");
        } catch (SQLException e) {
            System.err.println("❌ Erreur de connexion : " + e.getMessage());
        }

        // Lancement de l'interface JavaFX
        Application.launch(MyJavaFXApp.class, args);
    }
}
