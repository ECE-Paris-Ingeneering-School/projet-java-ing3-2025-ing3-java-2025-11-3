package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class RemoveHebergementView {
    private final VBox root;
    private final Button deleteBtn;
    private final ComboBox<String> selectLog;

    public RemoveHebergementView(ArrayList<String> hebergement) {
        root = new VBox(10);
        root.setPadding(new Insets(20));

        Label title = new Label("Supprimer un logement");

        selectLog = new ComboBox<>(
                FXCollections.observableArrayList(hebergement)
        );
        selectLog.setPromptText("Sélectionnez un logement");

        deleteBtn = new Button("Supprimer");

        root.getChildren().addAll(title, selectLog, deleteBtn);
    }

    public VBox getRoot() {
        return root;
    }
    public Button deleteBtn(){return deleteBtn;}
    public ComboBox<String> getSelectLog() {return selectLog;}

}
