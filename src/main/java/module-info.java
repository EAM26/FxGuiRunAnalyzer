module org.eamcode.fxguirunanalyzer {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.eamcode.fxguirunanalyzer to javafx.fxml;
    opens org.eamcode.fxguirunanalyzer.controller to javafx.fxml;
    exports org.eamcode.fxguirunanalyzer;
}