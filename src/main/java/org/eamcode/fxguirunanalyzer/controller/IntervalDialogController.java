package org.eamcode.fxguirunanalyzer.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import org.eamcode.fxguirunanalyzer.service.IntervalDialogService;

import java.util.List;

public class IntervalDialogController {

    private final IntervalDialogService intervalDialogService;
    @FXML
    public ComboBox<String> categoryCombo1;
    @FXML
    public Spinner<Integer> hourSpinner1;
    @FXML
    public Spinner<Integer> minuteSpinner1;
    @FXML
    public Spinner<Integer> secondSpinner1;
    @FXML
    public ComboBox<String> categoryCombo2;
    @FXML
    public Spinner<Integer> hourSpinner2;
    @FXML
    public Spinner<Integer> minuteSpinner2;
    @FXML
    public Spinner<Integer> secondSpinner2;
    @FXML
    public TextField multiplier;

    private Long reportId;

    public IntervalDialogController() {
        this.intervalDialogService = new IntervalDialogService();
    }

    @FXML
    public void initialize(Long reportId) {
        this.reportId = reportId;
        hourSpinner1.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0));
        minuteSpinner1.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        secondSpinner1.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        hourSpinner2.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0));
        minuteSpinner2.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        secondSpinner2.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        List<String> categories = intervalDialogService.getCategoryItems();
        categoryCombo1.setItems(FXCollections.observableArrayList(categories));
        categoryCombo2.setItems(FXCollections.observableArrayList(categories));

    }

    public void saveInterval() {
        intervalDialogService.saveInterval(
                this.reportId,
                hourSpinner1.getValue(),
                minuteSpinner1.getValue(),
                secondSpinner1.getValue(),
                categoryCombo1.getSelectionModel().getSelectedItem(),
                hourSpinner2.getValue(),
                minuteSpinner2.getValue(),
                secondSpinner2.getValue(),
                categoryCombo2.getSelectionModel().getSelectedItem(),
                multiplier.getText()
        );
        System.out.println("Saving interval with reportId: " + reportId);
    }
}


