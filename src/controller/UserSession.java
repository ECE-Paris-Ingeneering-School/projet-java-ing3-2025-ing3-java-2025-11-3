package controller;

import MODELE.User;

public class UserSession {
    private static UserSession instance;
    private User connectedUser;

    private UserSession() {
        // Constructeur privé pour singleton
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setConnectedUser(User user) {
        this.connectedUser = user;
    }

    public User getConnectedUser() {
        return connectedUser;
    }

    public void clearSession() {
        connectedUser = null;
        System.out.println("Session user clear");
    }
}
