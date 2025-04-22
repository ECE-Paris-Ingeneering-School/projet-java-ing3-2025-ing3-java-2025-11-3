package controller;

import javafx.stage.Stage;
import view.NavBarView;

public class NavBarController {
    private final Stage stage;

    public NavBarController(Stage stage, NavBarView navBarView) {
        this.stage = stage;

        navBarView.getTitleLabel().setOnMouseClicked(e ->
                new HomePageController(stage).show());

        navBarView.getRechercheLabel().setOnMouseClicked(e ->
                new SearchPageController(stage).show());

        navBarView.getReservationsLabel().setOnMouseClicked(e ->
                new ReservationController(stage).show());

        navBarView.getContactLabel().setOnMouseClicked(e ->
                new ContactController(stage).show());

        navBarView.getSignInLabel().setOnMouseClicked(e ->
                new LoginPageController(stage).show());
    }
}
