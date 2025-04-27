package controller;

import dao.ClientDaoImpl;
import dao.HebergementDaoImpl;
import dao.ReservationDaoImpl;
import db.AzureDBConnector;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.*;
import view.*;
import javafx.scene.layout.Region;

import java.util.*;
import java.time.LocalDate;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class AdminController {

    private final Stage primaryStage;
    private final AdminView view;
    private final HebergementDaoImpl hebergementDao;
    private final ClientDaoImpl clientDao;
    private final ReservationDaoImpl reservationDao;
    private UserReservationsView viewUserReservation;

    public AdminController(Stage primaryStage) {
        AzureDBConnector azureDBConnector = new AzureDBConnector();

        this.primaryStage = primaryStage;
        this.view = new AdminView();
        this.hebergementDao = new HebergementDaoImpl(azureDBConnector);
        this.clientDao = new ClientDaoImpl(azureDBConnector);
        this.reservationDao = new ReservationDaoImpl(azureDBConnector);
        this.viewUserReservation = null;

        new NavBarController(primaryStage, view.getNavBarView());

        view.getLogementsLabel().setOnMouseClicked(e ->
                showHebergementsList()
        );



        view.getUtilisateursLabel().setOnMouseClicked(e ->
                showUsersList()
        );

        view.getReservationsPasseesLabel().setOnMouseClicked(e ->
                showPassedReservations()
        );

        view.getReservationsUtilisateurLabel().setOnMouseClicked(e -> {
            showReservationsList();
        });

        view.getAjouterLogementLabel().setOnMouseClicked(e -> {
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

                Hebergement hebergement = new Hebergement(nom, type, adresse, description, prix, note);
                hebergementDao.ajouterHebergement(hebergement);
                new AdminController(primaryStage).show();
            });

        });

        view.getAjouterOptionLabel().setOnMouseClicked(e -> {
            AddOptionView addHView = new AddOptionView();
            showSection(addHView.getRoot());
        });
    }

    private void showHebergementsList(){
        loadAsync(
                hebergementDao::getAllHebergements,
                list -> {
                    HebergementListView hv = new HebergementListView(list);

                    TableView<Hebergement> table = hv.getTable();

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
    }

    private void showUsersList() {
        loadAsync(
                clientDao::getAllClients,
                list -> {
                    UsersListView uv = new UsersListView(list);
                    showSection(uv.getRoot());
                }
        );
    }

    private void showPassedReservations() {
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
        );
    }

    private void showReservationsList() {
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
    }

    /**
     * Méthode générique pour charger des données en asynchrone.
     */
    private <T> void loadAsync(Callable<List<T>> fetchData, Consumer<List<T>> onSuccess) {

        ProgressIndicator pi = new ProgressIndicator();
        VBox loaderBox = new VBox(pi);
        loaderBox.setAlignment(Pos.CENTER);
        view.getContentPane().getChildren().setAll(loaderBox);

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

        showHebergementsList();
    }

    private void showSection(Region sectionRoot) {
        view.getContentPane().getChildren().setAll(sectionRoot);
    }
}
