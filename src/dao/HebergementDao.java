package dao;

import modele.Avis;
import modele.Hebergement;
import modele.Options;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public interface HebergementDao {
     /*
     * Sauvegarde un nouvel hebergement sans id dans la BDD
     * @param Hebergement
      */
     void ajouterHebergement(Hebergement hebergement);
     /*
     * Return ID d un Hebergement dans la BDD
     * @param nom de l hebergement
     * @return hebergement_id
      */
     int getIdHebergement(String nom);
     /*
     * Return tous les hebergements dans la BDD
     * @return ArrayList<Hebergement> Hebergements
      */
     ArrayList<Hebergement> getAllHebergements();
     /*
     * Return un hebergement obtenu via son id dans la BDD
     * @param id: hebergement id
     * @return Hebergement
      */
     Hebergement getHebergement(int id);
     /*
     * Supprime un Hebergment dans la BDD
     * @param id: hebergement_id
      */
     void supprimerHebergement(int id);
     /*
     * Modifie un Hebergement dans la BDD
     * @param Hebergement avec id
      */
     void modifierHebergement(Hebergement hebergement);
     /*
     * Return la liste des option de l hebergement dans la BDD
     * @param hebergement_id
     * @return liste des options
      */
     ArrayList<Options> getOption(int id_hebergement);
     /*
      * Return la liste des avis de l hebergement dans la BDD
      * @param hebergement_id
      * @return liste des avis
      */
     ArrayList<Avis> getAllAvis(int id_hebergement);
     /*
     * Convertion d'une liste en un String
     * @param liste de String
     * @return String
      */
     String compreserListe(ArrayList<String> list);
     /*
      * Convertion d'un String en une Liste
      * @param liste de String
      * @return String
      */
     ArrayList<String> decompreserListe(String string);
     /*
     * Ajoute une Option a un hebergement dans la BDD
     * @param hebergement_id
     * @param option_id
      */
     void ajouterOption(int id_hebergement, int id_option);

     public List<Hebergement> getFilteredHebergements(List<String> types, int prixMax, String rechercheTexte, LocalDate dateArrivee, LocalDate dateDepart);

}