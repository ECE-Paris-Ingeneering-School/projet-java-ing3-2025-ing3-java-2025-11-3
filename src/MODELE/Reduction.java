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
    public int getReducID() {
        return reducID;
    }
    public void setReducID(int reducID) {
        this.reducID = reducID;
    }
    public String getCodePromo() {
        return CodePromo;
    }
    public void setCodePromo(String codePromo) {
        CodePromo = codePromo;
    }
    public int getPourcentage() {
        return pourcentage;
    }

}
