package Dao;

// import des packages
import java.util.ArrayList;

/**
 * On utilise une interface UserDao pour définir les méthodes d'accès aux données de la table Users,
 * indépendamment de la méthode de stockage. On indique juste des noms de méthodes ici.
 */
public interface UserDAO {
    /**
     * Récupérer de la base de données tous les objets des utilisateurs dans une liste
     * @return : liste retournée des objets des utilisateurs récupérés
     */
    public ArrayList<User> getAll();

    /**
     Ajouter un nouveau utilisateur en paramètre dans la base de données
     @params : user = objet de User à insérer dans la base de données
     */
    public void ajouter(User user) ;

    /**
     * Permet de chercher et récupérer un objet de User dans la base de données via son id en paramètre
     * @param : id
     * @return : objet de classe User cherché et retourné
     */
    public User chercher(int id);

    /**
     * Permet de modifier les données du nom de l'objet de la classe User en paramètre
     * dans la base de données à partir de l'id de cet objet en paramètre
     * @param : user = objet en paramètre de la classe User à mettre à jour
     * @return : objet user en paramètre mis à jour  dans la base de données à retourner
     */
    public User modifier(User user);

    /**
     * Supprimer un objet de la classe User en paramètre dans la base de données en respectant la contrainte
     * d'intégrité référentielle : en supprimant un utilisateur, supprimer aussi en cascade toutes les commandes de la
     * table commander qui ont l'id de l'utilisateur supprimé.
     * @params : user = objet de User en paramètre à supprimer de la base de données
     */
    public void supprimer (User user);

}
