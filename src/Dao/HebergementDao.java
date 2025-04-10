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
     Options getOption(int id);
     String compreserListe(ArrayList<String> list);
     ArrayList<String> decompreserListe(String string);
}
