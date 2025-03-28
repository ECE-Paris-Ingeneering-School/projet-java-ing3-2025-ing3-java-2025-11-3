package MODELE;

public class Reduction {
    private int reducID;
    private String CodePromo;
    private int pourcentage;

    public Reduction(String CodePromo, int pourcentage) {
        this.CodePromo = CodePromo;
        this.pourcentage = pourcentage;
    }
    public Reduction(int reducID, String CodePromo, int pourcentage) {
        this.reducID = reducID;
        this.CodePromo = CodePromo;
        this.pourcentage = pourcentage;
    }
}
