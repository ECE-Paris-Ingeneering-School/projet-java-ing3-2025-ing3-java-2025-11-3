package modele;

/**
 * La classe Avis représente un avis déposé par un client sur un hébergement.
 */
public class Avis {
    private int id;
    private int note;
    private String commentaire;
    private int idHebergement;
    private int idClient;

    /**
     * Constructeur par défaut de la classe Avis.
     */
    public Avis() {}

    /**
     * Constructeur permettant de créer un avis sans identifiant initialisé.
     *
     * @param note          La note attribuée par le client.
     * @param commentaire   Le commentaire laissé par le client.
     * @param idHebergement L'identifiant de l'hébergement concerné.
     * @param idClient      L'identifiant du client ayant donné l'avis.
     */
    public Avis(int note, String commentaire, int idHebergement, int idClient) {
        this.note = note;
        this.commentaire = commentaire;
        this.idHebergement = idHebergement;
        this.idClient = idClient;
    }

    /**
     * Constructeur permettant de créer un avis avec un identifiant spécifique.
     *
     * @param id            L'identifiant de l'avis.
     * @param note          La note attribuée par le client.
     * @param commentaire   Le commentaire laissé par le client.
     * @param idHebergement L'identifiant de l'hébergement concerné.
     * @param idClient      L'identifiant du client ayant donné l'avis.
     */
    public Avis(int id, int note, String commentaire, int idHebergement, int idClient) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.idHebergement = idHebergement;
        this.idClient = idClient;
    }

    /**
     * Récupère l'identifiant de l'avis.
     *
     * @return L'identifiant de l'avis.
     */
    public int getId() {
        return id;
    }

    /**
     * Récupère la note attribuée dans l'avis.
     *
     * @return La note de l'avis.
     */
    public int getNote() {
        return note;
    }

    /**
     * Récupère le commentaire associé à l'avis.
     *
     * @return Le commentaire de l'avis.
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * Récupère l'identifiant de l'hébergement lié à l'avis.
     *
     * @return L'identifiant de l'hébergement.
     */
    public int getIdHebergement() {
        return idHebergement;
    }

    /**
     * Récupère l'identifiant du client ayant laissé l'avis.
     *
     * @return L'identifiant du client.
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * Modifie l'identifiant de l'avis.
     *
     * @param id Le nouvel identifiant de l'avis.
     */
    public void setId(int id) {
        this.id = id;
    }
}
