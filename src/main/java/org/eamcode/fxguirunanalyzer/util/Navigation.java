package org.eamcode.fxguirunanalyzer.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import org.eamcode.fxguirunanalyzer.Main;
import org.eamcode.fxguirunanalyzer.api.model.ReportResponse;
import org.eamcode.fxguirunanalyzer.controller.IntervalDialogController;
import org.eamcode.fxguirunanalyzer.controller.PhaseDialogController;
import org.eamcode.fxguirunanalyzer.controller.ReportSceneController;

import java.io.IOException;
import java.util.Optional;

public class Navigation {


    public  void toStartScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/start-scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Start Screen");
        stage.setScene(scene);
        stage.show();
    }

    public void toReportScene(Stage stage, ReportResponse response) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/fxml/report-scene.fxml")) ;
        Parent root = loader.load();
        ReportSceneController controller = loader.getController();
        controller.initData(response);
        stage.setTitle("Report Details");
        stage.setScene(new Scene(root));
    }

    public void openPhaseDialog(Stage stage, Long reportId) throws IOException {
        FXMLLoader loader =
                new FXMLLoader(Main.class.getResource("/fxml/phase-dialog.fxml"));
        DialogPane pane = loader.load();
        PhaseDialogController controller = loader.getController();
        controller.initialize(reportId);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(pane);
        dialog.initOwner(stage);
        dialog.setTitle("Add phase");

        Optional<ButtonType> pressed = dialog.showAndWait();
        if (pressed.isPresent()
                && pressed.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            controller.savePhase();
        }
    }

    public void openIntervalDialog(Stage stage, Long reportId) throws IOException {
        FXMLLoader loader =
                new FXMLLoader(Main.class.getResource("/fxml/interval-dialog.fxml"));
        DialogPane pane = loader.load();
        IntervalDialogController controller = loader.getController();
        controller.initialize(reportId);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(pane);
        dialog.initOwner(stage);
        dialog.setTitle("Add interval");

        Optional<ButtonType> pressed = dialog.showAndWait();
        if (pressed.isPresent()
                && pressed.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            controller.saveInterval();
        }
    }
}
