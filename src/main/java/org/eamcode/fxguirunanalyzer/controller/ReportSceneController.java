package org.eamcode.fxguirunanalyzer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.Data;

import java.net.URL;
import java.util.ResourceBundle;

@Data
public class ReportSceneController {

    @FXML
    public Label labelReportId;

    public void initData(Long id) {
        labelReportId.setText(id.toString());
    }


}
