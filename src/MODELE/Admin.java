package MODELE;

public class Admin extends User{
    private boolean admin;

    public Admin(String nom, String prenom, String email, String password) {
        super(nom, prenom, email, password);
        this.admin = true;
    }
    public Admin(int id, String nom, String prenom, String email, String password) {
        super(id, nom, prenom, email, password);
        this.admin = true;
    }
}
