package view;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.beans.property.SimpleStringProperty;

public class ReservationView {

    private Scene scene;
    private NavBarView navBarView;
    private VBox reservationsCardsContainer;

    public interface ReservationActionHandler {
        void onView(Reservation reservation);
        void onCancel(Reservation reservation);
    }

    public ReservationView() {
        createUI();
    }

    private void createUI() {
        // Création de la barre de navigation (affichée en haut de la page)
        navBarView = new NavBarView();
        HBox navBar = navBarView.getNavBar();

        // En-tête : "Mon Espace Client" centré
        Label headerTitle = new Label("Mon Espace Client");
        headerTitle.setFont(new Font("Arial", 32));
        headerTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: #2C3E50;");
        headerTitle.setPadding(new Insets(10));
        HBox headerBox = new HBox(headerTitle);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(10));

        // Section "Mes réservations à venir"
        Label reservationsLabel = new Label("Mes réservations à venir");
        reservationsLabel.setFont(new Font("Arial", 28));
        reservationsLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #333333;");

        // Conteneur des cartes de réservation (largeur étendue)
        reservationsCardsContainer = new VBox();
        reservationsCardsContainer.setAlignment(Pos.CENTER);
        reservationsCardsContainer.setSpacing(20);


        // Conteneur pour les réservations
        VBox mainContainer = new VBox();
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setSpacing(20);
        mainContainer.setPadding(new Insets(20));
        mainContainer.setMaxWidth(800);
        mainContainer.getChildren().addAll(reservationsLabel, reservationsCardsContainer);



        // Section FAQ : conteneur étroit et centré avec label introductif "FAQ"
        Label faqLabel = new Label("FAQ");
        faqLabel.setFont(new Font("Arial", 20));
        faqLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2C3E50;");
        faqLabel.setPadding(new Insets(10));

        VBox faqContainer = new VBox();
        faqContainer.setAlignment(Pos.CENTER);
        faqContainer.setPadding(new Insets(20));
        faqContainer.setMaxWidth(500);
        Accordion faqAccordion = createFAQSection();
        faqContainer.getChildren().addAll(faqLabel, faqAccordion);

        // Conteneur central regroupant l'en-tête et les sections réservations et FAQ
        VBox centerContainer = new VBox();
        centerContainer.setAlignment(Pos.CENTER);
        centerContainer.setSpacing(20);
        centerContainer.getChildren().addAll(headerBox, mainContainer, faqContainer);
        centerContainer.setPadding(new Insets(20));

        // Layout global avec BorderPane : la navbar est placée en haut
        BorderPane root = new BorderPane();
        root.setTop(navBar);
        root.setCenter(centerContainer);
        root.setStyle("-fx-background-color: #F9F9F9;");

        // Encapsulation dans un ScrollPane pour permettre le défilement
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

    private Accordion createFAQSection() {
        Accordion accordion = new Accordion();

        TitledPane faq1 = new TitledPane();
        faq1.setText("Comment effectuer une réservation ?");
        Label answer1 = new Label("Pour effectuer une réservation, sélectionnez l'hébergement, choisissez vos dates et suivez les instructions de paiement.");
        answer1.setWrapText(true);
        faq1.setContent(answer1);
        faq1.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10; -fx-font-size: 14;");

        TitledPane faq2 = new TitledPane();
        faq2.setText("Quels modes de paiement sont acceptés ?");
        Label answer2 = new Label("Nous acceptons les paiements par carte bancaire, PayPal et virement bancaire.");
        answer2.setWrapText(true);
        faq2.setContent(answer2);
        faq2.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10; -fx-font-size: 14;");

        TitledPane faq3 = new TitledPane();
        faq3.setText("Comment modifier ou annuler une réservation ?");
        Label answer3 = new Label("Vous pouvez modifier ou annuler votre réservation depuis votre espace client, dans la section 'Mes réservations'.");
        answer3.setWrapText(true);
        faq3.setContent(answer3);
        faq3.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10; -fx-font-size: 14;");

        TitledPane faq4 = new TitledPane();
        faq4.setText("Que faire en cas de problème avec le paiement ?");
        Label answer4 = new Label("Contactez immédiatement notre service client via le formulaire de contact.");
        answer4.setWrapText(true);
        faq4.setContent(answer4);
        faq4.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10; -fx-font-size: 14;");

        TitledPane faq5 = new TitledPane();
        faq5.setText("Comment puis-je modifier mes informations personnelles ?");
        Label answer5 = new Label("Vous pouvez mettre à jour vos informations personnelles dans la section 'Mon profil' de votre espace client.");
        answer5.setWrapText(true);
        faq5.setContent(answer5);
        faq5.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10; -fx-font-size: 14;");

        accordion.getPanes().addAll(faq1, faq2, faq3, faq4, faq5);
        accordion.setStyle("-fx-background-color: transparent;");
        return accordion;
    }

