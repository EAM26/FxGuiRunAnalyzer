package org.eamcode.fxguirunanalyzer.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.eamcode.fxguirunanalyzer.Main;
import org.eamcode.fxguirunanalyzer.controller.ReportSceneController;

import java.io.IOException;

public class Navigation {

    public  void toStartScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/start-scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Start Screen");
        stage.setScene(scene);
        stage.show();
    }

    public void toReportScene(Stage stage, Long id) throws IOException {

        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/fxml/report-scene.fxml")) ;
        Parent root = loader.load();
        ReportSceneController controller = loader.getController();
        controller.initData(id);
        stage.setScene(new Scene(root));
    }
}
