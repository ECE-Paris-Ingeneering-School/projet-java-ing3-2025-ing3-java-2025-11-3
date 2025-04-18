package modele;

public class Reservation {
    private int id;
    private final String dateDebut;
    private final String dateFin;
    private final int idHebergement;
    private final int idClient;
    private final float prix;
    private Hebergement hebergement;

    public Reservation( String dateDebut, String dateFin, int idHebergement, int idClient, float prix) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idHebergement = idHebergement;
        this.idClient = idClient;
        this.prix = prix;
    }

    public Reservation(int id, String dateDebut, String dateFin, int idHebergement, int idClient, float prix) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idHebergement = idHebergement;
        this.idClient = idClient;
        this.prix = prix;
    }

    // Getters pour les attributs de la classe Reservation
    public int getId() {
        return id;
    }
    public String getDateDebut() {
        return dateDebut;
    }
    public String getDateFin() {return dateFin;}
    public int getIdHebergement() {
        return idHebergement;
    }
    public int getIdClient() {
        return idClient;
    }
    public float getPrix() {
        return prix;
    }

    // Setters pour les attributs de la classe Reservation
    public void setId(int id) {
        this.id = id;
    }

    public Hebergement getHebergement() {
        return hebergement;
    }

    public void setHebergement(Hebergement hebergement) {
        this.hebergement = hebergement;
    }
}
