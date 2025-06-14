package org.eamcode.fxguirunanalyzer.controller;

import javafx.fxml.FXML;
import org.eamcode.fxguirunanalyzer.service.StartSceneService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
    void onButtonClick(ActionEvent event) {
        labelHeader.setText(startSceneService.runStartSceneService());

    }
}
