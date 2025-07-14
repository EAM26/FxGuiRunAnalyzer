package org.eamcode.fxguirunanalyzer.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import org.eamcode.fxguirunanalyzer.service.PhaseDialogService;

import java.util.List;

public class PhaseDialogController {

    private final PhaseDialogService phaseDialogService;
    public Spinner<Integer> hourSpinner;
    public Spinner<Integer> minuteSpinner;
    public Spinner<Integer> secondSpinner;
    public ComboBox<String> categoryCombo;

    private Long reportId;

    public PhaseDialogController() {
        this.phaseDialogService = new PhaseDialogService();
    }

    @FXML
    public void initialize(Long reportId) {
        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0));
        minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        secondSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        List<String> comboItems = phaseDialogService.getCategoryItems();
        categoryCombo.setValue(comboItems.getFirst());
        categoryCombo.setItems(FXCollections.observableList(comboItems));


        this.reportId = reportId;
    }


    public void savePhase() {
        phaseDialogService.savePhase(
                this.reportId,
                hourSpinner.getValue(),
                minuteSpinner.getValue(),
                secondSpinner.getValue(),
                categoryCombo.getSelectionModel().getSelectedItem()
        );
    }
}
