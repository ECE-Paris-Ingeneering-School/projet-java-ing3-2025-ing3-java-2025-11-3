package dao;

import db.AzureDBConnector;
import modele.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {

    private final AzureDBConnector conn;

    public ClientDaoImpl(AzureDBConnector conn) {this.conn = conn;}
    @Override
    public void modifierUnewClient(Client client) {
        try {
            Connection connection = conn.getConnection();
            String sql =" UPDATE user SET newU = ? WHERE user_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, client.isNewClient());
            ps.setInt(2, client.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }



    @Override
    public int getClient_State(int id) {
        try{
            Connection connection= conn.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT newU FROM user WHERE user_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("newU");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT user_id, nom, prenom, email, mdp FROM user WHERE newU = 1 ";

        try {
            Connection connection = conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("user_id");
                String nom =(rs.getString("nom"));
                String prenom =(rs.getString("prenom"));
                String mail =(rs.getString("email"));
                String mdp = rs.getString("mdp");

                clients.add(new Client(id,nom,prenom,mail,mdp,1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }


}
