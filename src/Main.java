
import db.AzureDBConnector;
import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        try (Connection conn = AzureDBConnector.getConnection()) {
            System.out.println("✅ Connexion établie à la base Azure SQL !");
        } catch (SQLException e) {
            System.err.println("❌ Erreur de connexion : " + e.getMessage());
        }
    }
}   