package modele;

/**
 * La classe User représente un utilisateur avec des informations telles que son identifiant,
 * son nom, son prénom, son adresse e-mail et son mot de passe.
 */
public class User {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;

    /**
     * Constructeur pour créer un utilisateur avec un nom, un prénom, un e-mail et un mot de passe.
     *
     * @param nom      Le nom de l'utilisateur.
     * @param prenom   Le prénom de l'utilisateur.
     * @param email    L'adresse e-mail de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     */
    public User(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructeur pour créer un utilisateur avec un identifiant, un nom, un prénom,
     * un e-mail et un mot de passe.
     *
     * @param id       L'identifiant de l'utilisateur.
     * @param nom      Le nom de l'utilisateur.
     * @param prenom   Le prénom de l'utilisateur.
     * @param email    L'adresse e-mail de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     */
    public User(int id, String nom, String prenom, String email, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }

    /**
     * Récupère l'identifiant de l'utilisateur.
     *
     * @return L'identifiant de l'utilisateur.
     */
    public int getId() {
        return id;
    }

    /**
     * Récupère le nom de l'utilisateur.
     *
     * @return Le nom de l'utilisateur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Récupère le prénom de l'utilisateur.
     *
     * @return Le prénom de l'utilisateur.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Récupère l'adresse e-mail de l'utilisateur.
     *
     * @return L'adresse e-mail de l'utilisateur.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Récupère le mot de passe de l'utilisateur.
     *
     * @return Le mot de passe de l'utilisateur.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Modifie l'identifiant de l'utilisateur.
     *
     * @param id L'identifiant de l'utilisateur.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Modifie le nom de l'utilisateur.
     *
     * @param nom Le nom de l'utilisateur.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Modifie le prénom de l'utilisateur.
     *
     * @param prenom Le prénom de l'utilisateur.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Modifie l'adresse e-mail de l'utilisateur.
     *
     * @param email L'adresse e-mail de l'utilisateur.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Modifie le mot de passe de l'utilisateur.
     *
     * @param password Le mot de passe de l'utilisateur.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
