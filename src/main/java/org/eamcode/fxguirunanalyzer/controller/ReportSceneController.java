package org.eamcode.fxguirunanalyzer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.Data;
import org.eamcode.fxguirunanalyzer.Main;

import java.io.IOException;

@Data
public class ReportSceneController {

    @FXML
    public Label labelReportId;

    @FXML
    public Button btnStart;

    public void initData(Long id) {
        labelReportId.setText(id.toString());
    }

    @FXML
    private void onBtnStartClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)  btnStart.getScene().getWindow();
        Main.toStartScene(stage);
    }


}
