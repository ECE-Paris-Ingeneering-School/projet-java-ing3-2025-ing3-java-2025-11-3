package model;

/**
 * Classe représentant une réservation.
 */
public class Reservation {
    private int id;
    private String client;
    private String hebergement;
    private String dateArrivee;
    private String dateDepart;

    public Reservation(int id, String client, String hebergement, String dateArrivee, String dateDepart) {
        this.id = id;
        this.client = client;
        this.hebergement = hebergement;
        this.dateArrivee = dateArrivee;
        this.dateDepart = dateDepart;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getHebergement() {
        return hebergement;
    }

    public void setHebergement(String hebergement) {
        this.hebergement = hebergement;
    }

    public String getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(String dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }
}
