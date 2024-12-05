# Java Swing ToDo List Application

A simple and intuitive desktop task management application built with Java Swing.

## Features

- Add new tasks with title, description and due date
- Mark tasks as completed/uncompleted
- Edit existing tasks
- Delete tasks
- Scrollable task list interface
- Clean and intuitive user interface

## Project Structure

The project consists of five main classes:

- `ToDoApp.java`: Main application class that initializes the GUI
- `Task.java`: Data model class for individual tasks
- `TaskManager.java`: Business logic class for managing tasks
- `MainFrame.java`: Main window of the application
- `TaskPanel.java`: Custom panel for displaying individual tasks

## Class Details

### Task.java
- Represents a single task with title, description, due date and completion status
- Contains getters and setters for all properties
- Includes toString() method for string representation

### TaskManager.java
- Manages the list of tasks
- Provides methods for:
  - Adding tasks
  - Removing tasks
  - Toggling task completion
  - Getting task information
  - Updating tasks

### MainFrame.java
- Main window extending JFrame
- Provides the user interface for task management
- Features input fields for task creation
- Displays scrollable list of tasks
- Handles task editing and deletion

### TaskPanel.java
- Custom JPanel for displaying individual tasks
- Shows task title, due date, completion status
- Includes edit and delete buttons
- Updates visual style based on task completion

## Requirements

- Java SE Development Kit (JDK) 8 or higher
- Java Swing library (included in JDK)

## Running the Application

1. Compile all Java files:

```bash
javac *.java
``` 

2. Run the application:

```bash
java ToDoApp
```

Contributing
Feel free to fork this repository and submit pull requests to contribute to this project.

License
This project is open source and available under the MIT License. 