package Dao;
import MODELE.Client;
import MODELE.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.AzureDBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ClientDaoTest {
    private ClientDaoImpl ClientDao;
    private UserDaoImpl UserDao;

    @BeforeEach
    public void setUp() throws Exception {
        // Initialize the connection to the database
        AzureDBConnector connector = AzureDBConnector.getInstance();
        Connection connection = connector.getConnection();

        // Initialize the UserDaoImpl with the connection
        UserDao = new UserDaoImpl(connector);
        ClientDao = new ClientDaoImpl(connector);


    }
    @Test
    void testModifierUnewClient()  {
        // Create a new client
        User user = new User("testNom", "testPrenom", "testEmail", "testPassword");
        UserDao.ajouterUser(user);
        int userId = UserDao.getIdUser("testEmail");
        user.setId(userId);


        Client client = new Client(userId, user.getNom(), user.getPrenom(), user.getEmail(), user.getPassword(), 1);
        ClientDao.modifierUnewClient(client);


        // Verify that the client's state was updated correctly
        int clientState = ClientDao.getClient_State(userId);
        System.out.println(clientState);
        assertEquals(1, clientState, "Client state should be updated to true");

        client.setNewClient(0);

        ClientDao.modifierUnewClient(client);
        // Verify that the client's state was updated correctly
        clientState = ClientDao.getClient_State(userId);

        assertEquals(0, clientState, "Client state should be updated to false");

        // Clean up the test data
        //UserDao.supprimerUser(userId);
    }
    @AfterAll
    public static void tearDown() throws Exception {
        // Clean up the database connection
        AzureDBConnector connector = AzureDBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM user WHERE email = 'testEmail'");
        ps.executeUpdate();
        connection.close();
    }
}
