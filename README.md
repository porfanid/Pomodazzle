# Pomodazzle

⚠️ **Deprecation Notice**  
This repository is no longer actively maintained. Future updates and new features for Pomodazzle will be available on [Pomodazzle-Electron](https://github.com/porfanid/Pomodazzle-electron). Please visit the new repository for the latest version of the app.

---

Pomodazzle is a JavaFX-based Pomodoro timer application designed to help you manage your time effectively using the Pomodoro Technique. The application includes features such as starting, pausing, and resetting the timer, as well as a donation button to support the developer.

## Features

- **Pomodoro Timer**: Start, pause, and reset the Pomodoro timer.
- **Work and Break Status**: Visual indicators for work and break periods.
- **Settings**: Customize your Pomodoro settings.
- **Donation**: Support the developer via a donation link.

## Requirements

- Java 21
- JavaFX 22.0.1
- Maven

## Installation

1. **Clone the repository**:
    ```sh
    git clone https://github.com/porfanid/Pomodazzle.git
    cd Pomodazzle
    ```

2. **Build the project using Maven**:
    ```sh
    mvn clean package
    ```

3. **Run the application**:
    ```sh
    java --module-path /usr/share/openjfx/lib --add-modules javafx.controls,javafx.fxml,javafx.web,javafx.swing,javafx.media -jar target/Pomodazzle-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```

## Usage

1. **Start the Timer**: Click the "Start Pomodoro" button to begin the timer.
2. **Pause the Timer**: Click the "Pause Pomodoro" button to pause the timer.
3. **Reset the Timer**: Click the "Reset" button to reset the timer.
4. **Donate**: Click the "Donate" button to open the donation page in your default web browser.

## Development

### Prerequisites

- Ensure you have Java 21 and Maven installed.
- Install JavaFX on your system.

### Running Locally

1. **Open the project in IntelliJ IDEA**.
2. **Build and run the project** using the IDE's built-in tools.

### Building the .deb Package

1. **Run the GitHub Actions workflow** to build and package the application:
    ```yaml
    name: Build and Package JavaFX Application

    on:
      push:
        branches:
          - master
      pull_request:
        branches:
          - master

    jobs:
      build:
        runs-on: ubuntu-latest

        steps:
          - name: Checkout code
            uses: actions/checkout@v3

          - name: Set up JDK 21
            uses: actions/setup-java@v3
            with:
              java-version: '21'
              distribution: 'temurin'

          - name: Build project
            run: |
              sudo apt-get update
              sudo apt-get install -y openjfx
              mvn clean package

          - name: Create .deb package
            run: |
              sudo apt-get install -y jpackage
              jpackage \
                --input target \
                --name Pomodazzle \
                --main-jar Pomodazzle-1.0-SNAPSHOT-jar-with-dependencies.jar \
                --main-class com.porfanid.pomodazzle.Pomodazzle \
                --module-path /usr/share/openjfx/lib \
                --add-modules javafx.controls,javafx.fxml,javafx.web \
                --type deb \
                --dest ./package \
                --icon icons/pomodazzle-icon.png \
                --linux-shortcut \
                --linux-menu-group "Utility"

          - name: Upload package artifact
            uses: actions/upload-artifact@v3
            with:
              name: pomodazzle.deb
              path: ./package/Pomodazzle.deb
    ```

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Acknowledgements

- JavaFX
- Maven
- GitHub Actions

## Contact

For any inquiries, please contact the developer at [pavlos@orfanidis.net.gr](mailto:pavlos@orfanidis.net.gr) or create an issue at the issues tab.

## Attributions

The following sound is used in this project:
- <a href="https://freesound.org/people/221098HariPotter/sounds/655558/">Alarm - single beep 221098_AshtiHari_SD100_Term4.wav</a> by <a href="https://freesound.org/people/221098HariPotter/">221098HariPotter</a> | License: <a href="https://creativecommons.org/licenses/by/4.0/">Attribution 4.0</a>