package controller;

import dao.ClientDaoImpl;
import dao.HebergementDaoImpl;
import dao.ReservationDaoImpl;
import db.AzureDBConnector;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.*;
import view.*;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class AdminController {

    private final Stage primaryStage;
    private final AdminView view;
    private final HebergementDaoImpl hebergementDao;
    private  final ClientDaoImpl clientDao;
    private final ReservationDaoImpl reservationDao;
    private  UserReservationsView viewUserReservation;

    public AdminController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new AdminView();
        this.hebergementDao= new HebergementDaoImpl(new AzureDBConnector());
        this.clientDao = new ClientDaoImpl(new AzureDBConnector());
        this.reservationDao = new ReservationDaoImpl(new AzureDBConnector());
        this.viewUserReservation=null;

        // initialisation listeResa donnee
        List<Hebergement> listeHebergement = hebergementDao.getAllHebergements();
        List<Client> listeClients = clientDao.getAllClients();
        ArrayList<String> usersName = new ArrayList<>();
        List<Reservation> listeResa = reservationDao.getAllReservation();

        new NavBarController(primaryStage, view.getNavBarView());

        // liaisons menu → affichage du stub correspondant
        view.getLogementsLabel().setOnMouseClicked(e -> {//Herbegements
            HebergementListView hebergementView =new HebergementListView(listeHebergement);
            showSection(hebergementView.getRoot());
        });
        view.getUtilisateursLabel().setOnMouseClicked(e -> {//Utilisateurs
            UsersListView usersListView = new UsersListView(listeClients);
            showSection(usersListView.getRoot());
        });
        view.getReservationsPasseesLabel().setOnMouseClicked(e -> {//Réservation passées TODO : ajouter affichage en focntion date
            ReservationHistoryView reservationHistoryView = new ReservationHistoryView(listeResa);
            showSection(reservationHistoryView.getRoot());
        });
        view.getReservationsUtilisateurLabel().setOnMouseClicked(e -> {
            for(Client client : listeClients) {
                usersName.add(client.getNom()+" "+client.getPrenom());
            }
            this.viewUserReservation= new UserReservationsView(usersName);

            viewUserReservation.getLoadBtn().setOnAction(ev -> {
                String selectedUser = viewUserReservation.getSelectedUser();
                System.out.println("Utilisateur choisi : " + selectedUser);

                Client client = listeClients.stream()
                        .filter(c -> (c.getNom() +" "+ c.getPrenom()).equals(selectedUser))
                        .findFirst()
                        .orElse(null);

                if (client != null) {
                    List<Reservation> resaDuClient = listeResa.stream()
                            .filter(r -> r.getIdClient() == client.getId())
                            .toList();
                    viewUserReservation.setTable(resaDuClient);
                }
            });

            showSection(viewUserReservation.getRoot());
        });
        view.getAjouterLogementLabel().setOnMouseClicked(e -> showSection(new AddHebergementView().getRoot()));
        view.getSupprimerLogementLabel().setOnMouseClicked(e -> showSection(new RemoveHebergementView().getRoot()));
    }

    public void show() {
        primaryStage.setScene(view.getScene());
        primaryStage.show();
        List<Hebergement> listeHebergement = hebergementDao.getAllHebergements();
        showSection(new HebergementListView(listeHebergement).getRoot());
    }

    private void showLoadingScreen() {
        ProgressIndicator loader = new ProgressIndicator();
        VBox container = new VBox(20, loader);
        container.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane(container);
        root.setStyle("-fx-background-color: #F9F9F9;");
        Scene scene = new Scene(root, 900, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showSection(Region sectionRoot) {
        view.getContentPane().getChildren().setAll(sectionRoot);
    }
}
