package org.eamcode.fxguirunanalyzer;

import javafx.application.Application;
import javafx.stage.Stage;
import org.eamcode.fxguirunanalyzer.util.Navigation;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Navigation nav = new Navigation();
        nav.toStartScene(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}