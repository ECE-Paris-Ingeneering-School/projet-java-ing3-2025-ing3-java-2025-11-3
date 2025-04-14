package Dao;

import MODELE.Avis;
import MODELE.Hebergement;
import MODELE.Options;
import MODELE.User;
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

    @Test void avis(){
        HebergementDaoImpl hebergementDao = new HebergementDaoImpl(new AzureDBConnector());
        UserDaoImpl userDao = new UserDaoImpl(new AzureDBConnector());
        AvisDaoImpl avisDao = new AvisDaoImpl(new AzureDBConnector());

        //hebergement
        ArrayList<String> img = new ArrayList<>();
        img.add("image1.jpg");
        img.add("image2.jpg");
        String name = "hebergementtest" ;
        Hebergement hebergement = new Hebergement(name, 1, "adresse", "description",100);
        hebergement.setImage(img);
        hebergementDao.ajouterHebergement(hebergement);
        int hid =hebergementDao.getIdHebergement(name);
        hebergement.setHid(hid);

        //user
        String nom1 = "usertest1" ;
        String nom2 = "usertest2" ;
        User user = new User(nom1, "prenom", "email1", "password");
        User user2 = new User(nom2, "prenom", "email2", "password");
        userDao.ajouterUser(user);
        userDao.ajouterUser(user2);
        int id_user = userDao.getIdUser("email1");
        int id_user2 = userDao.getIdUser("email2");
        user.setId(id_user);
        user2.setId(id_user2);

        //avis
        Avis avis = new Avis(4, "commentaire", hid, id_user);
        avisDao.saveAvis(avis);
        Avis avis2 = new Avis(4, "commentaire2", hid, id_user2);
        avisDao.saveAvis(avis2);




        int id_avis = avisDao.getIdAvis(id_user, hid);
        int id_avis2 = avisDao.getIdAvis(id_user2, hid);
        avis.setId(id_avis);
        avis2.setId(id_avis2);


        assert avis.getIdClient() == id_user;

        assert avis2.getIdClient() == id_user2;

        ArrayList<Avis> avisList = hebergementDao.getAllAvis(hid);
        assert avisList != null;
        assert avisList.size() == 2;
        assert avisList.get(0).getIdClient() == id_user;
        assert avisList.get(1).getIdClient() == id_user2;

        hebergementDao.supprimerHebergement(hid);
        userDao.supprimerUser(id_user);
        userDao.supprimerUser(id_user2);








    }

    @Test void allhebergement(){
        HebergementDaoImpl hebergementDao = new HebergementDaoImpl(new AzureDBConnector());
        ArrayList<String> img1 = new ArrayList<>();
        img1.add("image1.jpg");
        img1.add("image2.jpg");
        ArrayList<String> img2 = new ArrayList<>();
        img2.add("image3.jpg");
        img2.add("image4.jpg");
        img2.add("image5.jpg");

        Hebergement hebergement1=new Hebergement("hebergement1", 1, "adresse1", "description",100);
        hebergement1.setImage(img1);
        Hebergement hebergement2=new Hebergement("hebergement2", 1, "adresse2", "description",100);
        hebergement2.setImage(img2);
        Hebergement hebergement3=new Hebergement("hebergement3", 2, "adresse3", "description",100);
        hebergement3.setImage(img1);
        Hebergement hebergement4=new Hebergement("hebergement4", 1, "adresse4", "description",100);
        hebergement4.setImage(img2);
        hebergementDao.ajouterHebergement(hebergement1);
        hebergementDao.ajouterHebergement(hebergement2);
        hebergementDao.ajouterHebergement(hebergement3);
        hebergementDao.ajouterHebergement(hebergement4);
        ArrayList<Hebergement> hebergements = hebergementDao.getAllHebergements();
        assert hebergements != null;
        assert hebergements.size() == 4;
        assert hebergements.get(0).getNom().equals("hebergement1");
        assert hebergements.get(1).getNom().equals("hebergement2");
        assert hebergements.get(2).getNom().equals("hebergement3");
        assert hebergements.get(3).getNom().equals("hebergement4");
        assert hebergements.get(0).getType() == 1;
        assert hebergements.get(1).getType() == 1;
        assert hebergements.get(2).getType() == 2;
        assert hebergements.get(3).getType() == 1;
        assert hebergements.get(0).getAdresse().equals("adresse1");
        assert hebergements.get(1).getAdresse().equals("adresse2");
        assert hebergements.get(2).getAdresse().equals("adresse3");
        assert hebergements.get(3).getAdresse().equals("adresse4");

        hebergementDao.supprimerHebergement(hebergements.get(0).getHid());
        hebergementDao.supprimerHebergement(hebergements.get(1).getHid());
        hebergementDao.supprimerHebergement(hebergements.get(2).getHid());
        hebergementDao.supprimerHebergement(hebergements.get(3).getHid());

    }
}
