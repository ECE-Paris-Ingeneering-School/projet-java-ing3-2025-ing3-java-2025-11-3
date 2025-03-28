package MODELE;

import java.util.ArrayList;

public class Hebergement {
    private int Hid;
    private String nom;
    private String type;
    private String adresse;
    private String description;
    private int prix;
    private int note;
    private ArrayList<String> image;
    private ArrayList<Options> options;
    private ArrayList<Avis> avis;

    public Hebergement( String nom, String type, String adresse, String description, int prix){
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.description = description;
        this.prix = prix;
        // set a default value for note, image, options and avis
        this.note = 0;
        this.image = null;
        this.options = null;
        this.avis = null;

    }
    public Hebergement(int Hid, String nom, String type, String adresse, String description, int prix, int note, ArrayList<String> image, ArrayList<Options> options, ArrayList<Avis> avis){
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
    public String getType() {
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
    public ArrayList<String> getImage() {
        return image;
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

    public void setOptions(ArrayList<Options> options) {
        this.options = options;
    }

    public void setAvis(ArrayList<Avis> avis) {
        this.avis = avis;
    }
}
