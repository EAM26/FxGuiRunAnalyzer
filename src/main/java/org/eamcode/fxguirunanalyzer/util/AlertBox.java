package org.eamcode.fxguirunanalyzer.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertBox {

    public boolean confirmDelete(String name) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete " + name);
        alert.setHeaderText("Are you sure you want to delete " + name + "?");
        alert.setContentText("This action cannot be undone.");

        ButtonType deleteButton   = new ButtonType("Delete",  ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton   = new ButtonType("Cancel",  ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(deleteButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == deleteButton;
    }
}
