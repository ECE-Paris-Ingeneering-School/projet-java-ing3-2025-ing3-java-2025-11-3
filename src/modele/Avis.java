package modele;

public class Avis {
    private int id;
    private int note;
    private String commentaire;
    private int idHebergement;
    private int idClient;

    public Avis() {}

    public Avis(int note, String commentaire, int idHebergement, int idClient) {
        this.note = note;
        this.commentaire = commentaire;
        this.idHebergement = idHebergement;
        this.idClient = idClient;
    }

    public Avis(int id, int note, String commentaire, int idHebergement, int idClient) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.idHebergement = idHebergement;
        this.idClient = idClient;
    }

    // Getters pour les attributs de la classe Avis
    public int getId() {
        return id;
    }
    public int getNote() {
        return note;
    }
    public String getCommentaire() {
        return commentaire;
    }
    public int getIdHebergement() {
        return idHebergement;
    }
    public int getIdClient() {
        return idClient;
    }

    // Setters pour les attributs de la classe Avis
    public void setId(int id) {
        this.id = id;
    }
}
