package org.eamcode.fxguirunanalyzer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Data;
import org.eamcode.fxguirunanalyzer.api.model.PhaseResponse;
import org.eamcode.fxguirunanalyzer.api.model.ReportResponse;
import org.eamcode.fxguirunanalyzer.util.Navigation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @FXML
    private TableColumn<PhaseResponse, Number> nrCol;

    @FXML
    public TableColumn<PhaseResponse, String> durationCol;

    @FXML
    public TableColumn<PhaseResponse, String> distanceCol;

    @FXML
    public TableColumn<PhaseResponse, String> hrCol;

    @FXML
    public TableColumn<PhaseResponse, String> speedCol;

    @FXML
    public TableColumn<PhaseResponse, String> categoryCol;

    @FXML
    public TableView<PhaseResponse> phaseTable;



    public void initData(ReportResponse response) {
        setMetaData(response);
        setPhaseTable(response);

    }

    @FXML
    private void onBtnStartClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnStart.getScene().getWindow();
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

    private void setPhaseTable(ReportResponse response) {
        nrCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : Integer.toString(getIndex() + 1));
            }
        });

        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        distanceCol.setCellValueFactory(new PropertyValueFactory<>("distance"));
        hrCol.setCellValueFactory(new PropertyValueFactory<>("heartRateAvg"));
        speedCol.setCellValueFactory(new PropertyValueFactory<>("speed"));

        if (response.getPhaseResponses() != null) {
            phaseTable.setItems(javafx.collections.FXCollections.observableList(response.getPhaseResponses()));
        }
    }

}
