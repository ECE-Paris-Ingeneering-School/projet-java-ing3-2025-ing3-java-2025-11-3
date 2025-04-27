package dao;
import modele.Options;

import javax.swing.text.html.Option;
import java.util.List;

public interface OptionDao {
    /*
    * Sauvegarde une nouvelle Option dans la BDD
    * @param Option sans id
     */
    void ajouterOption(Options option);
    /*
    * Supprime une Option dans la BDD
    * @param option_id
     */
    void supprimerOption(int id);
    /*
    * Return un Option via son id
    * @param option_id
    * @return Option
     */
    Options getOptionById(int id);
    /*
    * Return un Option via son nom
    * @param nom option
    * @return Option
     */
    Options getOptionByNom(String nom);
    /*
    * Return l id d une Option  via son nom
    * @param nom option
    * @return option_id
     */
    int getIdOption(String nom);

    List<Options> getAllOptions();
}
