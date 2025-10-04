package com.abhimurkute.zenflow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class ZenFlowApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // First, get the URL of the FXML file
        URL fxmlLocation = ZenFlowApp.class.getResource("zenflow-view.fxml");
        
        // Check if the file was actually found
        if (fxmlLocation == null) {
            System.err.println("FXML file not found: zenflow-view.fxml");
            throw new IOException("Cannot find FXML file. Make sure it's in the correct resources package.");
        }

        // Load the FXML from the validated location
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
        stage.setTitle("ZenFlow");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}