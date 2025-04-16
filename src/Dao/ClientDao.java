package Dao;


import MODELE.Client;

public interface ClientDao {

    /*
     * Modifie la variable Unew d'un client dans la BDD
     * @param Client
     */
    void modifierUnewClient(Client client);

    /*
        * Retourne la variable Unew d'un client dans la BDD
        * @param id
        * @return Unew
     */
    int getClient_State(int id);
}
