package org.eamcode.fxguirunanalyzer.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import org.eamcode.fxguirunanalyzer.Main;
import org.eamcode.fxguirunanalyzer.api.model.ReportResponse;
import org.eamcode.fxguirunanalyzer.controller.PhaseModalController;
import org.eamcode.fxguirunanalyzer.controller.ReportSceneController;

import java.io.IOException;

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

    public void openPhaseModal(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/phase-modal.fxml"));
        DialogPane pane = loader.load();
        PhaseModalController modal = loader.getController();

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(pane);
        dialog.initOwner(stage);
        dialog.setTitle("Add Phase");
        dialog.showAndWait();
    }
}
