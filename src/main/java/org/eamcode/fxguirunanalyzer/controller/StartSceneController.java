package org.eamcode.fxguirunanalyzer.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.eamcode.fxguirunanalyzer.Main;
import org.eamcode.fxguirunanalyzer.api.model.ReportSummaryResponse;
import org.eamcode.fxguirunanalyzer.service.StartSceneService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StartSceneController implements Initializable {

    private final StartSceneService startSceneService;

    public StartSceneController() {this.startSceneService = new StartSceneService();}

    @FXML
    public TableView<ReportSummaryResponse> startSceneTable;

    @FXML
    private Button btnClickMe;

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
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        distanceCol.setCellValueFactory(new PropertyValueFactory<>("distance"));

        List<ReportSummaryResponse> responseList = startSceneService.getAllSummaryReports();
        startSceneTable.setItems(FXCollections.observableList(responseList));
    }

    @FXML
    void onButtonClick(ActionEvent event) {
        labelHeader.setText(startSceneService.getAllSummaryReports().getFirst().getName());
    }

    @FXML
    public void toReportScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/start-scene.fxml"));
        ReportSceneController reportSceneController = new ReportSceneController();
        reportSceneController.setReportId(1L);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Start Screen");
        stage.setScene(scene);
    }


}
