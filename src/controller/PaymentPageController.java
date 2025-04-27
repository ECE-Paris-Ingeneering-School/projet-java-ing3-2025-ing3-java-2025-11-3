package controller;

import dao.ReductionDao;
import dao.ReductionDaoImpl;
import dao.ReservationDao;
import dao.ReservationDaoImpl;
import db.AzureDBConnector;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import modele.Reservation;
import view.PaymentPageView;

import java.time.LocalDate;

/**
 * Contrôleur de la page de paiement.
 * Gère l'affichage du prix, l'application d'un code promotionnel, la validation ou l'annulation du paiement,
 * ainsi que la création d'une nouvelle réservation en base de données.
 */
public class PaymentPageController {
    private final Stage stage;
    private final Scene previousScene;
    private final PaymentPageView view;
    private final ReservationDao reservationDao;
    private final ReductionDao reductionDao;

    private final int hebergementId;
    private final int clientId;
    private final LocalDate dateArrivee;
    private final LocalDate dateDepart;
    private double currentPrice;
    private int appliedReductionId = -1;

    /**
     * Constructeur du PaymentPageController.
     * Initialise la vue de paiement, calcule le prix total, connecte aux bases de données de réservation et de réduction,
     * et configure les événements utilisateurs pour l'application d'un code promo, la validation ou l'annulation du paiement.
     *
     * @param stage La fenêtre principale de l'application.
     * @param previousScene La scène précédente à afficher en cas d'annulation.
     * @param hebergementId L'identifiant de l'hébergement réservé.
     * @param clientId L'identifiant du client effectuant la réservation.
     * @param dateArrivee La date d'arrivée prévue.
     * @param dateDepart La date de départ prévue.
     * @param initialPrice Le prix par nuit initial sans réduction.
     */
    public PaymentPageController(Stage stage,Scene previousScene, int hebergementId, int clientId, LocalDate dateArrivee, LocalDate dateDepart, double initialPrice) {
        this.stage = stage;
        this.previousScene = previousScene;
        this.hebergementId = hebergementId;
        this.clientId = clientId;
        this.dateArrivee = dateArrivee;
        this.dateDepart = dateDepart;
        this.currentPrice = initialPrice * (dateDepart.toEpochDay() - dateArrivee.toEpochDay());
        this.view = new PaymentPageView(initialPrice);
        this.reservationDao = new ReservationDaoImpl(new AzureDBConnector());
        this.reductionDao  = new ReductionDaoImpl(new AzureDBConnector());

        configureEvents();
    }

    /**
     * Configure les événements associés aux boutons de la page de paiement :
     * - Application d'un code promotionnel.
     * - Validation du paiement et enregistrement de la réservation.
     * - Annulation du paiement et retour à la scène précédente.
     */
    private void configureEvents() {
        view.getPriceLabel().setText(String.format("Montant à payer : %.2f €", currentPrice));

        view.getApplyPromoButton().setOnAction(e -> {
            String code = view.getPromoCodeField().getText().trim();
            if (code.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Veuillez saisir un code promo.");
                return;
            }
            boolean exists = reductionDao.checkReductionCode(code);
            if (!exists) {
                showAlert(Alert.AlertType.ERROR, "Code promo invalide.");
                return;
            }

            int redId = reductionDao.getreductionId(new modele.Reduction(code, 0));
            modele.Reduction red = reductionDao.getReductionById(redId);

            double taux = red.getPourcentage() / 100.0;
            currentPrice = currentPrice * (1 - taux);
            appliedReductionId = redId;
            view.getPriceLabel().setText(
                    String.format("Montant à payer (après %d%%) : %.2f €",
                            red.getPourcentage(), currentPrice)
            );
            showAlert(Alert.AlertType.INFORMATION, "Réduction appliquée : " + red.getPourcentage() + "%");
        });

        view.getConfirmButton().setOnAction(e -> {
            Reservation res = new Reservation(
                    dateArrivee.toString(),
                    dateDepart.toString(),
                    hebergementId,
                    clientId,
                    (float) currentPrice
            );
            reservationDao.nouvelleReservation(res);
            showAlert(Alert.AlertType.INFORMATION, "Réservation et paiement validés !");

            new controller.ReservationController(stage).show();
        });

        view.getCancelButton().setOnAction(e -> {
            stage.setScene(previousScene);
        });
    }

    /**
     * Affiche une alerte de type spécifié avec un message donné.
     *
     * @param type Le type d'alerte (INFORMATION, WARNING, ERROR, etc.).
     * @param msg Le message à afficher dans l'alerte.
     */
    private void showAlert(Alert.AlertType type, String msg) {
        Alert a = new Alert(type);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    /**
     * Affiche la scène de la page de paiement.
     * Met à jour la scène de la fenêtre principale avec la vue de paiement.
     */
    public void show() {
        stage.setScene(view.getScene());
        stage.show();
    }
}
