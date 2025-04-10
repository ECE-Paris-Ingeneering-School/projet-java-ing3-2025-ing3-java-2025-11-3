package Dao;

import MODELE.Hebergement;
import MODELE.Options;

import java.util.ArrayList;


public interface HebergementDao {

     void ajouterHebergement(Hebergement hebergement);
     int getIdHebergement(String nom);
     Hebergement getHebergement(int id);
     void supprimerHebergement(int id);
     void modifierHebergement(Hebergement hebergement);
     ArrayList<Options> getOption(int id_hebergement);
     String compreserListe(ArrayList<String> list);
     ArrayList<String> decompreserListe(String string);
     void ajouterOption(int id_hebergement, int id_option);
}
