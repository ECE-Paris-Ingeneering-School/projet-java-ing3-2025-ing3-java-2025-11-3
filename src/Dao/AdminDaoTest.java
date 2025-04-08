package Dao;

import db.AzureDBConnector;
import MODELE.Admin;
import MODELE.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AdminDaoTest {

    private AdminDaoImpl AdminDao;
    private UserDaoImpl UserDao;
    @BeforeEach
    public void setUp() throws Exception {
        // Initialize the connection to the database
        AzureDBConnector connector = AzureDBConnector.getInstance();
        Connection connection = connector.getConnection();

        // Initialize the UserDaoImpl with the connection
        UserDao  = new UserDaoImpl(connector);
        AdminDao = new AdminDaoImpl(connector);


    }
    @Test
    void testCreateAdmin() throws Exception {
        AzureDBConnector connector = AzureDBConnector.getInstance();
        Connection connection = connector.getConnection();
        // Create a new user
        User user = new User("testNom", "testPrenom", "testEmail", "testPassword");
        UserDao.ajouterUser(user);
        int userId = UserDao.getIdUser("testEmail");
        user.setId(userId);

        // Create a new admin
        Admin admin = new Admin(userId, user.getNom(), user.getPrenom(), user.getEmail(), user.getPassword());
        AdminDao.createAdmin(admin);

        // Verify that the admin was created correctly
        List<Integer > adminList = AdminDao.getAllAdmins();
        if(adminList.isEmpty()){
            fail("Admin list should not be empty");
        }
        if (!adminList.contains(userId)) {
            fail("Admin list should contain the new admin's ID");
        }



        // Clean up the test data
        PreparedStatement ps = connection.prepareStatement("DELETE FROM user WHERE email = 'testEmail'");
        ps.executeUpdate();
    }
    @AfterAll
    public static void tearDown() throws Exception {
        // Clean up the database connection
        AzureDBConnector connector = AzureDBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM user WHERE email = 'testEmail'");
        ps.executeUpdate();
    }
}
