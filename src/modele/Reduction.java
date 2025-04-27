package modele;

/**
 * La classe Reduction représente une réduction associée à un code promo
 * avec un identifiant, un code promo et un pourcentage de réduction.
 */
public class Reduction {
    private int reducID;
    private String CodePromo;
    private int pourcentage;

    /**
     * Constructeur pour créer une réduction avec un code promo et un pourcentage.
     *
     * @param CodePromo Le code promo associé à la réduction.
     * @param pourcentage Le pourcentage de réduction.
     */
    public Reduction(String CodePromo, int pourcentage) {
        this.CodePromo = CodePromo;
        this.pourcentage = pourcentage;
    }

    /**
     * Constructeur pour créer une réduction avec un identifiant, un code promo et un pourcentage.
     *
     * @param reducID   L'identifiant de la réduction.
     * @param CodePromo Le code promo associé à la réduction.
     * @param pourcentage Le pourcentage de réduction.
     */
    public Reduction(int reducID, String CodePromo, int pourcentage) {
        this.reducID = reducID;
        this.CodePromo = CodePromo;
        this.pourcentage = pourcentage;
    }

    /**
     * Récupère l'identifiant de la réduction.
     *
     * @return L'identifiant de la réduction.
     */
    public int getReducID() {
        return reducID;
    }

    /**
     * Modifie l'identifiant de la réduction.
     *
     * @param reducID L'identifiant de la réduction.
     */
    public void setReducID(int reducID) {
        this.reducID = reducID;
    }

    /**
     * Récupère le code promo associé à la réduction.
     *
     * @return Le code promo de la réduction.
     */
    public String getCodePromo() {
        return CodePromo;
    }

    /**
     * Modifie le code promo associé à la réduction.
     *
     * @param codePromo Le nouveau code promo.
     */
    public void setCodePromo(String codePromo) {
        CodePromo = codePromo;
    }

    /**
     * Récupère le pourcentage de la réduction.
     *
     * @return Le pourcentage de la réduction.
     */
    public int getPourcentage() {
        return pourcentage;
    }
}
