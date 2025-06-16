package org.eamcode.fxguirunanalyzer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import org.eamcode.fxguirunanalyzer.service.StartSceneService;

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
        labelHeader.setText(startSceneService.getAllSummaryReports().getFirst().getName());
    }
}
