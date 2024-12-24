package com.porfanid.pomodazzle.pomodoroHandler;

import java.io.*;
import java.util.Properties;

public class PomodoroDefaultValues implements AutoCloseable, Serializable {
    // The path to store the settings file in a user-specific directory
    private static final String SETTINGS_FILE = getSettingsFilePath();


    public static final PomodoroDefaultValues INSTANCE = new PomodoroDefaultValues();

    // Constants for default Pomodoro and break durations
    private int defaultWorkTime; // 10 minutes (for testing purposes, use 25 * 60 for actual Pomodoro)
    private int defaultBreakTime; // 5 minutes
    private int defaultLongBreakTime; // 15 minutes after 4 Pomodoros

    public PomodoroDefaultValues() {
        // Try to load from the settings file on initialization
        loadSettings();
    }

    public int getDefaultWorkTime() {
        return defaultWorkTime;
    }

    public void setDefaultWorkTime(int defaultWorkTime) {
        this.defaultWorkTime = defaultWorkTime;
    }

    public int getDefaultBreakTime() {
        return defaultBreakTime;
    }

    public void setDefaultBreakTime(int defaultBreakTime) {
        this.defaultBreakTime = defaultBreakTime;
    }

    public int getDefaultLongBreakTime() {
        return defaultLongBreakTime;
    }

    public void setDefaultLongBreakTime(int defaultLongBreakTime) {
        this.defaultLongBreakTime = defaultLongBreakTime;
    }

    // Save settings to a properties file
    public void close() {
        Properties properties = new Properties();
        properties.setProperty("workTime", String.valueOf(defaultWorkTime));
        properties.setProperty("breakTime", String.valueOf(defaultBreakTime));
        properties.setProperty("longBreakTime", String.valueOf(defaultLongBreakTime));

        try (FileOutputStream outputStream = new FileOutputStream(SETTINGS_FILE)) {
            properties.store(outputStream, "Pomodoro Settings");
        } catch (IOException e) {
            System.err.println("Error saving settings: " + e.getMessage());
        }
    }

    // Load settings from a properties file
    public void loadSettings() {
        Properties properties = new Properties();

        try (FileInputStream inputStream = new FileInputStream(SETTINGS_FILE)) {
            properties.load(inputStream);
            defaultWorkTime = Integer.parseInt(properties.getProperty("workTime", "25"));
            defaultBreakTime = Integer.parseInt(properties.getProperty("breakTime", "5"));
            defaultLongBreakTime = Integer.parseInt(properties.getProperty("longBreakTime", "15"));
        } catch (IOException e) {
            System.err.println("Error loading settings: " + e.getMessage());
        }
    }

    // Get the path to the settings file based on the platform
    private static String getSettingsFilePath() {
        String userHome = System.getProperty("user.home");
        // Create a directory for the app if it doesn't exist
        File appDir = new File(userHome, ".pomodoro");
        boolean fileCreated;
        if (!appDir.exists()) {
            fileCreated = appDir.mkdir();
        }else{
            fileCreated = true;
        }

        if(!fileCreated){
            System.err.println("Error creating directory for settings file");
        }
        // Return the settings file path
        return new File(appDir, "pomodoro_settings.properties").getAbsolutePath();
    }
}
