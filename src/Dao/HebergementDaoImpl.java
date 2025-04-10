package Dao;

import MODELE.Hebergement;
import MODELE.Options;
import db.AzureDBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HebergementDaoImpl implements HebergementDao {
    private final AzureDBConnector conn;
    public HebergementDaoImpl(AzureDBConnector conn) {this.conn=conn;}


    @Override
    public void ajouterHebergement(Hebergement hebergement) {

        String images = compreserListe(hebergement.getImage());
        try{
            Connection connection=conn.getConnection();
            String sql = "INSERT INTO hebergement (nom, type, adresse, description, prix_base,etoile,photo) VALUES (?, ?, ?, ?, ?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, hebergement.getNom());
            ps.setInt(2, hebergement.getType());
            ps.setString(3, hebergement.getAdresse());
            ps.setString(4, hebergement.getDescription());
            ps.setInt(5, hebergement.getPrix());
            ps.setInt(6, hebergement.getNote());
            ps.setString(7, images);
            ps.executeUpdate();
            


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getIdHebergement(String nom) {
        try{
            Connection connection=conn.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT hebergement_id FROM hebergement WHERE nom = ?");
            ps.setString(1, nom);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("hebergement_id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public Hebergement getHebergement(int id) {
        try{
            Connection connection=conn.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM hebergement WHERE hebergement_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("nom");
                int type = rs.getInt("type");
                String adresse = rs.getString("adresse");
                String description = rs.getString("description");
                int prix = rs.getInt("prix_base");
                int etoile = rs.getInt("etoile");
                String images = rs.getString("photo");
                ArrayList<String> imageList = decompreserListe(images);
                return new Hebergement(id, nom, type, adresse, description, prix, etoile, imageList, null, null);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void supprimerHebergement(int id) {
        try{
            Connection connection=conn.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM hebergement WHERE hebergement_id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void modifierHebergement(Hebergement hebergement) {
        String images = compreserListe(hebergement.getImage());
        try{
            Connection connection=conn.getConnection();
            String sql = "UPDATE hebergement SET nom = ?, type = ?, adresse = ?, description = ?, prix_base = ?, etoile = ?, photo = ? WHERE hebergement_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, hebergement.getNom());
            ps.setInt(2, hebergement.getType());
            ps.setString(3, hebergement.getAdresse());
            ps.setString(4, hebergement.getDescription());
            ps.setInt(5, hebergement.getPrix());
            ps.setInt(6, hebergement.getNote());
            ps.setString(7, images);
            ps.setInt(8, hebergement.getHid());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public ArrayList<Options> getOption(int id_hebergement) {
        try{
            OptionDao optionDao= new OptionDaoImpl(conn);
            Connection connection=conn.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM hebergement_option WHERE hebergement_id = ?");
            ps.setInt(1, id_hebergement);
            ResultSet rs = ps.executeQuery();
            ArrayList<Options> optionsList = new ArrayList<>();
            while (rs.next()) {
                int option_id = rs.getInt("option_id");
                Options option = new Options();
                option=optionDao.getOptionById(option_id);
                optionsList.add(option);
            }
            return optionsList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String compreserListe(ArrayList<String> list) {
        String result = "";
        for (String img : list) {
            result += img + ",";
        }
        // Remove the last comma
        result = result.substring(0, result.length() - 1);
        return result;
    }
    @Override
    public ArrayList<String> decompreserListe(String string) {
        String[] parts = string.split(",");
        return new ArrayList<String>(List.of(parts));
    }

    @Override
    public void ajouterOption(int id_hebergement, int id_option) {
        try{
            Connection connection=conn.getConnection();
            String sql = "INSERT INTO hebergement_option (hebergement_id, option_id) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id_hebergement);
            ps.setInt(2, id_option);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
