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

    private void showAlert(Alert.AlertType type, String msg) {
        Alert a = new Alert(type);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    public void show() {
        stage.setScene(view.getScene());
        stage.show();
    }
}
