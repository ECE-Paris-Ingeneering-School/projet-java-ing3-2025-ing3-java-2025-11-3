package modele;

import java.util.ArrayList;

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

    public Hebergement(String nom, int type, String adresse, String description, int prix, int note){
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

    public Hebergement(int Hid,String nom, int type, String adresse, String description, int prix){
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
    public Hebergement(int Hid, String nom, int type, String adresse, String description, int prix, int note, ArrayList<String> image, ArrayList<Options> options, ArrayList<Avis> avis){
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

    // Getters pour les attributs de la classe Hebergement
    public int getHid() {
        return Hid;
    }
    public String getNom() {
        return nom;
    }
    public int getType() {
        return type;
    }
    public String getAdresse() {
        return adresse;
    }
    public String getDescription() {
        return description;
    }
    public int getPrix() {
        return prix;
    }
    public int getNote() {
        return note;
    }

    public ArrayList<Options> getOptions() {
        return options;
    }
    public ArrayList<Avis> getAvis() {
        return avis;
    }

    //setters
    public void setHid(int hid) {
        Hid = hid;
    }
    public void setImage(ArrayList<String> image) {
        this.image = image;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setOptions(ArrayList<Options> options) {
        this.options = options;
    }

    public void setAvis(ArrayList<Avis> avis) {
        this.avis = avis;
    }

    public String getImageFilename() {
        if (image != null && !image.isEmpty()) {
            return image.getFirst(); // Renvoie le nom du premier fichier d'image
        }
        return null; // Ou une valeur par défaut si aucune image n'est disponible
    }
}
