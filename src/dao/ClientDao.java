package dao;


import modele.Client;

import java.util.List;

public interface ClientDao {

    /*
     * Modifie la variable Usernew d'un client dans la BDD
     * @param Client
     */
    void modifierUnewClient(Client client);

    /*
     * Retourne la variable Usernew d'un client dans la BDD
     * @param id
     * @return Unew
     */
    int getClient_State(int id);

    List<Client> getAllClients();
}
