package Dao;

import MODELE.Reservation;

import java.sql.Date;
import java.util.ArrayList;

public interface ReservationDao {
    void nouvelleReservation(Reservation reservation);
    void annulerReservation(int id);

    int getReservationId(int idClient, int idHebergement);
    Reservation getReservationById(int id);

    ArrayList<Reservation> getallReservationSQL(String Sql,int id);
    ArrayList<Reservation> getAllReservationByClientId(int idClient);
    ArrayList<Reservation> getAllReservationByHebergementId(int idHebergement);

    String convertDateToString(Date date);
    Date convertStringToDate(String date);


}
