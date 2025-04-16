package Dao;

import MODELE.Reduction;

public interface ReductionDao {
    /*
    *Sauvegarde une nouvelle reduction dans la BDD
    @param Reduction sans id
     */
    void addReduction(Reduction reduction);
    /*
    * Return l'id d une Option dans la BDD
    * @param Reducion
    * @return reduction_id
     */
    int getreductionId(Reduction reduction);
    /*
    * Return une Reduction de la BDD via son id
    * @param reduction_id
    *@return Reduction
     */
    Reduction getReductionById(int id);
    /*
    * Supprime une reducion de la BDD
    * @param reduction_id
     */
    void deleteReduction(int id);
    /*
    * Modifie une Reduction dans la BDD
    * param Reduction
     */
    void updateReduction(Reduction reduction);
    /*
    * Verifie si un code de reduction est valide
    * @param Code de reduction
    * @return boolean
     */
    boolean checkReductionCode(String CodePromo);
}
