package Dao;

import MODELE.Reservation;

import java.sql.Date;
import java.util.ArrayList;

public interface ReservationDao {
    /*
    * Sauvegarde une nouvelle reservation dans la BDD
    * @param Reservation sans reservation_id
     */
    void nouvelleReservation(Reservation reservation);
    /*
     * Suprime une reservation dans la BDD
     * @param reservation_id
     */
    void annulerReservation(int id);
    /*
    * Return Reservation_id
    * @param Client_id
    * @param Hebergement_id
    * @return reservation_id
     */
    int getReservationId(int idClient, int idHebergement);
    /*
    * Return une Reservation via son reservation_id
    * @param reservation_id
    * @return Reservation
     */
    Reservation getReservationById(int id);
    /*
    * Return toutes les reservation d un Client  via son idClient
    * @param idClient
     */
    ArrayList<Reservation> getAllReservationByClientId(int idClient);
    /*
     * Return toutes les reservation d un hebergements  via son idHebergement
     * @param idHebergement
     */
    ArrayList<Reservation> getAllReservationByHebergementId(int idHebergement);

    /*
     * Convertion d'une date en String
     * @param date
     * @return String
     */
    String convertDateToString(Date date);
    /*
     * Convertion d'une String en date
     * @param date
     * @return Date
     */
    Date convertStringToDate(String date);


}
