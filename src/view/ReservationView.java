package view;

import java.util.List;
import java.util.Optional;

import dao.HebergementDaoImpl;
import db.AzureDBConnector;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import modele.Reservation;
import modele.Avis;
import modele.Hebergement;

public class ReservationView {

    private Scene scene;
    private NavBarView navBarView;
    private VBox reservationsCardsContainer;
    private VBox pastReservationsCardsContainer;

    public interface ReservationActionHandler {
        void onView(Reservation reservation);
        void onCancel(Reservation reservation);
    }

    public ReservationView() {
        createUI();
    }

    private void createUI() {
        navBarView = new NavBarView();
        HBox navBar = navBarView.getNavBar();

        Label headerTitle = new Label("Mon Espace Client");
        headerTitle.setFont(new Font("Arial", 32));
        headerTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: #2C3E50;");
        headerTitle.setPadding(new Insets(10));
        HBox headerBox = new HBox(headerTitle);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(10));

        Label reservationsLabel = new Label("Mes réservations à venir");
        reservationsLabel.setFont(new Font("Arial", 28));
        reservationsLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #333333;");
        reservationsCardsContainer = new VBox(20);
        reservationsCardsContainer.setAlignment(Pos.CENTER);

        Label pastLabel = new Label("Mes réservations passées");
        pastLabel.setFont(new Font("Arial", 28));
        pastLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #333333;");
        pastReservationsCardsContainer = new VBox(20);
        pastReservationsCardsContainer.setAlignment(Pos.CENTER);

