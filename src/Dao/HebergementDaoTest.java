package Dao;

import MODELE.Hebergement;
import db.AzureDBConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class HebergementDaoTest {
    @BeforeEach
    public void setUp()  {
        // Initialize the connection to the database
        AzureDBConnector connector = AzureDBConnector.getInstance();
        HebergementDaoImpl hebergementDao = new HebergementDaoImpl(connector);

    }
    @Test
    public void insertHebergement() {
        Random rand = new Random();
        HebergementDaoImpl hebergementDao = new HebergementDaoImpl(new AzureDBConnector());
        ArrayList<String> img = new ArrayList<>();
        img.add("image1.jpg");
        img.add("image2.jpg");
        String name = "hebergement" + rand.nextInt(100000);



        Hebergement hebergement = new Hebergement(name, 1, "adresse", "description",100);
        hebergement.setImage(img);
        hebergementDao.ajouterHebergement(hebergement);
        int id = hebergementDao.getIdHebergement(name);
        Hebergement hebergement1 = hebergementDao.getHebergement(id);
        assert hebergement1 != null;
        assert hebergement1.getNom().equals(name);
        assert hebergement1.getType() == 1;
        assert hebergement1.getAdresse().equals("adresse");
        assert hebergement1.getDescription().equals("description");
        assert hebergement1.getPrix() == 100;
        assert hebergement1.getImage().get(0).equals("image1.jpg");
        assert hebergement1.getImage().get(1).equals("image2.jpg");
        hebergementDao.supprimerHebergement(id);



    }
    @Test
    public void modifHebergementTest() {
        Random rand = new Random();
        HebergementDaoImpl hebergementDao = new HebergementDaoImpl(new AzureDBConnector());
        ArrayList<String> img = new ArrayList<>();
        img.add("image1.jpg");
        img.add("image2.jpg");
        String name = "hebergement" + rand.nextInt(100000)+10000000;
        Hebergement hebergement = new Hebergement(name, 1, "adresse", "description",100);
        hebergement.setImage(img);
        hebergementDao.ajouterHebergement(hebergement);
        int id = hebergementDao.getIdHebergement(name);
        Hebergement hebergement1 = hebergementDao.getHebergement(id);
        hebergement1.setDescription("description hebergement Modifie");
        hebergementDao.modifierHebergement(hebergement1);
        Hebergement hebergement2 = hebergementDao.getHebergement(id);
        assert hebergement2 != null;
        assert hebergement2.getNom().equals(name);
        assert hebergement2.getType() == 1;
        assert hebergement2.getAdresse().equals("adresse");
        assert hebergement2.getDescription().equals("description hebergement Modifie");

    }
}
