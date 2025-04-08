package Dao;

import MODELE.User;


public interface UserDao {


    void ajouterUser(User user);
    int getIdUser(String email);
    User getUser(int id);
    void supprimerUser(int id);
    void modifierUser(User user);


}
