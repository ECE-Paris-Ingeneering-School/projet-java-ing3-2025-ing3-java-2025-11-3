package modele;

/**
 * La classe Admin représente un utilisateur ayant des privilèges d'administrateur.
 * Elle hérite de la classe {@link User}.
 */
public class Admin extends User {
    /**
     * Indique si l'utilisateur est un administrateur.
     * La valeur est fixée à 1 pour un administrateur.
     */
    private int admin;

    /**
     * Constructeur permettant de créer un administrateur sans spécifier d'identifiant.
     *
     * @param nom      Le nom de l'administrateur.
     * @param prenom   Le prénom de l'administrateur.
     * @param email    L'adresse email de l'administrateur.
     * @param password Le mot de passe de l'administrateur.
     */
    public Admin(String nom, String prenom, String email, String password) {
        super(nom, prenom, email, password);
        this.admin = 1;
    }

    /**
     * Constructeur permettant de créer un administrateur avec un identifiant spécifique.
     *
     * @param id       L'identifiant unique de l'administrateur.
     * @param nom      Le nom de l'administrateur.
     * @param prenom   Le prénom de l'administrateur.
     * @param email    L'adresse email de l'administrateur.
     * @param password Le mot de passe de l'administrateur.
     */
    public Admin(int id, String nom, String prenom, String email, String password) {
        super(id, nom, prenom, email, password);
        this.admin = 1;
    }
}
