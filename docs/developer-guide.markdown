---
layout: page
title: Developer Guide
permalink: /developer-guide/
---

# Developer Guide

Welcome to the Pomodazzle developer guide! This guide provides information on how to set up your development environment, build the application, and contribute to the project.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Setting Up the Development Environment](#setting-up-the-development-environment)
- [Building the Application](#building-the-application)
- [Contributing to the Project](#contributing-to-the-project)
- [Resources](#resources)
- [License](#license)
- [Acknowledgements](#acknowledgements)
- [Contact](#contact)

## Prerequisites

Before you begin developing for Pomodazzle, ensure you have the following prerequisites installed on your system:
 - Java 21
 - JavaFX 22.0.1
 - Maven
 - Git

## Setting Up the Development Environment

To set up your development environment for Pomodazzle, follow these steps:
- Clone the repository:
    ```sh
      git clone https://github.com/porfanid/Pomodazzle.git
      cd Pomodazzle
    ```
- Open the project in your preferred IDE (e.g., IntelliJ IDEA).
- Install the necessary dependencies using Maven:
    ```sh
      mvn clean install
    ```
- Run the application:
    ```sh
      mvn javafx:run
    ```
- Start developing!
- Create a new branch for your feature or bug fix:
    ```sh
      git checkout -b feature/your-feature-name
    ```
- Make your changes and commit them:
    ```sh
      git add .
      git commit -m "Add your commit message here"
    ```
- Push your changes to your fork:
    ```sh
      git push origin feature/your-feature-name
    ```
- Open a pull request on GitHub to merge your changes into the main branch.
- Wait for your changes to be reviewed and merged.