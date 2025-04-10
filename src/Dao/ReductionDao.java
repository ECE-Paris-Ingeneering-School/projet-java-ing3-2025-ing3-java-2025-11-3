package Dao;

import MODELE.Reduction;

public interface ReductionDao {

    void addReduction(Reduction reduction);
    int getreductionId(Reduction reduction);
    Reduction getReductionById(int id);
    void deleteReduction(int id);
    void updateReduction(Reduction reduction);
    boolean checkReductionCode(String CodePromo);
}
