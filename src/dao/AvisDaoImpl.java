package dao;

import modele.Avis;
import db.AzureDBConnector;

import java.sql.Connection;
import java.sql.SQLException;

public class    AvisDaoImpl implements AvisDao {
    private final AzureDBConnector conn;

    public AvisDaoImpl(AzureDBConnector conn) {this.conn = conn;}

    @Override
    public void saveAvis(Avis avis) {
        try{
            Connection connection = conn.getConnection();
            String sql = "INSERT INTO avis (user_id,hebergement_id,note,commentaire) VALUES (?, ?, ?, ?)";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, avis.getIdClient());
            preparedStatement.setInt(2, avis.getIdHebergement());
            preparedStatement.setInt(3, avis.getNote());
            preparedStatement.setString(4, avis.getCommentaire());
            preparedStatement.executeUpdate();


        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getIdAvis(int clientId, int produitId) {
        try{
            Connection connection = conn.getConnection();
            String sql = "SELECT avis_id FROM avis WHERE user_id = ? AND hebergement_id = ?";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, clientId);
            preparedStatement.setInt(2, produitId);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("avis_id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public Avis getAvisById(int id) {
        try{
            Connection connection= conn.getConnection();
            String sql = "SELECT * FROM avis WHERE avis_id = ? ";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int note = resultSet.getInt("note");
                String commentaire = resultSet.getString("commentaire");
                int idHebergement = resultSet.getInt("hebergement_id");
                int idClient = resultSet.getInt("user_id");
                return new Avis(id, note, commentaire, idHebergement, idClient);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void updateAvis(Avis avis) {
        try{
            Connection connection = conn.getConnection();
            String sql = "UPDATE avis SET note = ?, commentaire = ? WHERE avis_id = ?";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, avis.getNote());
            preparedStatement.setString(2, avis.getCommentaire());
            preparedStatement.setInt(3, avis.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteAvis(int id) {
        try{
            Connection connection = conn.getConnection();
            String sql = "DELETE FROM avis WHERE avis_id = ?";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