    /**
     * Crée une carte de réservation présentant l'image à gauche, les détails au centre,
     * et une colonne de boutons d'action à droite avec des boutons de même taille.
     */
    private HBox createReservationCard(Reservation reservation, ReservationActionHandler handler) {
        HBox card = new HBox();
        card.setSpacing(20);
        card.setPadding(new Insets(15));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setMaxWidth(750);
        card.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 10;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0, 0, 2);");

        ImageView imageView = new ImageView();
        String imageUrl = reservation.getImageUrl();
        if (imageUrl == null || imageUrl.isEmpty()) {
            imageUrl = "https://via.placeholder.com/150?text=Illustration";
        }
        try {
            Image image = new Image(imageUrl, 150, 150, false, true);
            imageView.setImage(image);
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de l'image: " + e.getMessage());
        }
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);

        VBox detailsBox = new VBox();
        detailsBox.setSpacing(10);
        detailsBox.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label(reservation.getName());
        titleLabel.setFont(new Font("Arial", 20));
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2C3E50;");

        Label locationLabel = new Label(reservation.getLocation());
        locationLabel.setFont(new Font("Arial", 14));
        locationLabel.setStyle("-fx-text-fill: #7F8C8D;");

        Label datesLabel = new Label("Du " + reservation.getArrival() + " au " + reservation.getDeparture());
        datesLabel.setFont(new Font("Arial", 14));
        datesLabel.setStyle("-fx-text-fill: #34495E;");

        Label totalPriceLabel = new Label("Total : " + reservation.getTotalPrice());
        totalPriceLabel.setFont(new Font("Arial", 16));
        totalPriceLabel.setStyle("-fx-text-fill: #E74C3C; -fx-font-weight: bold;");

        Label pricePerNightLabel = new Label("Par nuit : " + reservation.getPricePerNight());
        pricePerNightLabel.setFont(new Font("Arial", 14));
        pricePerNightLabel.setStyle("-fx-text-fill: #E67E22;");

        detailsBox.getChildren().addAll(titleLabel, locationLabel, datesLabel, totalPriceLabel, pricePerNightLabel);
        HBox.setHgrow(detailsBox, Priority.ALWAYS);

        VBox buttonBox = new VBox();
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button viewButton = new Button("Consulter le bien");
        Button cancelButton = new Button("Annuler ma réservation");
        double buttonWidth = 150;
        viewButton.setPrefWidth(buttonWidth);
        cancelButton.setPrefWidth(buttonWidth);

        viewButton.setOnAction(e -> handler.onView(reservation));
        cancelButton.setOnAction(e -> handler.onCancel(reservation));

        buttonBox.getChildren().addAll(viewButton, cancelButton);
        card.getChildren().addAll(imageView, detailsBox, buttonBox);
        return card;
    }

    public Scene getScene() {
        return scene;
    }

    public NavBarView getNavBarView() {
        return navBarView;
    }

    /**
     * Classe interne pour représenter une réservation avec informations supplémentaires.
     */
    public static class Reservation {
        private final SimpleStringProperty name;
        private final SimpleStringProperty arrival;
        private final SimpleStringProperty departure;
        private final SimpleStringProperty totalPrice;
        private final SimpleStringProperty imageUrl;
        private final SimpleStringProperty location;
        private final SimpleStringProperty pricePerNight;
        private final int reservationId;

        public Reservation(int reservationId, String name, String arrival, String departure, String totalPrice,
                           List<String> imageUrls, String location, String pricePerNight) {
            this.reservationId = reservationId;
            this.name = new SimpleStringProperty(name);
            this.arrival = new SimpleStringProperty(arrival);
            this.departure = new SimpleStringProperty(departure);
            this.totalPrice = new SimpleStringProperty(totalPrice);
            this.imageUrl = new SimpleStringProperty(
                    (imageUrls != null && !imageUrls.isEmpty()) ? imageUrls.get(0) : ""
            );
            this.location = new SimpleStringProperty(location);
            this.pricePerNight = new SimpleStringProperty(pricePerNight);
        }

        public int getReservationId() {return reservationId;}

        public String getName() {
            return name.get();
        }
        public void setName(String name) {
            this.name.set(name);
        }

        public String getArrival() {
            return arrival.get();
        }
        public void setArrival(String arrival) {
            this.arrival.set(arrival);
        }

        public String getDeparture() {
            return departure.get();
        }
        public void setDeparture(String departure) {
            this.departure.set(departure);
        }

        public String getTotalPrice() {
            return totalPrice.get();
        }
        public void setTotalPrice(String totalPrice) {
            this.totalPrice.set(totalPrice);
        }

        public String getImageUrl() {
            return imageUrl.get();
        }
        public void setImageUrl(String imageUrl) {
            this.imageUrl.set(imageUrl);
        }

        public String getLocation() {
            return location.get();
        }
        public void setLocation(String location) {
            this.location.set(location);
        }

        public String getPricePerNight() {
            return pricePerNight.get();
        }
        public void setPricePerNight(String pricePerNight) {
            this.pricePerNight.set(pricePerNight);
        }
    }
}
