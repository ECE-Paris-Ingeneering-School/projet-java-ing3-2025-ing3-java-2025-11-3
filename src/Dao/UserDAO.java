package dao;

import modele.User;


public interface UserDao {

    void ajouterUser(User user);

    public int getIdUserByEmail(String email);

    User getUserByEmail(String email);

    void supprimerUser(int id);

    void modifierUser(User user);

    public User getUserByID(int id);

    boolean connexionUser(String mail, String mdp);
}