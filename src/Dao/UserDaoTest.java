package Dao;
import MODELE.User;
import db.AzureDBConnector;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;

public class UserDaoTest {
    private UserDaoImpl userDao;

    @BeforeEach
    public void setUp() throws SQLException {
        // Initialize the connection to the database
        AzureDBConnector connector = AzureDBConnector.getInstance();
        try{
            Connection connection = connector.getConnection();

            // Initialize the UserDaoImpl with the connection
            userDao = new UserDaoImpl(connector);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    @Test
    void testajouterUser() throws SQLException {
        User user = new User("testNom", "testPrenom", "testEmail", "testPassword");
        userDao.ajouterUser(user);
        int userId = userDao.getIdUser("testEmail");

        assertNotEquals(-1, userId, "User should be added to the database");
        user.setId(userId);

        // Verify that the user was added correctly
        User retrievedUser = userDao.getUser(userId);


        assertEquals(user.getNom(), retrievedUser.getNom(), "User name should match");
        assertEquals(user.getPrenom(), retrievedUser.getPrenom(), "User first name should match");
        assertEquals(user.getEmail(), retrievedUser.getEmail(), "User email should match");


        // Clean up the test data
        userDao.supprimerUser(userId);

  }

  @Test
  void testmodifierUser() throws SQLException {
        User user = new User("testNom", "testPrenom", "testEmail", "testPassword");
        userDao.ajouterUser(user);
        int userId = userDao.getIdUser("testEmail");
        user.setId(userId);

        // Modify the user's information
        user.setNom("newNom");
        user.setPrenom("newPrenom");
        user.setEmail("newEmail");
        user.setPassword("newPassword");

        // Update the user in the database
        userDao.modifierUser(user);

        // Verify that the user's information was updated correctly
        User retrievedUser = userDao.getUser(userId);
        assertEquals(user.getNom(), retrievedUser.getNom(), "User name should match after update");
        assertEquals(user.getPrenom(), retrievedUser.getPrenom(), "User first name should match after update");
        assertEquals(user.getEmail(), retrievedUser.getEmail(), "User email should match after update");

        // Clean up the test data
        userDao.supprimerUser(userId);
  }




  @AfterAll
    public static void closeConnection() throws SQLException {
        Connection connection = AzureDBConnector.getConnection();
        if (connection != null) {
            connection.close();
        }
  }
}
