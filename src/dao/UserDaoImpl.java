package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modele.Admin;
import modele.User;
import db.AzureDBConnector;

public class UserDaoImpl implements UserDao {

    private final AzureDBConnector conn;

    public UserDaoImpl(AzureDBConnector conn) {this.conn = conn;}

    @Override
    public int getIdUserByEmail(String email) {
        try{
            Connection connection= conn.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT user_id FROM user WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public void ajouterUser(User user) {
        try {
            Connection connection = conn.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO user (nom, prenom, email, mdp) VALUES (?, ?, ?, ?)");
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByID(int id) {
        return getUserFromQuery("SELECT * FROM user WHERE user_id = ?", id);
    }

    @Override
    public User getUserByEmail(String email) {
        return getUserFromQuery("SELECT * FROM user WHERE email = ?", email);
    }

    private User getUserFromQuery(String query, Object param) {
        try {
            Connection connection = conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            if (param instanceof Integer) {
                ps.setInt(1, (int) param);
            } else if (param instanceof String) {
                ps.setString(1, (String) param);
            } else {
                throw new IllegalArgumentException("Unsupported parameter type");
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("user_id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                String password = rs.getString("mdp");
                boolean isAdmin = rs.getBoolean("admin");

                if (isAdmin) {
                    return new Admin(id, nom, prenom, email, password);
                } else {
                    return new User(id, nom, prenom, email, password);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    
    @Override
    public void supprimerUser(int id) {
        try {
            Connection connection =conn.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM user WHERE user_id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifierUser(User user) {
        try{
            Connection connection = conn.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE user SET nom = ?, prenom = ?, email = ?, mdp = ? WHERE user_id = ?");
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean connexionUser(String mail, String mdp){
        try {
            Connection connection = conn.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE email = ? AND mdp = ?");
            ps.setString(1, mail);
            ps.setString(2,mdp);
            System.out.println("launch request");
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}