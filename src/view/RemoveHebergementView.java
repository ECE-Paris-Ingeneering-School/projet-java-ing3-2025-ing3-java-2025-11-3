package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class RemoveHebergementView {
    private final VBox root;

    public RemoveHebergementView() {
        root = new VBox(10);
        root.setPadding(new Insets(20));

        Label title = new Label("Supprimer un logement");

        ComboBox<String> selectLog = new ComboBox<>(
                FXCollections.observableArrayList("1 - Hôtel A", "2 - Villa B")
        );
        selectLog.setPromptText("Sélectionnez un logement");

        Button deleteBtn = new Button("Supprimer");

        root.getChildren().addAll(title, selectLog, deleteBtn);
    }

    public VBox getRoot() {
        return root;
    }
}
