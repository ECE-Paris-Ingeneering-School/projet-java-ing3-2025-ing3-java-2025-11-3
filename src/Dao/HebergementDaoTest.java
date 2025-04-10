package Dao;

import MODELE.Hebergement;
import MODELE.Options;
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


    @Test
    public void OptionHebergementTest() {
        Random rand = new Random();
        HebergementDaoImpl hebergementDao = new HebergementDaoImpl(new AzureDBConnector());
        OptionDaoImpl optionDao = new OptionDaoImpl(new AzureDBConnector());
        ArrayList<String> img = new ArrayList<>();
        img.add("image1.jpg");
        img.add("image2.jpg");
        String name = "hebergement" + rand.nextInt(100000)+10000000;
        Hebergement hebergement = new Hebergement(name, 1, "adresse", "description",100);
        hebergement.setImage(img);
        hebergementDao.ajouterHebergement(hebergement);
        int id =hebergementDao.getIdHebergement(name);
        hebergement.setHid(id);
        Options option1 = new Options("option1", "description option 1");
        Options option2 = new Options("option2", "description option 2");
        optionDao.ajouterOption(option1);
        optionDao.ajouterOption(option2);
        int id_option1 = optionDao.getIdOption("option1");
        option1.setId(id_option1);
        int id_option2 = optionDao.getIdOption("option2");
        option2.setId(id_option2);
        hebergementDao.ajouterOption(id, id_option1);
        hebergementDao.ajouterOption(id, id_option2);
        ArrayList<Options> options = hebergementDao.getOption(id);
        assert options != null;
        assert options.size() == 2;
        assert options.get(0).getNom().equals("option1");
        assert options.get(1).getNom().equals("option2");
        assert options.get(0).getDescription().equals("description option 1");
        assert options.get(1).getDescription().equals("description option 2");
        hebergementDao.supprimerHebergement(id);
        optionDao.supprimerOption(id_option1);
        optionDao.supprimerOption(id_option2);



    }
}
