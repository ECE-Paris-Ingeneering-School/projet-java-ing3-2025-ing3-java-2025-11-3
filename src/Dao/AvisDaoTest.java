package Dao;

import MODELE.Avis;
import MODELE.Hebergement;
import MODELE.User;
import db.AzureDBConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

public class AvisDaoTest {


    @BeforeEach
    void setUp() throws Exception {
        // Initialize the connection to the database
        AzureDBConnector connector = AzureDBConnector.getInstance();
        Connection connection = connector.getConnection();




    }


    @Test void testajout(){

        AvisDaoImpl avisDao = new AvisDaoImpl(new AzureDBConnector());
        HebergementDaoImpl hebergementDao = new HebergementDaoImpl(new AzureDBConnector());
        UserDaoImpl userDao = new UserDaoImpl(new AzureDBConnector());
        // Create a new User object
        User user = new User("testNom", "testPrenom", "testEmail", "testPassword");
        userDao.ajouterUser(user);
        int userId = userDao.getIdUser("testEmail");
        user.setId(userId);

        // Create a new Hebergement object
        String hname = "testHebergement";
        ArrayList<String> im = new ArrayList<>();
        im.add("test");
        Hebergement hebergement = new Hebergement(hname, 1, "testDescription", "des", 1);
        hebergement.setImage(im);
        hebergementDao.ajouterHebergement(hebergement);
        int hebergementId = hebergementDao.getIdHebergement(hname);
        hebergement.setHid(hebergementId);



        // Create a new Avis object
        Avis avis = new Avis(1, "test", hebergementId, userId);
        avisDao.saveAvis(avis);

        int avisId = avisDao.getIdAvis(userId, hebergementId);
        avis.setId(avisId);
        Avis retriveavis = avisDao.getAvisById(avisId);


        // Verify that the Avis was saved correctly
        assertEquals(avis.getCommentaire(), retriveavis.getCommentaire(), "Avis Comment should match");
        assertEquals(avis.getNote(), retriveavis.getNote(), "Avis Note should match");
        assertEquals(avis.getIdHebergement(), retriveavis.getIdHebergement(), "Avis Hebergement ID should match");
        assertEquals(avis.getIdClient(), retriveavis.getIdClient(), "Avis Client should match");

        // Clean up the test data
        //avisDao.deleteAvis(avisId);
        //userDao.supprimerUser(userId);
        //hebergementDao.supprimerHebergement(hebergementId);


    }



}
