package org.eamcode.fxguirunanalyzer.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.eamcode.fxguirunanalyzer.api.model.ReportResponse;
import org.eamcode.fxguirunanalyzer.api.model.ReportSummaryResponse;
import org.eamcode.fxguirunanalyzer.service.ReportSceneService;
import org.eamcode.fxguirunanalyzer.service.StartSceneService;
import org.eamcode.fxguirunanalyzer.util.Navigation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StartSceneController implements Initializable {

    private final StartSceneService startSceneService;
    private final ReportSceneService reportSceneService;


    public StartSceneController() {
        this.startSceneService = new StartSceneService();
        this.reportSceneService = new ReportSceneService();
    }

    @FXML
    public TableView<ReportSummaryResponse> startSceneTable;

    @FXML
    private Button btnOpen;

    @FXML
    public Button btnNew;

    @FXML
    private Label labelHeader;

    @FXML
    private TableColumn<ReportSummaryResponse, String> nameCol;

    @FXML
    private TableColumn<ReportSummaryResponse, String> durationCol;

    @FXML
    private TableColumn<ReportSummaryResponse, String> distanceCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setStartTable();
    }

    public void setStartTable(){
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        distanceCol.setCellValueFactory(new PropertyValueFactory<>("distance"));

        List<ReportSummaryResponse> responseList;
        try {
            responseList = startSceneService.getAllSummaryReports();
        } catch (IOException | InterruptedException e) {
            System.err.println("Error fetching reports: " + e);
            responseList = List.of();
        }
        startSceneTable.setItems(FXCollections.observableList(responseList));
    }

    @FXML
    void onButtonOpenClick(ActionEvent event) throws IOException, InterruptedException {
        ReportSummaryResponse selectedReport = startSceneTable.getSelectionModel().getSelectedItem();
        if (selectedReport == null) {
            System.out.println("No report selected.");
            return;
        }
        Stage stage = (Stage) startSceneTable.getScene().getWindow();
        ReportResponse reportResponse = reportSceneService.getSingleReport(selectedReport.getId());
        Navigation nav = new Navigation();
        nav.toReportScene(stage, reportResponse);
    }

    @FXML
    public void onButtonNewClick(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();

        File backendDir = new File(System.getProperty("backend.dir", "C:\\Users\\Gebruiker\\Projects\\JavaProjects\\RunAnalyzer\\src\\main\\resources\\test-data"));
        if(backendDir.exists()){
            fileChooser.setInitialDirectory(backendDir);
        }

        fileChooser.setTitle("Select Report File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", "*.CSV", "*.csv"));

        File file = fileChooser.showOpenDialog(btnNew.getScene().getWindow());
        if(file == null) {
            return;
        }
        String absPath = file.getAbsolutePath();
        ReportResponse response = reportSceneService.createReport(absPath);

        Stage stage = (Stage) startSceneTable.getScene().getWindow();
        Navigation nav = new Navigation();
        nav.toReportScene(stage, response);
    }

}
