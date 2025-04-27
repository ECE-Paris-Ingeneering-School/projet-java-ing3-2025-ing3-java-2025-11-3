package controller;

import dao.ClientDaoImpl;
import dao.HebergementDaoImpl;
import dao.OptionDaoImpl;
import dao.ReservationDaoImpl;
import db.AzureDBConnector;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modele.*;
import view.*;
import javafx.scene.layout.Region;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.time.LocalDate;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.stream.Collectors;



public class AdminController {

    private final Stage primaryStage;
    private final AdminView view;
    private final HebergementDaoImpl hebergementDao;
    private final ClientDaoImpl clientDao;
    private final ReservationDaoImpl reservationDao;
    private final OptionDaoImpl optionDao;
    private UserReservationsView viewUserReservation;
    private AddOptionView addOptionView;

    public AdminController(Stage primaryStage) {
        AzureDBConnector azureDBConnector = new AzureDBConnector();

        this.primaryStage = primaryStage;
        this.view = new AdminView();
        this.hebergementDao = new HebergementDaoImpl(azureDBConnector);
        this.clientDao = new ClientDaoImpl(azureDBConnector);
        this.reservationDao = new ReservationDaoImpl(azureDBConnector);
        this.optionDao = new OptionDaoImpl(azureDBConnector);
        this.viewUserReservation = null;
        this.addOptionView = null;

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

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Sélectionner une image pour le logement");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
            );
            addHView.getBtnChooseImage().setOnAction(ev -> {
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    addHView.setImagePath(file.getAbsolutePath());
                }
            });

            showSection(addHView.getRoot());
            addHView.getBtnSubmit().setOnAction(ev -> {
                try {
                    String nom         = addHView.getNomField().getText();
                    int type           = typeMap.getOrDefault(addHView.getType(), -1);
                    String adresse     = addHView.getAdresseField().getText();
                    String description = addHView.getDescriptionArea().getText();
                    int prix           = Integer.parseInt(addHView.getPrixField().getText());
                    int note           = addHView.getNoteSpin().getValue();

                    String srcPath = addHView.getImagePath();
                    ArrayList<String> images = new ArrayList<>();
                    if (srcPath != null && !srcPath.isBlank()) {
                        Path destDir  = Paths.get("src","resources", "images");
                        Files.createDirectories(destDir);
                        String ext      = srcPath.substring(srcPath.lastIndexOf('.'));
                        String fileName = UUID.randomUUID().toString() + ext;
                        Path destPath   = destDir.resolve(fileName);
                        Files.copy(Paths.get(srcPath), destPath);

                        images.add(fileName);
                    }

                    ArrayList<Options> options = new ArrayList<>();
                    ArrayList<Avis> avis        = new ArrayList<>();
                    Hebergement hebergement = new Hebergement(0,
                            nom, type, adresse,
                            description, prix, note,
                            images, options, avis
                    );

                    hebergementDao.ajouterHebergement(hebergement);

                    new AdminController(primaryStage).show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });


        });

        view.getAjouterOptionLabel().setOnMouseClicked(e -> {
            showAddOption();
        });
    }
    private void showAddOption(){
        loadAsync(
                hebergementDao::getAllHebergements,
                hebergements -> {
                    ArrayList<String> names = hebergements.stream()
                            .map(h -> h.getNom())
                            .collect(Collectors.toCollection(ArrayList::new));  // ici on obtient vraiment un ArrayList

                    addOptionView = new AddOptionView(names);
                    showSection(addOptionView.getRoot());

                    addOptionView.getBtnSubmit().setOnAction(ev -> {
                        // Récupération des valeurs
                        String nom = addOptionView.getNomField().getText();
                        String description = addOptionView.getDescriptionField().getText();
                        // Récupération de l'hébergement sélectionné
                        String selectedHebergement = addOptionView.getHebergementSelect();
                        int hebergementId = hebergementDao.getIdHebergement(selectedHebergement);
                        // Vérification de l'existence de l'option sinon ajout
                        Options option = new Options(nom,description);
                        optionDao.ajouterOption(option);
                        // Ajout de l'option à l'hébergement
                        hebergementDao.ajouterOption(hebergementId,optionDao.getIdOption(nom) );
                        addOptionView.resetFields();
                    });
                }
        );
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
                    ArrayList<String> names = clients.stream()
                            .map(c -> c.getNom() + " " + c.getPrenom())
                            .collect(Collectors.toCollection(ArrayList::new));  // ici on obtient vraiment un ArrayList

                    viewUserReservation = new UserReservationsView(names);

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
