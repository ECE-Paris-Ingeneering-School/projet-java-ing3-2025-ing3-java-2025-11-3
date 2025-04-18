package dao;

import modele.Avis;

public interface AvisDao {
    /*
    * Sauvegarde un nouvel avis dans la BDD
    * @param Avis sans id
     */


    void saveAvis(Avis avis);
    /*
    * Recherche un avis par son id dans la BDD
    * @param id client
    * @param id produit
    * @return l'avis
     */
    int getIdAvis(int clientId, int produitId);
    /*
    * fonction de recherche d'un avis par son id dans la BDD
    * @param id d un Avis
    * @return l'avis
     */
    Avis getAvisById(int id);
    /*
    * Modifie un avis dans la BDD
    * @param Avis
     */
    void updateAvis(Avis avis);
    /*
    * fonction pour supprimer un avis dans la BDD
    * @param id d un Avis
     */
    void deleteAvis(int id);
    /*
    * Return tous les Avis d un hebergement dans la BDD
    * @param hebergement_id
    * @return liste des avis
     */
}
