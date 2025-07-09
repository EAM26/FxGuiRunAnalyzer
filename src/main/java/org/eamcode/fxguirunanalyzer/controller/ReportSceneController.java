package org.eamcode.fxguirunanalyzer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Data;
import org.eamcode.fxguirunanalyzer.api.model.ReportResponse;
import org.eamcode.fxguirunanalyzer.util.Navigation;

import java.io.IOException;

@Data
public class ReportSceneController {

    @FXML
    public Label labelReportId;

    @FXML
    public Button btnStart;

    @FXML
    public Label labelName;

    @FXML
    public TextField tfDuration;

    @FXML
    public TextField tfDistance;

    @FXML
    public TextField tfHeartRate;

    @FXML
    public TextField tfSpeed;

    @FXML
    public Label tableName1;

   @FXML
    public Label tableName2;

   @FXML
    public Label lblTraining;


    public void initData(ReportResponse response) {
        setMetaData(response);

    }

    @FXML
    private void onBtnStartClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)  btnStart.getScene().getWindow();
        Navigation nav = new Navigation();
        nav.toStartScene(stage);
    }

    private void setMetaData(ReportResponse response) {
        labelName.setText(response.getName());
        tfDuration.setText(String.valueOf(response.getMetaData().getDuration()));
        tfDistance.setText(response.getMetaData().getTotalDistance());
        tfHeartRate.setText(response.getMetaData().getHeartRateAvg());
        tfSpeed.setText(response.getMetaData().getSpeedAvg());
    }

}
