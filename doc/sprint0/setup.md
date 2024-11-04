# Setup for CodeEsc Project

## Introduction
This setup document outlines the steps necessary to establish the foundational architecture of the CodeEsc application, focusing on integrating the Model, View, and Controller (MVC) components.

## Development Environment Setup

### Required Software
- **Java Development Kit (JDK):** Make sure the latest version is being used
- **Integrated Development Environment (IDE):** Any IDEs that support Java development, e.g. IntellJ, Visual Studio Code, Eclipse and etc

### Database Installation
- **MySQL:** Set up a MySQL server for local development.

## MVC Setup and Database Connectivity

### Model
- Define a `Puzzle` class in the `src/model` directory. This class will handle data related to puzzle challenges.

### View
- Create a basic GUI using Java Swing in the `src/view` directory. This should include buttons and input fields for interacting with puzzles.

### Controller
- Develop a `PuzzleController` class in the `src/controller` directory. This class will mediate interactions between the View and the Model.

### Database Integration
- Set up JDBC to connect to the MySQL database. Include methods in the Model to perform basic CRUD operations.

### Initial Functionality
- Implement a feature where a button in the View creates a new `Puzzle` object in the database using the Controller.
- This serves as a proof of concept that the MVC components are integrated correctly.

### Functionality Testing
- Test the button functionality to ensure that pressing it results in a new entry in the database.

### Setup Documentation
- Document all steps taken and configurations made in this `setup.md` file to ensure reproducibility and maintain project standards.

