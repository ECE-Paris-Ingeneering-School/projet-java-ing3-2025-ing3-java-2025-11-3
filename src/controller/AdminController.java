package controller;

import dao.ClientDaoImpl;
import dao.HebergementDaoImpl;
import dao.ReservationDaoImpl;
import db.AzureDBConnector;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.*;
import view.*;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Consumer;


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

        new NavBarController(primaryStage, view.getNavBarView());

        loadAsync(
                hebergementDao::getAllHebergements,
                list -> {
                    HebergementListView hv = new HebergementListView(list);

                    TableView<Hebergement> table = hv.getTable();

                    //Créer la colonne Action avec bouton Supprimer
                    TableColumn<Hebergement, Void> actionCol = new TableColumn<>("Action");
                    actionCol.setCellFactory(col -> new TableCell<>() {
                        private final Button deleteBtn = new Button("Supprimer");
                        {
                            deleteBtn.setOnAction(e -> {
                                Hebergement h = getTableView().getItems().get(getIndex());
                                hebergementDao.supprimerHebergement(h.getHid());
                                table.getItems().remove(h);
                            });
                        }
                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            setGraphic(empty ? null : deleteBtn);
                        }
                    });

                    table.getColumns().add(actionCol);

                    showSection(hv.getRoot());
                }
        );


        view.getUtilisateursLabel().setOnMouseClicked(e ->
                loadAsync(
                        clientDao::getAllClients,
                        list -> {
                            UsersListView uv = new UsersListView(list);
                            showSection(uv.getRoot());
                        }
                )
        );

        view.getReservationsPasseesLabel().setOnMouseClicked(e ->
                loadAsync(
                        () -> reservationDao.getAllReservation().stream()
                                .filter(r -> {
                                    try {
                                        return LocalDate.parse(r.getDateFin())
                                                .isBefore(LocalDate.now());
                                    } catch (Exception ex) {
                                        return false;
                                    }
                                })
                                .toList(),
                        list -> {
                            ReservationHistoryView rhv = new ReservationHistoryView(list);
                            showSection(rhv.getRoot());
                        }
                )
        );

        view.getReservationsUtilisateurLabel().setOnMouseClicked(e -> {
            loadAsync(
                    clientDao::getAllClients,
                    clients -> {
                        List<String> names = clients.stream()
                                .map(c -> c.getNom() + " " + c.getPrenom())
                                .toList();
                        viewUserReservation = new UserReservationsView((ArrayList<String>) names);

                        viewUserReservation.getLoadBtn().setOnAction(ev -> {
                            String sel = viewUserReservation.getSelectedUser();
                            clients.stream()
                                    .filter(c -> (c.getNom() + " " + c.getPrenom()).equals(sel))
                                    .findFirst()
                                    .ifPresent(client -> {
                                        loadAsync(
                                                () -> reservationDao.getAllReservationByClientId(client.getId()),
                                                resas -> viewUserReservation.setTable(resas)
                                        );
                                    });
                        });
                        showSection(viewUserReservation.getRoot());
                    }
            );
        });

        view.getAjouterLogementLabel().setOnMouseClicked(e -> {
            AddHebergementView addHView = new AddHebergementView();
            showSection(addHView.getRoot());
            addHView.getBtnSubmit().setOnAction(ev -> {
                String nom = addHView.getNomField().getText();
                int type = addHView.getTypeBox().getValue();
                String adresse = addHView.getAdresseField().getText();
                String description = addHView.getDescriptionArea().getText();
                int prix = Integer.parseInt(addHView.getPrixField().getText());
                int note = addHView.getNoteSpin().getValue();

                Hebergement hebergement = new Hebergement(nom, type, adresse, description, prix, note);
                hebergementDao.ajouterHebergement(hebergement);
                new AdminController(primaryStage).show();
            });

        });
    }

    /**
     * Méthode générique pour charger des données en asynchrone.
     */
    private <T> void loadAsync(Callable<List<T>> fetchData, Consumer<List<T>> onSuccess) {
        Task<List<T>> task = new Task<>() {
            @Override
            protected List<T> call() throws Exception {
                return fetchData.call();
            }

            @Override
            protected void succeeded() {
                onSuccess.accept(getValue());
            }

            @Override
            protected void failed() {
                Throwable ex = getException();
                System.err.println("Erreur lors du chargement : " + ex.getMessage());
                ex.printStackTrace();
            }
        };

        Thread thread = new Thread(task, "Loader-Thread");
        thread.setDaemon(true);
        thread.start();
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
