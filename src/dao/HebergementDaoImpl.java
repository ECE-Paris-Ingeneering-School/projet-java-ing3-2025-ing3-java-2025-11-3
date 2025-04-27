package dao;

import modele.Avis;
import modele.Hebergement;
import modele.Options;
import db.AzureDBConnector;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Map;

public class HebergementDaoImpl implements HebergementDao {
    private final AzureDBConnector conn;
    public HebergementDaoImpl(AzureDBConnector conn) {this.conn=conn;}


    @Override
    public void ajouterHebergement(Hebergement hebergement) {

        String images = null;
        if (hebergement.getImage()!=null) {
            images = compreserListe(hebergement.getImage());
        }
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
    public ArrayList<Hebergement> getAllHebergements() {
        try {
            Connection connection = AzureDBConnector.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM hebergement");
            ResultSet rs = ps.executeQuery();
            ArrayList<Hebergement> hebergements = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("hebergement_id");
                String nom = rs.getString("nom");
                int type = rs.getInt("type");
                String adresse = rs.getString("adresse");
                String description = rs.getString("description");
                int prix = rs.getInt("prix_base");
                int etoile = rs.getInt("etoile");
                String images = rs.getString("photo");
                ArrayList<String> imageList = decompreserListe(images);

                // Récupérer les options pour cet hébergement
                ArrayList<Options> options = getOption(id);

                // Récupérer les avis pour cet hébergement
                ArrayList<Avis> avis = getAllAvis(id);

                // Créer un objet Hebergement avec les données récupérées
                hebergements.add(new Hebergement(id, nom, type, adresse, description, prix, etoile, imageList, options, avis));
            }
            return hebergements;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    public ArrayList<Avis> getAllAvis(int id_hebergement) {
        try{
            AvisDao avisDao= new AvisDaoImpl(conn);
            Connection connection=conn.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM avis WHERE hebergement_id = ?");
            ps.setInt(1, id_hebergement);
            ResultSet rs = ps.executeQuery();
            ArrayList<Avis> avisList = new ArrayList<>();
            while (rs.next()) {
                int avis_id = rs.getInt("avis_id");
                Avis avis = new Avis();
                avis=avisDao.getAvisById(avis_id);
                avisList.add(avis);
            }
            return avisList;

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

        if (string !=null) {
            String[] parts = string.split(",");
            return new ArrayList<String>(List.of(parts));
        }else{
            return new ArrayList<>();
        }
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

    private static final Map<String, Integer> TYPE_CODES = Map.of(
            "HOTEL", 0,
            "AUBERGE", 1,
            "APPARTEMENT", 2,
            "MAISON", 3,
            "CAMPING", 4,
            "AUTRE", 5
    );

    @Override
    public List<Hebergement> getFilteredHebergements(List<String> types, int prixMax, String rechercheTexte, LocalDate dateArrivee, LocalDate dateDepart) {
        List<Hebergement> hebergements = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT * FROM hebergement h WHERE prix_base <= ?");
        List<Object> params = new ArrayList<>();
        params.add(prixMax);

        // Filtre par types
        if (types != null && !types.isEmpty()) {
            String placeholders = types.stream()
                    .map(t -> "?")
                    .collect(Collectors.joining(", "));
            sql.append(" AND h.type IN (").append(placeholders).append(")");
            for (String type : types) {
                Integer code = TYPE_CODES.get(type.toUpperCase());
                if (code != null) params.add(code);
            }
        }

        // Filtre textuel
        if (rechercheTexte != null && !rechercheTexte.isBlank()) {
            sql.append(" AND LOWER(h.nom) LIKE ?");
            params.add("%" + rechercheTexte.toLowerCase().trim() + "%");
        }

        // Filtre de disponibilité via table reservation
        if (dateArrivee != null && dateDepart != null) {
            sql.append(" AND NOT EXISTS (")
                    .append("SELECT 1 FROM reservation r ")
                    .append("WHERE r.hebergement_id = h.hebergement_id ")
                    .append("AND r.date_debut < ? ")
                    .append("AND r.date_fin > ?")
                    .append(")");
            // dateDepart pour le test < date_debut, puis dateArrivee pour > date_fin
            params.add(Date.valueOf(dateDepart));
            params.add(Date.valueOf(dateArrivee));
        }

        try (Connection connection = conn.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql.toString())) {

            // Injection des paramètres
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    hebergements.add(new Hebergement(
                            rs.getInt("hebergement_id"),
                            rs.getString("nom"),
                            rs.getInt("type"),
                            rs.getString("adresse"),
                            rs.getString("description"),
                            rs.getInt("prix_base"),
                            rs.getInt("etoile"),
                            decompreserListe(rs.getString("photo")),
                            null,
                            null
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur DAO getFilteredHebergements", e);
        }

        return hebergements;
    }

}
