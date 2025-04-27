package modele;

/**
 * La classe Reservation représente une réservation effectuée par un client pour un hébergement,
 * comprenant des informations telles que les dates de début et de fin, l'identifiant du client,
 * l'identifiant de l'hébergement, ainsi que le prix de la réservation.
 */
public class Reservation {
    private int id;
    private final String dateDebut;
    private final String dateFin;
    private final int idHebergement;
    private final int idClient;
    private final float prix;
    private Hebergement hebergement;

    /**
     * Constructeur pour créer une réservation avec les dates de début et de fin,
     * l'identifiant de l'hébergement, l'identifiant du client, et le prix.
     *
     * @param dateDebut    La date de début de la réservation.
     * @param dateFin      La date de fin de la réservation.
     * @param idHebergement L'identifiant de l'hébergement réservé.
     * @param idClient     L'identifiant du client ayant effectué la réservation.
     * @param prix         Le prix total de la réservation.
     */
    public Reservation(String dateDebut, String dateFin, int idHebergement, int idClient, float prix) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idHebergement = idHebergement;
        this.idClient = idClient;
        this.prix = prix;
    }

    /**
     * Constructeur pour créer une réservation avec un identifiant, les dates de début et de fin,
     * l'identifiant de l'hébergement, l'identifiant du client, et le prix.
     *
     * @param id           L'identifiant de la réservation.
     * @param dateDebut    La date de début de la réservation.
     * @param dateFin      La date de fin de la réservation.
     * @param idHebergement L'identifiant de l'hébergement réservé.
     * @param idClient     L'identifiant du client ayant effectué la réservation.
     * @param prix         Le prix total de la réservation.
     */
    public Reservation(int id, String dateDebut, String dateFin, int idHebergement, int idClient, float prix) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idHebergement = idHebergement;
        this.idClient = idClient;
        this.prix = prix;
    }

    /**
     * Récupère l'identifiant de la réservation.
     *
     * @return L'identifiant de la réservation.
     */
    public int getId() {
        return id;
    }

    /**
     * Récupère la date de début de la réservation.
     *
     * @return La date de début de la réservation.
     */
    public String getDateDebut() {
        return dateDebut;
    }

    /**
     * Récupère la date de fin de la réservation.
     *
     * @return La date de fin de la réservation.
     */
    public String getDateFin() {
        return dateFin;
    }

    /**
     * Récupère l'identifiant de l'hébergement réservé.
     *
     * @return L'identifiant de l'hébergement réservé.
     */
    public int getIdHebergement() {
        return idHebergement;
    }

    /**
     * Récupère l'identifiant du client ayant effectué la réservation.
     *
     * @return L'identifiant du client.
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * Récupère le prix total de la réservation.
     *
     * @return Le prix total de la réservation.
     */
    public float getPrix() {
        return prix;
    }

    /**
     * Modifie l'identifiant de la réservation.
     *
     * @param id L'identifiant de la réservation.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Récupère l'hébergement associé à la réservation.
     *
     * @return L'hébergement réservé.
     */
    public Hebergement getHebergement() {
        return hebergement;
    }

    /**
     * Modifie l'hébergement associé à la réservation.
     *
     * @param hebergement L'hébergement réservé.
     */
    public void setHebergement(Hebergement hebergement) {
        this.hebergement = hebergement;
    }
}
