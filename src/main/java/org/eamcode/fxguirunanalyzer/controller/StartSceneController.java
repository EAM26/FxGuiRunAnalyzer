package org.eamcode.fxguirunanalyzer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import org.eamcode.fxguirunanalyzer.api.model.ReportResponse;
import org.eamcode.fxguirunanalyzer.service.StartSceneService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.List;

public class StartSceneController {

    private final StartSceneService startSceneService;

    public StartSceneController() {
        this.startSceneService = new StartSceneService();
    }


    @FXML
    private Button btnClickMe;

    @FXML
    private Label labelHeader;

    @FXML
    private TableColumn<?, ?> tColDate;

    @FXML
    private TableColumn<?, ?> tColDistance;

    @FXML
    private TableColumn<?, ?> tColDuration;


    @FXML
    void onButtonClick(ActionEvent event) {
        labelHeader.setText(startSceneService.getAllReports().getFirst().getName());
    }
}
