package controller;

import dao.HebergementDao;
import dao.ReservationDao;
import dao.HebergementDaoImpl;
import dao.ReservationDaoImpl;
import db.AzureDBConnector;
import modele.Avis;
import modele.Options;
import modele.Hebergement;
import modele.Reservation;
import view.BookingPageView;
import view.NavBarView;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Controller for the booking page: loads data, configures UI, and manages reservations.
 */
public class BookingPageController {

    private final Stage primaryStage;
    private BookingPageView view;
    private final HebergementDao hebergementDao;
    private final ReservationDao reservationDao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * Package for holding data loaded for booking.
     */
    private static class BookingData {
        final List<Avis> avis;
        final List<Options> options;
        final List<Reservation> reservations;

        BookingData(List<Avis> avis, List<Options> options, List<Reservation> reservations) {
            this.avis = avis;
            this.options = options;
            this.reservations = reservations;
        }
    }

    /**
     * Creates controller, shows loading screen, and initiates data fetch.
     *
     * @param primaryStage the application stage
     * @param hebergement  the accommodation to book
     */
    public BookingPageController(Stage primaryStage, Hebergement hebergement) {
        this.primaryStage = primaryStage;
        this.hebergementDao = new HebergementDaoImpl(new AzureDBConnector());
        this.reservationDao = new ReservationDaoImpl(new AzureDBConnector());

        showLoadingScreen();
        loadBookingData(hebergement);
    }

    /**
     * Displays a simple loading indicator scene.
     */
    private void showLoadingScreen() {
        ProgressIndicator loader = new ProgressIndicator();
        VBox container = new VBox(loader);
        container.setAlignment(javafx.geometry.Pos.CENTER);
        Scene scene = new Scene(container, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Asynchronously fetches booking data and initializes the view upon success.
     */
    private void loadBookingData(Hebergement hebergement) {
        Task<BookingData> task = new Task<>() {
            @Override
            protected BookingData call() {
                List<Avis> avisList = hebergementDao.getAllAvis(hebergement.getHid());
                List<Options> optionsList = hebergementDao.getOption(hebergement.getHid());
                List<Reservation> reservationList = reservationDao.getAllReservationByHebergementId(hebergement.getHid());
                return new BookingData(avisList, optionsList, reservationList);
            }

            @Override
            protected void succeeded() {
                BookingData data = getValue();
                view = new BookingPageView(hebergement, data.avis, data.options);
                configureEventHandlers();
                configureDatePickers(data.reservations);
                primaryStage.setScene(view.getScene());
                primaryStage.show();
            }

            @Override
            protected void failed() {
                System.err.println("Error loading booking data: " + getException());
            }
        };
        executor.submit(task);
    }

    /**
     * Sets up UI controls and navigation links.
     */
    private void configureEventHandlers() {
        NavBarView nav = view.getNavBarView();
        nav.getTitleLabel().setOnMouseClicked(e -> new HomePageController(primaryStage).show());
        nav.getSignInLabel().setOnMouseClicked(e -> new LoginPageController(primaryStage).show());
        nav.getRegisterLabel().setOnMouseClicked(e -> new RegisterPageController(primaryStage).show());
        nav.getRechercheLabel().setOnMouseClicked(e -> new SearchPageController(primaryStage).show());
        nav.getReservationsLabel().setOnMouseClicked(e -> new ReservationController(primaryStage).show());

        view.getBackButton().setOnAction(e -> new SearchPageController(primaryStage).show());
        view.getReserverButton().setDisable(true);

        view.getDateArriveePicker().valueProperty().addListener((obs, oldV, newV) -> checkDatesSelected());
        view.getDateDepartPicker().valueProperty().addListener((obs, oldV, newV) -> checkDatesSelected());
        view.getReserverButton().setOnAction(e -> makeReservation());
    }

    /**
     * Disables unavailable dates and sets up interdependent date pickers.
     *
     * @param reservations list of existing reservations
     */
    private void configureDatePickers(List<Reservation> reservations) {
        Set<LocalDate> takenDates = extractReservedDates(reservations);

        // Disable past and taken dates for arrival and departure
        view.getDateArriveePicker().setDayCellFactory(picker -> createDateCell(takenDates, LocalDate.MIN, LocalDate.MAX));
        view.getDateDepartPicker().setDayCellFactory(picker -> createDateCell(takenDates, LocalDate.MIN, LocalDate.MAX));

        // Constrain departure based on arrival selection
        view.getDateArriveePicker().valueProperty().addListener((obs, oldV, newV) -> {
            view.getDateDepartPicker().setDayCellFactory(picker -> createDateCell(takenDates, newV, LocalDate.MAX));
        });
        view.getDateDepartPicker().valueProperty().addListener((obs, oldV, newV) -> {
            view.getDateArriveePicker().setDayCellFactory(picker -> createDateCell(takenDates, LocalDate.MIN, newV));
        });
    }

    /**
     * Initializes a DateCell that disables dates before a lower bound, after an upper bound, or in takenDates.
     */
    private DateCell createDateCell(Set<LocalDate> takenDates, LocalDate startInclusive, LocalDate endInclusive) {
        return new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                boolean outOfRange = date.isBefore(startInclusive) || date.isAfter(endInclusive);
                if (empty || date.isBefore(LocalDate.now()) || outOfRange || takenDates.contains(date)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        };
    }

    /**
     * Extracts all dates covered by existing reservations.
     */
    private Set<LocalDate> extractReservedDates(List<Reservation> reservations) {
        return reservations.stream()
                .flatMap(res -> Stream.iterate(
                                LocalDate.parse(res.getDateDebut()), d -> d.plusDays(1))
                        .limit(
                                LocalDate.parse(res.getDateFin())
                                        .toEpochDay() - LocalDate.parse(res.getDateDebut()).toEpochDay() + 1
                        )
                )
                .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * Enables the reserve button only when valid dates are selected.
     */
    private void checkDatesSelected() {
        LocalDate start = view.getDateArriveePicker().getValue();
        LocalDate end = view.getDateDepartPicker().getValue();
        boolean valid = start != null && end != null && !end.isBefore(start);
        view.getReserverButton().setDisable(!valid);
    }

    /**
     * Handles reservation action (for now prints to console).
     */
    private void makeReservation() {

    }

    /**
     * Shows the booking view scene.
     */
    public void show() {
        primaryStage.setScene(view.getScene());
        primaryStage.show();
    }
}
