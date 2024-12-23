package com.porfanid.pomodazzle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import com.porfanid.pomodazzle.pomodoroHandler.PomodoroDefaultValues;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;

public class SettingsController {

    @FXML
    private TextField workTimeInput;

    @FXML
    private TextField breakTimeInput;

    @FXML
    private TextField longBreakTimeInput;

    @FXML
    private Button saveButton;

    @FXML
    private Button loadButton;
    @FXML
    private ImageView arrowLeftIcon;

    private final PomodoroDefaultValues pomodoroSettings;

    public SettingsController() {
        pomodoroSettings = PomodoroDefaultValues.INSTANCE;
    }

    @FXML
    public void initialize() {
        // Load settings when the screen is displayed
        handleLoadSettings();
    }

    @FXML
    private void handleSaveSettings() {
        try {
            int workTime = Integer.parseInt(workTimeInput.getText());
            int breakTime = Integer.parseInt(breakTimeInput.getText());
            int longBreakTime = Integer.parseInt(longBreakTimeInput.getText());

            pomodoroSettings.setDefaultWorkTime(workTime);
            pomodoroSettings.setDefaultBreakTime(breakTime);
            pomodoroSettings.setDefaultLongBreakTime(longBreakTime);

            pomodoroSettings.close();

            showAlert(AlertType.INFORMATION, "Settings Saved", "The settings have been successfully saved.");

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Invalid Input", "Please enter valid numeric values.");
        }
    }


    private void handleLoadSettings() {
        pomodoroSettings.loadSettings();

        // Update fields with the loaded values
        workTimeInput.setText(String.valueOf(pomodoroSettings.getDefaultWorkTime()));
        breakTimeInput.setText(String.valueOf(pomodoroSettings.getDefaultBreakTime()));
        longBreakTimeInput.setText(String.valueOf(pomodoroSettings.getDefaultLongBreakTime()));
    }

    // Method to handle the Back button action
    @FXML
    private void handleBackButton() {
        try {
            // Load the main application FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/porfanid/pomodazzle/main.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) saveButton.getScene().getWindow();

            // Set the scene to the main application
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null); // Optional: Hide the header text
        alert.setContentText(message);

        // Apply custom CSS
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/com/porfanid/pomodazzle/theme.css").toExternalForm());

        // Show the alert and wait for user interaction
        alert.showAndWait();
    }

}
