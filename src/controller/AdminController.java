package controller;

import dao.ClientDaoImpl;
import dao.HebergementDaoImpl;
import dao.ReservationDaoImpl;
import db.AzureDBConnector;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.*;
import view.*;
import javafx.scene.layout.Region;

import java.util.*;
import java.time.LocalDate;


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
        List<Reservation> listeResa = reservationDao.getAllReservation();

        new NavBarController(primaryStage, view.getNavBarView());

        // liaisons menu → affichage du stub correspondant
        view.getLogementsLabel().setOnMouseClicked(e -> {//Listes des herbegements
            HebergementListView hebergementView =new HebergementListView(listeHebergement);
            showSection(hebergementView.getRoot());
        });
        view.getUtilisateursLabel().setOnMouseClicked(e -> {//Utilisateurs
            UsersListView usersListView = new UsersListView(listeClients);
            showSection(usersListView.getRoot());
        });
        view.getReservationsPasseesLabel().setOnMouseClicked(e -> {//Réservation passées
            // Filtrer les réservations passées (fin avant aujourd’hui)
            LocalDate aujourdHui = LocalDate.now();
            List<Reservation> resaPassees = listeResa.stream()
                    .filter(resa -> {
                        try {
                            LocalDate dateFin = LocalDate.parse(resa.getDateFin());
                            return dateFin.isBefore(aujourdHui);
                        } catch (Exception ex) {
                            ex.printStackTrace(); // pour voir les erreurs de parsing
                            return false;
                        }
                    })
                    .toList();

            ReservationHistoryView reservationHistoryView = new ReservationHistoryView(resaPassees);
            showSection(reservationHistoryView.getRoot());
        });
        view.getReservationsUtilisateurLabel().setOnMouseClicked(e -> {//Réservation en fct du client choose
            ArrayList<String> usersName = new ArrayList<>();
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

        view.getAjouterLogementLabel().setOnMouseClicked(e -> {
            // Créer un dictionnaire type => ID
            Map<String, Integer> typeMap = new HashMap<>();
            typeMap.put("Hotel", 0);
            typeMap.put("Auberge", 1);
            typeMap.put("Appartement", 2);
            typeMap.put("Maison", 3);
            typeMap.put("Camping", 4);

            // Créer une liste pour l'affichage (combo box ou autre)
            ArrayList<String> types = new ArrayList<>(typeMap.keySet());

            AddHebergementView addHView = new AddHebergementView(types);

            showSection(addHView.getRoot());
            addHView.getBtnSubmit().setOnAction(ev -> {
                String nom = addHView.getNomField().getText();
                String selectedType = addHView.getType(); // Assure-toi que tu récupères bien la valeur sélectionnée
                int type = typeMap.getOrDefault(selectedType, -1); // Par sécurité, si jamais le type n'est pas trouvé
                String adresse = addHView.getAdresseField().getText();
                String description = addHView.getDescriptionArea().getText();
                int prix = Integer.parseInt(addHView.getPrixField().getText());
                int note = addHView.getNoteSpin().getValue();

                // Créer l'objet Hebergement
                Hebergement hebergement = new Hebergement(nom, type, adresse, description, prix, note);
                hebergementDao.ajouterHebergement(hebergement);
                new AdminController(primaryStage).show();
            });
        });

        view.getSupprimerLogementLabel().setOnMouseClicked(e -> {
            ArrayList<String> hebergements = new ArrayList<>();
            for(Hebergement hebergement : listeHebergement) {
                hebergements.add(hebergement.getNom());
            }
            RemoveHebergementView removeView= new RemoveHebergementView(hebergements);
            showSection(removeView.getRoot());

            removeView.deleteBtn().setOnAction(ev ->{
                String nomChoisi = removeView.getSelectLog().getValue();

                Optional<Hebergement> hebergementTrouve = listeHebergement.stream()
                        .filter(h -> h.getNom().equals(nomChoisi))
                        .findFirst();

                if (hebergementTrouve.isPresent()) {
                    int id = hebergementTrouve.get().getHid();
                    System.out.println("ID du logement sélectionné : " + id);
                    hebergementDao.supprimerHebergement(id);
                    new AdminController(primaryStage).show();
                } else {
                    System.out.println("Aucun hébergement trouvé avec ce nom !");
                }

            });
        });
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
