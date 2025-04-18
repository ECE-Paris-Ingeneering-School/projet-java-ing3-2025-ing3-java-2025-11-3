package dao;

import modele.Hebergement;
import modele.Reservation;
import db.AzureDBConnector;

import java.sql.*;
import java.util.ArrayList;

public class ReservationDaoImpl implements ReservationDao {
    private final AzureDBConnector conn;

    public ReservationDaoImpl(AzureDBConnector conn) {
        this.conn = conn;
    }

    @Override
    public void nouvelleReservation(Reservation reservation) {
        String sql = "INSERT INTO reservation (date_debut, date_fin, hebergement_id, user_id, tarif_final) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection connection = conn.getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setDate(1, convertStringToDate(reservation.getDateDebut()));
            prst.setDate(2, convertStringToDate(reservation.getDateFin()));
            prst.setInt(3, reservation.getIdHebergement());
            prst.setInt(4, reservation.getIdClient());
            prst.setFloat(5, reservation.getPrix());
            prst.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void annulerReservation(int id) {
        String sql = "DELETE FROM reservation WHERE reservation_id = ?";
        try {
            Connection connection = conn.getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setInt(1, id);
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getReservationId(int idClient, int idHebergement) {
        String sql = "SELECT reservation_id FROM reservation WHERE user_id = ? AND hebergement_id = ?";
        try {
            Connection connection = conn.getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setInt(1, idClient);
            prst.setInt(2, idHebergement);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                return rs.getInt("reservation_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public Reservation getReservationById(int id) {
        String sql = "SELECT * FROM reservation WHERE reservation_id = ?";
        try {
            Connection connection = conn.getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setInt(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                return new Reservation(
                        rs.getInt("reservation_id"),
                        convertDateToString(rs.getDate("date_debut")),
                        convertDateToString(rs.getDate("date_fin")),
                        rs.getInt("hebergement_id"),
                        rs.getInt("user_id"),
                        rs.getFloat("tarif_final")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    private ArrayList<Reservation> getallReservationSQL(String Sql, int id) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            Connection connection = conn.getConnection();
            PreparedStatement prst = connection.prepareStatement(Sql);
            prst.setInt(1, id);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Reservation r = new Reservation(
                        rs.getInt("reservation_id"),
                        convertDateToString(rs.getDate("date_debut")),
                        convertDateToString(rs.getDate("date_fin")),
                        rs.getInt("hebergement_id"),
                        rs.getInt("user_id"),
                        rs.getFloat("tarif_final")
                );

                Hebergement h = new Hebergement(
                        rs.getString("hebergement_nom"),
                        rs.getInt("hebergement_type"),
                        rs.getString("hebergement_adresse"),
                        rs.getString("hebergement_description"),
                        rs.getInt("hebergement_prix")
                );

                r.setHebergement(h);
                reservations.add(r);
            }
            return reservations;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Reservation> getAllReservationByClientId(int idClient) {
        String sql = """
                SELECT 
                    r.*, 
                    h.nom AS hebergement_nom,
                    h.type AS hebergement_type,
                    h.adresse AS hebergement_adresse,
                    h.description AS hebergement_description,
                    h.prix_base AS hebergement_prix
                FROM reservation r
                JOIN hebergement h ON r.hebergement_id = h.hebergement_id
                WHERE r.user_id = ?
                """;
        return getallReservationSQL(sql, idClient);
    }

    @Override
    public ArrayList<Reservation> getAllReservationByHebergementId(int idHebergement) {
        String sql = "SELECT * FROM reservation WHERE hebergement_id = ?";
        return getallReservationSQL(sql, idHebergement);
    }

    @Override
    public String convertDateToString(Date date) {
        if (date != null) {
            return date.toString();
        }
        return "";
    }

    @Override
    public Date convertStringToDate(String date) {
        if (date != null && !date.isEmpty()) {
            return Date.valueOf(date);
        }
        return null;
    }
}
