package Dao;
import MODELE.Options;

public interface OptionDao {
    void ajouterOption(Options option);
    void supprimerOption(int id);
    Options getOptionById(int id);
    Options getOptionByNom(String nom);
    int getIdOption(String nom);


}
