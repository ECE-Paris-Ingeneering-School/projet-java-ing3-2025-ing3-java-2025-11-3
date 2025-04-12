package Dao;
import MODELE.Reservation;
import db.AzureDBConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import MODELE.User;
import MODELE.Hebergement;

import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.util.ArrayList;

public class ReservationDaoTest {
    @BeforeEach
    void setUp() {
        // Initialize the ReservationDaoImpl or any other setup needed for the tests
        // For example:




    }
    @Test
    void testajoutetsup() {
        ReservationDaoImpl reservationDao  = new ReservationDaoImpl(new AzureDBConnector());
        UserDaoImpl userDao = new UserDaoImpl(new AzureDBConnector());
        HebergementDaoImpl hebergementDao = new HebergementDaoImpl(new AzureDBConnector());

        // Create a new user
        User user1 = new User("nom", "prenom", "email1", "password");
        User user2 = new User("nom2", "prenom2", "email2", "password2");
        userDao.ajouterUser(user1);
        userDao.ajouterUser(user2);
        user1.setId(userDao.getIdUser("email1"));
        System.out.println(user1.getId());

        user2.setId(userDao.getIdUser("email2"));
        System.out.println(user2.getId());
        // Create a new hebergement
        ArrayList<String> images = new ArrayList<>();
        images.add("image1");
        images.add("image2");


        Hebergement hebergement1 = new Hebergement("nom1", 1,"adresse", "description", 100);
        hebergement1.setImage(images);
        Hebergement hebergement2 = new Hebergement("nom2", 2,"adresse2", "description2", 200);
        hebergement2.setImage(images);
        hebergementDao.ajouterHebergement(hebergement1);
        hebergementDao.ajouterHebergement(hebergement2);
        hebergement1.setHid(hebergementDao.getIdHebergement("nom1"));
        hebergement2.setHid(hebergementDao.getIdHebergement("nom2"));
        // Create a new reservation
        Reservation reservation1 = new Reservation("2023-10-01", "2023-10-05", hebergement1.getHid(), user1.getId(), 100);
        Reservation reservation2 = new Reservation("2023-10-06", "2023-10-10", hebergement1.getHid(), user2.getId(), 200);
        Reservation reservation3 = new Reservation("2023-10-11", "2023-10-15", hebergement2.getHid(), user1.getId(), 300);

        reservationDao.nouvelleReservation(reservation1);
        reservationDao.nouvelleReservation(reservation2);
        reservationDao.nouvelleReservation(reservation3);
        // Check if the reservation was added
        reservation1.setId( reservationDao.getReservationId(user1.getId(), hebergement1.getHid()));
        reservation2.setId( reservationDao.getReservationId(user2.getId(), hebergement1.getHid()));
        reservation3.setId( reservationDao.getReservationId(user1.getId(), hebergement2.getHid()));
        Assertions.assertNotEquals(-1, reservation1.getId());
        Assertions.assertNotEquals(-1, reservation2.getId());
        Assertions.assertNotEquals(-1, reservation3.getId());
        // Check if the reservation is in the database
        ArrayList<Reservation> reservationsC1 = reservationDao.getAllReservationByClientId(user1.getId());
        assert reservationsC1.size() == 2;
        ArrayList<Reservation> reservationsC2 = reservationDao.getAllReservationByClientId(user2.getId());
        assert reservationsC2.size() == 1;
        ArrayList<Reservation> reservationsH1 = reservationDao.getAllReservationByHebergementId(hebergement1.getHid());
        assert reservationsH1.size() == 2;
        ArrayList<Reservation> reservationsH2 = reservationDao.getAllReservationByHebergementId(hebergement2.getHid());
        assert reservationsH2.size() == 1;
        // Delete the reservation
        reservationDao.annulerReservation(reservation1.getId());
        reservationDao.annulerReservation(reservation2.getId());
        reservationDao.annulerReservation(reservation3.getId());
        // Check if the reservation was deleted
        ArrayList<Reservation> reservationsC1After = reservationDao.getAllReservationByClientId(user1.getId());
        ArrayList<Reservation> reservationsC2After = reservationDao.getAllReservationByClientId(user2.getId());
        ArrayList<Reservation> reservationsH1After = reservationDao.getAllReservationByHebergementId(hebergement1.getHid());
        ArrayList<Reservation> reservationsH2After = reservationDao.getAllReservationByHebergementId(hebergement2.getHid());
        assert reservationsC1After.size() == 0;
        assert reservationsC2After.size() == 0;
        assert reservationsH1After.size() == 0;
        assert reservationsH2After.size() == 0;
        // Delete the user
        userDao.supprimerUser(user1.getId());
        userDao.supprimerUser(user2.getId());
        // Delete the hebergement
        hebergementDao.supprimerHebergement(hebergement1.getHid());
        hebergementDao.supprimerHebergement(hebergement2.getHid());



    }
}
