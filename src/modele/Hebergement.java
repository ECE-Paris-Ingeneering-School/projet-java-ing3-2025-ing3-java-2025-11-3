package modele;

import java.util.ArrayList;

/**
 * La classe Hebergement représente un hébergement disponible à la location,
 * avec ses caractéristiques, ses options, ses avis et ses images associées.
 */
public class Hebergement {
    private int Hid;
    private String nom;
    private int type;
    private String adresse;
    private String description;
    private int prix;
    private int note;
    private ArrayList<String> image;
    private ArrayList<Options> options;
    private ArrayList<Avis> avis;

    /**
     * Constructeur pour créer un hébergement sans identifiant.
     *
     * @param nom          Le nom de l'hébergement.
     * @param type         Le type d'hébergement.
     * @param adresse      L'adresse de l'hébergement.
     * @param description  La description de l'hébergement.
     * @param prix         Le prix de l'hébergement.
     * @param note         La note de l'hébergement.
     */
    public Hebergement(String nom, int type, String adresse, String description, int prix, int note) {
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.description = description;
        this.prix = prix;
        this.note = note;
        this.image = null;
        this.options = null;
        this.avis = null;
    }

    /**
     * Constructeur pour créer un hébergement avec un identifiant et sans avis, options ou images.
     *
     * @param Hid          L'identifiant de l'hébergement.
     * @param nom          Le nom de l'hébergement.
     * @param type         Le type d'hébergement.
     * @param adresse      L'adresse de l'hébergement.
     * @param description  La description de l'hébergement.
     * @param prix         Le prix de l'hébergement.
     */
    public Hebergement(int Hid, String nom, int type, String adresse, String description, int prix) {
        this.Hid = Hid;
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.description = description;
        this.prix = prix;
        this.note = 0;
        this.image = null;
        this.options = null;
        this.avis = null;
    }

    /**
     * Constructeur complet pour créer un hébergement avec toutes les informations.
     *
     * @param Hid          L'identifiant de l'hébergement.
     * @param nom          Le nom de l'hébergement.
     * @param type         Le type d'hébergement.
     * @param adresse      L'adresse de l'hébergement.
     * @param description  La description de l'hébergement.
     * @param prix         Le prix de l'hébergement.
     * @param note         La note de l'hébergement.
     * @param image        La liste des images associées à l'hébergement.
     * @param options      La liste des options disponibles pour l'hébergement.
     * @param avis         La liste des avis associés à l'hébergement.
     */
    public Hebergement(int Hid, String nom, int type, String adresse, String description, int prix, int note, ArrayList<String> image, ArrayList<Options> options, ArrayList<Avis> avis) {
        this.Hid = Hid;
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.description = description;
        this.prix = prix;
        this.note = note;
        this.image = image;
        this.options = options;
        this.avis = avis;
    }

    /**
     * Récupère l'identifiant de l'hébergement.
     *
     * @return L'identifiant de l'hébergement.
     */
    public int getHid() {
        return Hid;
    }

    /**
     * Récupère le nom de l'hébergement.
     *
     * @return Le nom de l'hébergement.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Récupère le type de l'hébergement.
     *
     * @return Le type de l'hébergement.
     */
    public int getType() {
        return type;
    }

    /**
     * Récupère l'adresse de l'hébergement.
     *
     * @return L'adresse de l'hébergement.
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Récupère la description de l'hébergement.
     *
     * @return La description de l'hébergement.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Récupère le prix de l'hébergement.
     *
     * @return Le prix de l'hébergement.
     */
    public int getPrix() {
        return prix;
    }

    /**
     * Récupère la note moyenne de l'hébergement.
     *
     * @return La note de l'hébergement.
     */
    public int getNote() {
        return note;
    }

    /**
     * Récupère la liste des options disponibles pour l'hébergement.
     *
     * @return La liste des options.
     */
    public ArrayList<Options> getOptions() {
        return options;
    }

    /**
     * Récupère la liste des avis liés à l'hébergement.
     *
     * @return La liste des avis.
     */
    public ArrayList<Avis> getAvis() {
        return avis;
    }

    /**
     * Modifie l'identifiant de l'hébergement.
     *
     * @param hid Le nouvel identifiant.
     */
    public void setHid(int hid) {
        Hid = hid;
    }

    /**
     * Modifie la liste des images de l'hébergement.
     *
     * @param image La nouvelle liste d'images.
     */
    public void setImage(ArrayList<String> image) {
        this.image = image;
    }

    /**
     * Modifie l'adresse de l'hébergement.
     *
     * @param adresse La nouvelle adresse.
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Modifie la description de l'hébergement.
     *
     * @param description La nouvelle description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Modifie la liste des options de l'hébergement.
     *
     * @param options La nouvelle liste d'options.
     */
    public void setOptions(ArrayList<Options> options) {
        this.options = options;
    }

    /**
     * Modifie la liste des avis de l'hébergement.
     *
     * @param avis La nouvelle liste d'avis.
     */
    public void setAvis(ArrayList<Avis> avis) {
        this.avis = avis;
    }

    /**
     * Récupère le nom du premier fichier image de l'hébergement, s'il existe.
     *
     * @return Le nom du premier fichier image, ou {@code null} s'il n'y en a pas.
     */
    public String getImageFilename() {
        if (image != null && !image.isEmpty()) {
            return image.getFirst(); // Renvoie le nom du premier fichier d'image
        }
        return null;
    }
}
