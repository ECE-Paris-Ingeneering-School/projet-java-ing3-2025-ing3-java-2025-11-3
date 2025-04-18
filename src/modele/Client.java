package modele;

public class Client extends User{
    private int newClient;

    public Client(String nom, String prenom, String email, String password, int newClient) {
        super(nom, prenom, email, password);
        this.newClient = newClient;
    }

    public Client(int id, String nom, String prenom, String email, String password, int newClient) {
        super(id, nom, prenom, email, password);
        this.newClient = newClient;
    }

    public int isNewClient() {
        return newClient;
    }
    public void setNewClient(int newClient) {
        this.newClient = newClient;
    }

    

}
