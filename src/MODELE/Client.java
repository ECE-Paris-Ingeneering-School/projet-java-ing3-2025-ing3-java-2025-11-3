package MODELE;

public class Client extends User{
    private boolean newClient;

    public Client(String nom, String prenom, String email, String password, boolean newClient) {
        super(nom, prenom, email, password);
        this.newClient = newClient;
    }

    public Client(int id, String nom, String prenom, String email, String password, boolean newClient) {
        super(id, nom, prenom, email, password);
        this.newClient = newClient;
    }

    public boolean isNewClient() {
        return newClient;
    }

    

}
