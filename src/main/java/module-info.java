module org.eamcode.fxguirunanalyzer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires jdk.jfr;
    requires com.fasterxml.jackson.databind;
    requires static lombok;
    requires com.fasterxml.jackson.datatype.jsr310;


    opens org.eamcode.fxguirunanalyzer to javafx.fxml;
    opens org.eamcode.fxguirunanalyzer.controller to javafx.fxml;
    opens org.eamcode.fxguirunanalyzer.api.model;



    exports org.eamcode.fxguirunanalyzer;
}