package Dao;

import MODELE.Avis;

public interface AvisDao {

    void saveAvis(Avis avis);
    int getIdAvis(int clientId, int produitId);
    Avis getAvisById(int id);
    void updateAvis(Avis avis);
    void deleteAvis(int id);


}
