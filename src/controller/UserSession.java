package controller;

import modele.User;

/**
 * Singleton pour gérer la session de l'utilisateur connecté.
 * Permet de stocker et récupérer l'utilisateur actif dans l'application.
 */
public class UserSession {
    private static UserSession instance;
    private User connectedUser;

    /**
     * Constructeur privé pour empêcher l'instanciation externe (Pattern Singleton).
     */
    private UserSession() {
        // Constructeur privé pour singleton
    }

    /**
     * Retourne l'unique instance de {@code UserSession}.
     * Si elle n'existe pas encore, elle est créée.
     *
     * @return L'instance unique de {@code UserSession}.
     */
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    /**
     * Définit l'utilisateur actuellement connecté.
     *
     * @param user L'utilisateur à associer à la session.
     */
    public void setConnectedUser(User user) {
        this.connectedUser = user;
    }

    /**
     * Retourne l'utilisateur actuellement connecté.
     *
     * @return L'utilisateur connecté, ou {@code null} si aucun utilisateur n'est connecté.
     */
    public User getConnectedUser() {
        return connectedUser;
    }

    /**
     * Supprime l'utilisateur de la session en cours.
     * Réinitialise la session utilisateur.
     */
    public void clearSession() {
        connectedUser = null;
        System.out.println("Session user clear");
    }
}
