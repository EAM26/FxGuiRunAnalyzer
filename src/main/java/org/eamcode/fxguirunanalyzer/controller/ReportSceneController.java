package org.eamcode.fxguirunanalyzer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.Data;

import java.net.URL;
import java.util.ResourceBundle;

@Data
public class ReportSceneController implements Initializable {

    private Long reportId;

    @FXML
    public Label testLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        testLabel.setText(String.valueOf(reportId));
    }
}
