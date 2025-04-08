package Dao;


import MODELE.Client;

public interface ClientDao {
    void modifierUnewClient(Client client);

    int getClient_State(int id);
}
