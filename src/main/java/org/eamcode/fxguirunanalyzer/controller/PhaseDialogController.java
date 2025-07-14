package org.eamcode.fxguirunanalyzer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import org.eamcode.fxguirunanalyzer.service.PhaseDialogService;

public class PhaseDialogController {

    private final PhaseDialogService phaseDialogService;
    public Spinner<Integer> hourSpinner;
    public Spinner<Integer> minuteSpinner;
    public Spinner<Integer> secondSpinner;
    public ComboBox<String> categoryCombo;

    public PhaseDialogController() {
        this.phaseDialogService = new PhaseDialogService();
    }

    @FXML
    public void initialize() {
        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0));
        minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        secondSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
    }


    public void savePhase() {
        System.out.println("Saving phase with values: " );

        phaseDialogService.savePhase(
                hourSpinner.getValue(),
                minuteSpinner.getValue(),
                secondSpinner.getValue(),
                categoryCombo.getValue()
        );
    }
}
