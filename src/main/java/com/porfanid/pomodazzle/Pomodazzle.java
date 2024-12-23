package com.porfanid.pomodazzle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Pomodazzle extends Application {

    private PomodazzleMainController controller;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load your FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/porfanid/pomodazzle/main.fxml"));
        Parent root = fxmlLoader.load();

        controller = fxmlLoader.getController();

        // Create a Scene with the root node from FXML
        Scene scene = new Scene(root, 400, 600);

        // Add the CSS to the Scene
        scene.getStylesheets().add(getClass().getResource("/com/porfanid/pomodazzle/style.css").toExternalForm());

        // Set the stage and show the application
        primaryStage.setTitle("Pomodazzle");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        // Call the close method of the controller
        if (controller != null) {
            controller.close();
        }
        super.stop();
    }

    public static void main(String[] args) {

        launch();
    }
}
