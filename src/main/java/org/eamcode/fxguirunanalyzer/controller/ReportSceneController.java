package org.eamcode.fxguirunanalyzer.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Data;
import org.eamcode.fxguirunanalyzer.api.model.PhaseGroupSummary;
import org.eamcode.fxguirunanalyzer.api.model.PhaseResponse;
import org.eamcode.fxguirunanalyzer.api.model.ReportResponse;
import org.eamcode.fxguirunanalyzer.service.ReportSceneService;
import org.eamcode.fxguirunanalyzer.util.MyTimeFormatter;
import org.eamcode.fxguirunanalyzer.util.Navigation;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

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

    // Phase table
    @FXML
    private TableColumn<PhaseResponse, Number> phasesNrCol;

    @FXML
    public TableColumn<PhaseResponse, String> phasesDurationCol;

    @FXML
    public TableColumn<PhaseResponse, String> phasesDistanceCol;

    @FXML
    public TableColumn<PhaseResponse, String> phasesHrCol;

    @FXML
    public TableColumn<PhaseResponse, String> phasesSpeedCol;

    @FXML
    public TableColumn<PhaseResponse, String> phasesCategoryCol;

    @FXML
    public TableView<PhaseResponse> phasesTable;

    // Summary table

    @FXML
    public TableView<PhaseGroupSummary> summaryTable;

    @FXML
    public TableColumn<PhaseGroupSummary, String> summaryCategoryCol;
    @FXML
    public TableColumn<PhaseGroupSummary, String> summaryDurationCol;
    @FXML
    public TableColumn<PhaseGroupSummary, String> summaryDistanceCol;
    @FXML
    public TableColumn<PhaseGroupSummary, String> summaryHrCol;
    @FXML
    public TableColumn<PhaseGroupSummary, String> summarySpeedCol;

    private Long reportId;
    private final ReportSceneService reportSceneService;
//    private ReportResponse reportResponse;

    public ReportSceneController() {
        this.reportSceneService = new ReportSceneService();
    }

    public void initData(ReportResponse response) {
        setMetaData(response);
        setReportId(response.getId());
        refreshTables(response);

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
        ReportResponse updatedResponse = reportSceneService.getSingleReport(reportId);
        refreshTables(updatedResponse);
    }

    @FXML
    private void openIntervalDialog(ActionEvent event) throws IOException {
        Stage stage = (Stage) addPhaseBtn.getScene().getWindow();
        Navigation nav = new Navigation();
        nav.openIntervalDialog(stage, reportId);
        ReportResponse updatedResponse = reportSceneService.getSingleReport(reportId);
        refreshTables(updatedResponse);
    }

    @FXML
    public void deleteAllPhases(ActionEvent event) {
        reportSceneService.deleteAllPhases(reportId);
        ReportResponse updatedResponse = reportSceneService.getSingleReport(reportId);
        refreshTables(updatedResponse);
    }

    private void setMetaData(ReportResponse response) {
        labelName.setText(response.getName());
        tfDuration.setText(String.valueOf(response.getMetaData().getDuration()));
        tfDistance.setText(response.getMetaData().getTotalDistance());
        tfHeartRate.setText(response.getMetaData().getHeartRateAvg());
        tfSpeed.setText(response.getMetaData().getSpeedAvg());
    }

    private void setSummaryTable(ReportResponse response) {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("summaries are: "+response.getSummaries());
        summaryCategoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        summaryDurationCol.setCellValueFactory(cd ->
                new SimpleStringProperty(timeFormatter.formatDuration(cd.getValue().getTotalDuration())));

        summaryDistanceCol.setCellValueFactory(cd ->
                new SimpleStringProperty(String.format("%.0f", cd.getValue().getTotalDistance())));

        summaryHrCol.setCellValueFactory(cd ->
                new SimpleStringProperty(String.format("%.0f", cd.getValue().getAverageHeartRate())));

        summarySpeedCol.setCellValueFactory(cd ->
                new SimpleStringProperty(String.format("%.2f", cd.getValue().getAverageSpeed())));



        if (response.getSummaries() != null) {
            List<PhaseGroupSummary> filteredSummaries = response.getSummaries().stream().filter(s ->
                    !s.getTotalDuration().isZero()).toList();
            summaryTable.setItems(javafx.collections.FXCollections.observableList(filteredSummaries));
        }
    }

    private void setPhasesTable(ReportResponse response) {

        phasesNrCol.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(
                        phasesTable.getItems().indexOf(cellData.getValue()) + 1
                )
        );
        phasesCategoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        phasesDurationCol.setCellValueFactory(dur ->
                new SimpleStringProperty(timeFormatter.formatDuration(dur.getValue().getDuration())));
        phasesDistanceCol.setCellValueFactory(cd ->
                new SimpleStringProperty(String.format("%.0f", cd.getValue().getDistance()))
        );

        phasesHrCol.setCellValueFactory(cd ->
                new SimpleStringProperty(String.format("%.0f", cd.getValue().getHeartRateAvg()))
        );

        phasesSpeedCol.setCellValueFactory(cd ->
                new SimpleStringProperty(String.format("%.2f", cd.getValue().getSpeed()))
        );

        if (response.getPhaseResponses() != null) {
            phasesTable.setItems(javafx.collections.FXCollections.observableList(response.getPhaseResponses()));
        }
    }

    private void refreshTables(ReportResponse updateResponse) {
        setPhasesTable(updateResponse);
        setSummaryTable(updateResponse);
    }

}
