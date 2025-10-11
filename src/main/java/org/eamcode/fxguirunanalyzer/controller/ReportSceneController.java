package org.eamcode.fxguirunanalyzer.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Data;
import org.eamcode.fxguirunanalyzer.api.model.PhaseResponse;
import org.eamcode.fxguirunanalyzer.api.model.ReportResponse;
import org.eamcode.fxguirunanalyzer.service.ReportSceneService;
import org.eamcode.fxguirunanalyzer.util.MyTimeFormatter;
import org.eamcode.fxguirunanalyzer.util.Navigation;

import java.io.IOException;

@Data
public class ReportSceneController {

    MyTimeFormatter timeFormatter = new MyTimeFormatter();

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
    public Button addPhaseBtn;

    @FXML
    public Button addIntervalBtn;

    @FXML
    public Button deletePhasesBtn;

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

    private Long reportId;
    private final ReportSceneService reportSceneService;
//    private ReportResponse reportResponse;

    public ReportSceneController() {
        this.reportSceneService = new ReportSceneService();
    }

    public void initData(ReportResponse response) {
        setMetaData(response);
        setPhaseTable(response);
        setReportId(response.getId());
//        this.reportResponse = response;

    }

    @FXML
    private void onBtnBackClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnStart.getScene().getWindow();
        Navigation nav = new Navigation();
        nav.toStartScene(stage);
    }

    @FXML
    private void openPhaseDialog(ActionEvent event) throws IOException {
        Stage stage = (Stage) addPhaseBtn.getScene().getWindow();
        Navigation nav = new Navigation();
        nav.openPhaseDialog(stage, reportId);
        ReportResponse updatedResponse = reportSceneService.getSingleReport(reportId);     // or whatever your method is
        setPhaseTable(updatedResponse);
    }

    @FXML
    private void openIntervalDialog(ActionEvent event) throws IOException {
        Stage stage = (Stage) addPhaseBtn.getScene().getWindow();
        Navigation nav = new Navigation();
        nav.openIntervalDialog(stage, reportId);
        ReportResponse updatedResponse = reportSceneService.getSingleReport(reportId);     // or whatever your method is
        setPhaseTable(updatedResponse);
    }

    @FXML
    public void deleteAllPhases(ActionEvent event) {
        reportSceneService.deleteAllPhases(reportId);
        ReportResponse updatedResponse = reportSceneService.getSingleReport(reportId);     // or whatever your method is
        setPhaseTable(updatedResponse);
    }

    private void setMetaData(ReportResponse response) {
        labelName.setText(response.getName());
        tfDuration.setText(String.valueOf(response.getMetaData().getDuration()));
        tfDistance.setText(response.getMetaData().getTotalDistance());
        tfHeartRate.setText(response.getMetaData().getHeartRateAvg());
        tfSpeed.setText(response.getMetaData().getSpeedAvg());
    }

    private void setPhaseTable(ReportResponse response) {

        nrCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(
                        phaseTable.getItems().indexOf(cellData.getValue()) + 1
                )
        );
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        durationCol.setCellValueFactory(dur -> new SimpleStringProperty(timeFormatter.formatDuration(dur.getValue().getDuration())));
        distanceCol.setCellValueFactory(cd ->
                new SimpleStringProperty(String.format("%.0f", cd.getValue().getDistance()))
        );

        hrCol.setCellValueFactory(cd ->
                new SimpleStringProperty(String.format("%.0f", cd.getValue().getHeartRateAvg()))
        );

        speedCol.setCellValueFactory(cd ->
                new SimpleStringProperty(String.format("%.2f", cd.getValue().getSpeed()))
        );

        if (response.getPhaseResponses() != null) {
            phaseTable.setItems(javafx.collections.FXCollections.observableList(response.getPhaseResponses()));
        }
    }

}
