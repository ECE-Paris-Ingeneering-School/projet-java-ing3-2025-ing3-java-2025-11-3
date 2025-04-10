package Dao;

import MODELE.Options;
import db.AzureDBConnector;

import org.junit.jupiter.api.*;

import java.sql.Connection;

public class OptionDaoTest {


    @BeforeEach
    public void setUp() throws Exception {
        // Initialize the connection to the database
        AzureDBConnector connector = AzureDBConnector.getInstance();
        Connection connection = connector.getConnection();

        // Initialize the OptionDaoImpl with the connection
        OptionDaoImpl optionDao = new OptionDaoImpl(connector);

        // Add test data if necessary
    }

    @Test
    void testAjouterOption() {
        // Create a new option
        Options option = new Options("testNom", "testDescription");

        // Add the option to the database
        OptionDaoImpl optionDao = new OptionDaoImpl(AzureDBConnector.getInstance());
        optionDao.ajouterOption(option);

        // Verify that the option was added correctly
        int id = optionDao.getIdOption("testNom");
        Assertions.assertNotEquals(-1, id, "Option ID should not be -1");
        option.setId(id);
        Options options2 = optionDao.getOptionById(id);
        Assertions.assertEquals(option.getNom(), options2.getNom(), "Option name should be 'testNom'");
        Assertions.assertEquals(option.getDescription(), options2.getDescription(), "Option description should be 'testDescription'");


        // Clean up the test data
        optionDao.supprimerOption(id);
    }

    @Test
    void testGets() {
        // Create a new option
        Options option = new Options("testNom", "testDescription");

        // Add the option to the database
        OptionDaoImpl optionDao = new OptionDaoImpl(AzureDBConnector.getInstance());
        optionDao.ajouterOption(option);

        // Verify that the option was added correctly
        int id = optionDao.getIdOption("testNom");
        Assertions.assertNotEquals(-1, id, "Option ID should not be -1");
        option.setId(id);
        Options options2 = optionDao.getOptionByNom("testNom");

        Assertions.assertEquals(option.getNom(), options2.getNom(), "Option name should be 'testNom'");
        Assertions.assertEquals(option.getDescription(), options2.getDescription(), "Option description should be 'testDescription'");
    }
}