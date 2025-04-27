package modele;

/**
 * La classe Client représente un utilisateur classique du système.
 * Elle hérite de la classe {@link User}.
 */
public class Client extends User {
    private int newClient;

    /**
     * Constructeur permettant de créer un client sans identifiant spécifique.
     *
     * @param nom        Le nom du client.
     * @param prenom     Le prénom du client.
     * @param email      L'adresse email du client.
     * @param password   Le mot de passe du client.
     * @param newClient  Indique si le client est nouveau (par exemple 1 pour oui, 0 pour non).
     */
    public Client(String nom, String prenom, String email, String password, int newClient) {
        super(nom, prenom, email, password);
        this.newClient = newClient;
    }

    /**
     * Constructeur permettant de créer un client avec un identifiant spécifique.
     *
     * @param id         L'identifiant du client.
     * @param nom        Le nom du client.
     * @param prenom     Le prénom du client.
     * @param email      L'adresse email du client.
     * @param password   Le mot de passe du client.
     * @param newClient  Indique si le client est nouveau (par exemple 1 pour oui, 0 pour non).
     */
    public Client(int id, String nom, String prenom, String email, String password, int newClient) {
        super(id, nom, prenom, email, password);
        this.newClient = newClient;
    }

    /**
     * Vérifie si le client est un nouveau client.
     *
     * @return 1 si le client est nouveau, sinon 0.
     */
    public int isNewClient() {
        return newClient;
    }

    /**
     * Modifie l'état "nouveau client" du client.
     *
     * @param newClient 1 pour indiquer un nouveau client, 0 sinon.
     */
    public void setNewClient(int newClient) {
        this.newClient = newClient;
    }
}
