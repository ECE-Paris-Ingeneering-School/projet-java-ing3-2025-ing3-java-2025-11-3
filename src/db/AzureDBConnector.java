package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AzureDBConnector {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DatabaseConfig.getUrl(),
                DatabaseConfig.getUsername(),
                DatabaseConfig.getPassword()
        );
    }
}