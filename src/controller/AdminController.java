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

import java.util.List;
import java.util.Optional;


public class AdminController {

    private final Stage primaryStage;
    private final AdminView view;
    private final HebergementDaoImpl hebergementDao;
    private  final ClientDaoImpl clientDao;
    private final ReservationDaoImpl reservationDao;

    public AdminController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new AdminView();
        this.hebergementDao= new HebergementDaoImpl(new AzureDBConnector());
        this.clientDao = new ClientDaoImpl(new AzureDBConnector());
        this.reservationDao = new ReservationDaoImpl(new AzureDBConnector());

        // liaisons menu → affichage du stub correspondant
        view.getLogementsLabel().setOnMouseClicked(e -> {
            HebergementListView hebergementView =new HebergementListView();
            List<Hebergement> liste = hebergementDao.getAllHebergements();
            hebergementView.setHebergements(liste);
            showSection(hebergementView.getRoot());
        });
        view.getUtilisateursLabel().setOnMouseClicked(e -> {
            UsersListView usersListView = new UsersListView();
            List<Client> list = clientDao.getAllClients();
            usersListView.setUsers(list);
            showSection(usersListView.getRoot());
        });
        view.getReservationsPasseesLabel().setOnMouseClicked(e -> {
            ReservationHistoryView reservationHistoryView = new ReservationHistoryView();
            List<Reservation> list = reservationDao.getAllReservation();
            reservationHistoryView.set(list);
            showSection(new ReservationHistoryView().getRoot());
        });
        view.getReservationsUtilisateurLabel().setOnMouseClicked(e -> showSection(new UserReservationsView().getRoot()));
        view.getAjouterLogementLabel().setOnMouseClicked(e -> showSection(new AddHebergementView().getRoot()));
        view.getSupprimerLogementLabel().setOnMouseClicked(e -> showSection(new RemoveHebergementView().getRoot()));

        // lieu de retour vers la homepage
        view.getRetourButton().setOnAction(e -> new HomePageController(primaryStage).show());
    }

    public void show() {
        User currentUser = UserSession.getInstance().getConnectedUser();
        if (!(currentUser instanceof Admin)) {//currentUser == null || !(currentUser instanceof Admin)
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Veuillez-vous connecter avec un compte admin");
            Optional<ButtonType> result = alert.showAndWait();

            return;
        }
        primaryStage.setScene(view.getScene());
        primaryStage.show();
        // par défaut on charge la liste des logements
        showSection(new HebergementListView().getRoot());

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
