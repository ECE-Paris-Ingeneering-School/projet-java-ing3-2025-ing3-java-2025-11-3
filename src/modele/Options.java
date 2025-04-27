package modele;

/**
 * La classe Options représente les options disponibles pour un hébergement,
 * comprenant un identifiant, un nom et une description.
 */
public class Options {
    private int id;
    private String nom;
    private String description;

    /**
     * Constructeur par défaut pour la classe Options.
     * Permet de créer une instance d'Options sans initialiser les attributs.
     */
    public Options() {}

    /**
     * Constructeur pour créer une option avec un nom et une description.
     *
     * @param nom        Le nom de l'option.
     * @param description La description de l'option.
     */
    public Options(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    /**
     * Constructeur pour créer une option avec un identifiant, un nom et une description.
     *
     * @param id         L'identifiant de l'option.
     * @param nom        Le nom de l'option.
     * @param description La description de l'option.
     */
    public Options(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    /**
     * Récupère l'identifiant de l'option.
     *
     * @return L'identifiant de l'option.
     */
    public int getId() {
        return id;
    }

    /**
     * Récupère le nom de l'option.
     *
     * @return Le nom de l'option.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Récupère la description de l'option.
     *
     * @return La description de l'option.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifie l'identifiant de l'option.
     *
     * @param id Le nouvel identifiant de l'option.
     */
    public void setId(int id) {
        this.id = id;
    }
}
