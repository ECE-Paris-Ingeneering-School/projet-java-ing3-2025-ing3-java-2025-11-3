package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Dao.AdminDaoImpl;
import Dao.ClientDaoImpl;
import Dao.HebergementDaoImpl;
import Dao.UserDaoImpl;


public class AzureDBConnector {
    /**
     * Attributs private pour la connexion JDBC
     */
    private static    String url;
    private static String username;
    private static String password;

    // constructeur
    public AzureDBConnector() {
        url = DatabaseConfig.getUrl();
        username = DatabaseConfig.getUsername();
        password = DatabaseConfig.getPassword();
    }

    /**
     * Méthode qui retourne 1 objet de DaoFactory
     *
     *
     * @return : objet de la classe DaoFactoru
     */
    public static AzureDBConnector getInstance( ) {
        try {
            // chargement driver "com.mysql.cj.jdbc.Driver"
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de connexion à la base de données");
        }




        // Instancier une instance l'objet de DaoFactory

        // Retourner cette instance
        return new AzureDBConnector();
    }

    /**
     * Méthode qui retourne le driver de base de données approprié
     *
     * @return : le driver approprié
     * @throws SQLException : exception SQL
     */
    public static Connection getConnection() throws SQLException {
        // Retourner la connection du driver de la base de données
        return DriverManager.getConnection(url, username, password);
    }

    public void closeConnection() throws SQLException {
        Connection connection = getConnection();
        if (connection != null) {
            connection.close();
        }
    }

    public UserDaoImpl getUserDao () {
        return new UserDaoImpl(this);
    }
    public ClientDaoImpl getClientDao () { return  new ClientDaoImpl(this); }
    public AdminDaoImpl getAdminDao () { return  new AdminDaoImpl(this); }
    public HebergementDaoImpl getHebergementDao () { return  new HebergementDaoImpl(this); }



}