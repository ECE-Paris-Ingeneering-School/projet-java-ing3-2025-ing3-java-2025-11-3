package dao;

import modele.Options;
import db.AzureDBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OptionDaoImpl implements OptionDao{
    private AzureDBConnector conn;
    public OptionDaoImpl(AzureDBConnector conn) {
        this.conn = conn;
    }

    @Override
    public void ajouterOption(Options option) {

        String sql = "INSERT INTO options (nom_option, description) VALUES (?, ?)";
        try  {
            Connection connection= conn.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, option.getNom());
            pstmt.setString(2, option.getDescription());
            pstmt.executeUpdate();
        } catch ( SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void supprimerOption(int id) {
        String sql = "DELETE FROM options WHERE option_id = ?";
        try {
            Connection connection= conn.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Options getOptionById(int id) {
        String sql = "SELECT * FROM options WHERE option_id = ?";
        try{
            Connection connection= conn.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {

                String nom = rs.getString("nom_option");
                String description = rs.getString("description");
                return new Options(id, nom, description);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Options getOptionByNom(String nom) {
        String sql = "SELECT * FROM options WHERE nom_option = ?";
        try {
            Connection connection= conn.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("option_id");
                String description = rs.getString("description");
                return new Options(id, nom, description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int getIdOption(String nom) {
        String sql = "SELECT option_id FROM options WHERE nom_option = ?";
        try {
            Connection connection= conn.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("option_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
