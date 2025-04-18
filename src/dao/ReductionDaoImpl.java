package dao;

import modele.Reduction;
import db.AzureDBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReductionDaoImpl implements ReductionDao {
    private final AzureDBConnector conn;

    public ReductionDaoImpl(AzureDBConnector conn) {this.conn = conn;}

    @Override
    public void addReduction(Reduction reduction) {
        try{
            Connection connection = conn.getConnection();
            String sql = "INSERT INTO reduction (code_promo, pourcentage) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, reduction.getCodePromo());
            preparedStatement.setInt(2, reduction.getPourcentage());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getreductionId(Reduction reduction) {
        try {
            Connection connection = conn.getConnection();
            String sql = "SELECT reduction_id FROM reduction WHERE code_promo = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, reduction.getCodePromo());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("reduction_id");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public Reduction getReductionById(int id) {
        try{
            Connection connection = conn.getConnection();
            String sql = "SELECT * FROM reduction WHERE reduction_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Reduction(resultSet.getInt("reduction_id"), resultSet.getString("code_promo"), resultSet.getInt("pourcentage"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteReduction(int id) {
        try{
            Connection connection = conn.getConnection();
            String sql = "DELETE FROM reduction WHERE reduction_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateReduction(Reduction reduction) {
        try{
            Connection connection = conn.getConnection();
            String sql = "UPDATE reduction SET code_promo = ?, pourcentage = ? WHERE reduction_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, reduction.getCodePromo());
            preparedStatement.setInt(2, reduction.getPourcentage());
            preparedStatement.setInt(3, reduction.getReducID());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean checkReductionCode(String CodePromo) {
        try {
            Connection connection = conn.getConnection();
            String sql = "SELECT * FROM reduction WHERE code_promo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, CodePromo);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
