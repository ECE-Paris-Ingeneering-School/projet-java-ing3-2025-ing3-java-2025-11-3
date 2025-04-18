package modele;

public class Options {
    private int id;
    private String nom;
    private String description;
    public Options() {}

    public Options(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
    public Options(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    // Getters pour les attributs de la classe Options
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getDescription() {
        return description;
    }

    // Setters pour les attributs de la classe Options
    public void setId(int id) {
        this.id = id;
    }
}
