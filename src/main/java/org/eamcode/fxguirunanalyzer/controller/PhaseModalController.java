package org.eamcode.fxguirunanalyzer.controller;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;

public class PhaseModalController {
    public Spinner<Integer> hourSpinner = new Spinner<>(0, 10, 0);
    public Spinner<Integer> minuteSpinner = new Spinner<>(0, 59, 0);
    public Spinner<Integer> secondSpinner  = new Spinner<>(0, 59, 0);;
    public ComboBox<String> categoryCombo;
}
