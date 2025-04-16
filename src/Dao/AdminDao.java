package Dao;

import MODELE.Admin;

import java.util.List;


public interface AdminDao {
    /*
        * Sauvegarde un nouvel admin sans id dans la BDD
        * @param admin The admin object to be created.
     */
    void createAdmin(Admin admin);

    /*
        * Return tous les admins dans la BDD
        * @param none
        * @return list of all admins
     */
    List<Integer> getAllAdmins();

}
