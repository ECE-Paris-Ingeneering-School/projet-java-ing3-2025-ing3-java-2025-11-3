package Dao;

import MODELE.User;


public interface UserDao {

    /*
        * Sauvegarde un nouvel user dans la BDD
        * @param user
     */
    void ajouterUser(User user);
    /*
        * Return l'id d un user dans la BDD
        * @param email
        * @return id
     */
    int getIdUser(String email);
    /*
        * Return un user de la BDD via son id
        * @param id
        * @return user
     */
    User getUser(int id);
    /*
        * Supprime un user de la BDD
        * @param id
     */
    void supprimerUser(int id);
    /*
        * Modifie un user de la BDD
        * @param User
     */
    void modifierUser(User user);

    /*
     * return true si l'user existe pour les identifiants donnee
     * @param mail, mdp
     */
    boolean connexionUser(String mail, String mdp);

}