        VBox mainContainer = new VBox(20,
                reservationsLabel,
                reservationsCardsContainer,
                pastLabel,
                pastReservationsCardsContainer
        );
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(20));
        mainContainer.setMaxWidth(800);

        Label faqLabel = new Label("FAQ");
        faqLabel.setFont(new Font("Arial", 20));
        faqLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2C3E50;");
        faqLabel.setPadding(new Insets(10));

        Accordion faqAccordion = createFAQSection();
        VBox faqContainer = new VBox(20, faqLabel, faqAccordion);
        faqContainer.setAlignment(Pos.CENTER);
        faqContainer.setPadding(new Insets(20));
        faqContainer.setMaxWidth(500);

        VBox centerContainer = new VBox(20, headerBox, mainContainer, faqContainer);
        centerContainer.setAlignment(Pos.CENTER);
        centerContainer.setPadding(new Insets(20));

        BorderPane root = new BorderPane();
        root.setTop(navBar);
        root.setCenter(centerContainer);
        root.setStyle("-fx-background-color: #F9F9F9;");

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);

        scene = new Scene(scrollPane, 900, 700);
    }

    public void setReservations(List<Reservation> reservations, ReservationActionHandler handler) {
        reservationsCardsContainer.getChildren().clear();
        if (reservations.isEmpty()) {
            Label noReservationsLabel = new Label("Vous n'avez aucune réservation.");
            noReservationsLabel.setFont(new Font("Arial", 18));
            noReservationsLabel.setStyle("-fx-text-fill: #7F8C8D;");
            reservationsCardsContainer.getChildren().add(noReservationsLabel);
        } else {
            for (Reservation r : reservations) {
                HBox card = createReservationCard(r, handler);
                reservationsCardsContainer.getChildren().add(card);
            }
        }
    }

    public void setPastReservations(List<Reservation> pastReservations, ReservationActionHandler handler) {
        pastReservationsCardsContainer.getChildren().clear();
        if (pastReservations.isEmpty()) {
            Label none = new Label("Vous n'avez aucune réservation passée.");
            none.setFont(new Font("Arial", 18));
            none.setStyle("-fx-text-fill: #7F8C8D;");
            pastReservationsCardsContainer.getChildren().add(none);
        } else {
            for (Reservation r : pastReservations) {
                HBox card = createReservationCard(r, handler);
                // Remplace le texte du 2ᵉ bouton par "Évaluer"
                VBox buttonBox = (VBox) card.getChildren().get(2);
                Button secondBtn = (Button) buttonBox.getChildren().get(1);
                secondBtn.setText("Évaluer");
                pastReservationsCardsContainer.getChildren().add(card);
            }
        }
    }

    private Accordion createFAQSection() {
        Accordion accordion = new Accordion();

        TitledPane pane1 = new TitledPane(
                "Comment puis-je modifier une réservation ?",
                new Label("Pour modifier une réservation, rendez-vous dans la section 'Mes réservations à venir' et cliquez sur le bouton 'Consulter le bien' de la réservation concernée.")
        );

        TitledPane pane2 = new TitledPane(
                "Comment annuler une réservation ?",
                new Label("Cliquez sur 'Annuler ma réservation' dans la carte de réservation concernée. Notez que des frais d’annulation peuvent s’appliquer selon la politique de l’établissement.")
        );

        TitledPane pane3 = new TitledPane(
                "Quelles sont les méthodes de paiement acceptées ?",
                new Label("Nous acceptons les paiements par carte bancaire (Visa, MasterCard, American Express) et PayPal.")
        );

        TitledPane pane4 = new TitledPane(
                "Puis-je laisser un avis sur un hébergement ?",
                new Label("Oui, une fois votre séjour terminé, vous pourrez cliquer sur 'Évaluer' dans la section 'Mes réservations passées' pour laisser un commentaire et une note.")
        );

        TitledPane pane5 = new TitledPane(
                "Comment bénéficier des réductions ?",
                new Label("Les réductions sont automatiquement appliquées aux anciens clients lors de la réservation, selon les offres disponibles.")
        );

        accordion.getPanes().addAll(pane1, pane2, pane3, pane4, pane5);
        return accordion;
    }

    private HBox createReservationCard(Reservation reservation, ReservationActionHandler handler) {
        HBox card = new HBox(20);
        card.setPadding(new Insets(15));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setMaxWidth(750);
        card.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0, 0, 2);");

        ImageView imageView = new ImageView();
        String imageUrl = "";
        HebergementDaoImpl hebergementDao = new HebergementDaoImpl(new AzureDBConnector());
        Hebergement h = hebergementDao.getHebergement(reservation.getIdHebergement());

        imageUrl = "file:src/resources/images/" + h.getImageFilename();
        System.out.println("Image URL: " + imageUrl);
        if (imageUrl == null) {
            imageUrl = "file:src/resources/images/larry.jpg"; // Image par défaut
        }
        try {
            imageView.setImage(new Image(imageUrl, 150, 150, false, true));
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de l'image: " + e.getMessage());
        }
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);

        VBox detailsBox = new VBox(10);
        detailsBox.setAlignment(Pos.CENTER_LEFT);

        String title = h != null ? h.getNom() : "";
        String location = h != null ? h.getAdresse() : "";
        Label titleLabel = new Label(title);
        titleLabel.setFont(new Font("Arial", 20));
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2C3E50;");

        Label locationLabel = new Label(location);
        locationLabel.setFont(new Font("Arial", 14));
        locationLabel.setStyle("-fx-text-fill: #7F8C8D;");

        Label datesLabel = new Label("Du " + reservation.getDateDebut() + " au " + reservation.getDateFin());
        datesLabel.setFont(new Font("Arial", 14));
        datesLabel.setStyle("-fx-text-fill: #34495E;");

        Label priceLabel = new Label("Prix : " + reservation.getPrix() + " €");
        priceLabel.setFont(new Font("Arial", 16));
        priceLabel.setStyle("-fx-text-fill: #E74C3C; -fx-font-weight: bold;");

        detailsBox.getChildren().addAll(titleLabel, locationLabel, datesLabel, priceLabel);
        HBox.setHgrow(detailsBox, Priority.ALWAYS);

        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        Button viewButton = new Button("Consulter le bien");
        Button cancelButton = new Button("Annuler ma réservation");
        viewButton.setPrefWidth(150);
        cancelButton.setPrefWidth(150);
        viewButton.setOnAction(e -> handler.onView(reservation));
        cancelButton.setOnAction(e -> handler.onCancel(reservation));
        buttonBox.getChildren().addAll(viewButton, cancelButton);

        card.getChildren().addAll(imageView, detailsBox, buttonBox);
        return card;
    }

    public Optional<Avis> showEvaluationDialog(String propertyName,
                                               int idHebergement,
                                               int idClient) {
        Dialog<Avis> dialog = new Dialog<>();
        dialog.setTitle("Évaluer votre séjour");
        dialog.setHeaderText("Merci d’évaluer votre expérience à « " + propertyName + " »");

        ButtonType sendBtn = new ButtonType("Envoyer", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(sendBtn, ButtonType.CANCEL);

        Slider rating = new Slider(1, 5, 3);
        rating.setMajorTickUnit(1);
        rating.setMinorTickCount(0);
        rating.setSnapToTicks(true);
        rating.setShowTickLabels(true);
        rating.setShowTickMarks(true);

        TextArea comment = new TextArea();
        comment.setPromptText("Votre commentaire…");
        comment.setWrapText(true);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("Note (1–5) :"), 0, 0);
        grid.add(rating,               1, 0);
        grid.add(new Label("Commentaire :"), 0, 1);
        grid.add(comment,              1, 1);
        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(btn -> {
            if (btn == sendBtn) {
                return new Avis(
                        (int) rating.getValue(),
                        comment.getText(),
                        idHebergement,
                        idClient
                );
            }
            return null;
        });

        return dialog.showAndWait();
    }

    public Scene getScene() {
        return scene;
    }

    public NavBarView getNavBarView() {
        return navBarView;
    }
}
