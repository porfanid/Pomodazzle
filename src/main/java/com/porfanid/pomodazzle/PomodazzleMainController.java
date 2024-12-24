package com.porfanid.pomodazzle;

import com.porfanid.pomodazzle.pomodoroHandler.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.Closeable;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URI;
import java.net.URISyntaxException;

public class PomodazzleMainController implements Closeable {

    @FXML
    private Label timerLabel;
    @FXML
    private Label statusLabel;  // Label to show Work or Break status
    @FXML
    private Button startButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button donateButton;
    @FXML
    private Button settingsButton;

    private Application app;

    private PomodoroDefaultValues defaultValues;


    private PomodoroInt pomodoroTimer;

    public void initialize() {

        // Create the listener for PomodoroTimer updates
        PomodoroTimerListener listener = new PomodoroTimerListener() {
            @Override
            public void onTimeUpdate(int minutesLeft, int secondsLeft) {
                // Update the UI based on time
                Platform.runLater(() -> {
                    updateTimerLabel();
                });
            }

            @Override
            public void onStateChange(TimerState state) {
                // Update the UI based on state changes (Work, Break, Paused)
                Platform.runLater(() -> {
                    updateUI(state);
                });
            }
        };

        defaultValues = new PomodoroDefaultValues();

        // Initialize PomodoroTimer
        pomodoroTimer = PomodoroFactory.createPomodoro(listener);

        // Initial UI update
        updateTimerLabel();
        updateStatusLabel(TimerState.STOPPED);
    }

    public void setApp(Application app) {
        this.app = app;
    }

    @FXML
    private void onStartButtonClick() {
        pomodoroTimer.changeState();
    }

    @FXML
    private void onDonateButtonClick() {
        if(app==null) return;
        app.getHostServices().showDocument("https://ko-fi.com/porfanid");
    }







    @FXML
    private void onResetButtonClick() {
        pomodoroTimer.reset(); // Reset the PomodoroTimer
    }

    private void updateUI(TimerState state) {
        // Update the UI based on the state (Work, Break, Paused)
        updateTimerLabel();
        updateStatusLabel(state);
    }

    private void updateTimerLabel() {
        int remainingTime = pomodoroTimer.getRemainingTime();
        int minutes = remainingTime / 60;
        int seconds = remainingTime % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    @FXML
    private void onSettingsButtonClick() {
        // Code to navigate to the settings screen
        // For example, loading the settings FXML
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/porfanid/pomodazzle/activity_settings.fxml"));
            Parent settingsRoot = loader.load();
            Scene settingsScene = new Scene(settingsRoot);

            Stage currentStage = (Stage) settingsButton.getScene().getWindow();
            currentStage.setScene(settingsScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateStatusLabel(TimerState state) {
        // Remove all status-related classes
        statusLabel.getStyleClass().removeAll("working", "on-break", "paused", "stopped");
        timerLabel.getStyleClass().removeAll("working", "on-break", "paused", "stopped");

        // Add the appropriate CSS class based on the state
        switch (state) {
            case RUNNING:
                if (pomodoroTimer.isWorking()) {
                    statusLabel.getStyleClass().add("working");
                    timerLabel.getStyleClass().add("working");
                    statusLabel.setText("Working");
                    startButton.setText("Pause Pomodoro");
                } else if (pomodoroTimer.isOnBreak()) {
                    statusLabel.getStyleClass().add("on-break");
                    timerLabel.getStyleClass().add("on-break");
                    statusLabel.setText("On Break");
                    startButton.setText("Pause Break");
                }
                break;
            case PAUSED:
                statusLabel.getStyleClass().add("paused");
                timerLabel.getStyleClass().add("paused");
                statusLabel.setText("Paused");
                startButton.setText("Resume");
                break;
            case STOPPED:
                statusLabel.getStyleClass().add("stopped");
                timerLabel.getStyleClass().add("stopped");
                statusLabel.setText("Stopped");
                startButton.setText("Start Pomodoro");
                break;
            default:
                break;
        }
    }

    @Override
    public void close() throws IOException {
        pomodoroTimer.close();
    }
}