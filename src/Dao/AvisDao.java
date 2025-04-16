package Dao;

import MODELE.Avis;
import java.util.List;

public interface AvisDao {

    List<Avis> getAvisByHebergementId(int hebergementId);
    void saveAvis(Avis avis);
    int getIdAvis(int clientId, int produitId);
    Avis getAvisById(int id);
    void updateAvis(Avis avis);
    void deleteAvis(int id);


}
