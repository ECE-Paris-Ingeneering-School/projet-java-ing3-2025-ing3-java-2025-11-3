package Dao;

import MODELE.Admin;
import db.AzureDBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    private final AzureDBConnector conn;

    public AdminDaoImpl(AzureDBConnector conn) {this.conn = conn;}
    // Implement the methods from AdminDao interface
    @Override
    public void createAdmin(Admin admin) {
        // Code to create an admin in the database
        try{
            Connection connection = conn.getConnection();
            String query = "UPDATE user SET admin = 1 WHERE user_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, admin.getId());
            ps.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<Integer> getAllAdmins() {
        // Code to retrieve all admins from the database
        try{
            Connection connection = conn.getConnection();
            String query = "SELECT user_id FROM user WHERE admin = 1";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<Integer> adminIds = new ArrayList<>();
            while (rs.next()) {
                adminIds.add(rs.getInt("user_id"));
            }
            return adminIds;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
