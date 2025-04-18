package modele;

public class Admin extends User{
    private int admin;

    public Admin(String nom, String prenom, String email, String password) {
        super(nom, prenom, email, password);
        this.admin = 1;
    }
    public Admin(int id, String nom, String prenom, String email, String password) {
        super(id, nom, prenom, email, password);
        this.admin = 1;
    }
}
