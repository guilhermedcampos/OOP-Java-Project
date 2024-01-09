# CalculatorApp

## Object Oriented Programming Project

This project is an application that serves as a calculator and spreadsheet manager. It employs various Object-Oriented Programming (OOP) design patterns, including visitor, singleton, observer, composite, and state. The codebase adheres to the principles of open-closed design, allowing for easy extension while maintaining validation of the open-closed principle.
Features:

    Calculator functionality
    Spreadsheet management with various operations
    OOP Design Patterns:
        Visitor
        Singleton
        Observer
        Composite
        State

### Getting Started

Clone the repository to your local machine using the following command in your terminal or command prompt:

```bash
git clone https://github.com/guilhermedcampos/calculator-app/
```

### Dependencies

The project uses the `po-uilib.jar` framework for user interaction (updated as of October 3rd). Additionally, Ensure that you have the correct version compiled for Java 17 or higher.

### Project Structure

The project repository contains the following structure:

    app: Application-specific code
    core: Core functionality and classes
    tests: A set of 100 run tests for testing the code

### Compile the project:

    javac -cp path/to/po-uilib.jar:. `find xxl -name "*.java"`

### Run the application:

    java -cp path/to/po-uilib.jar:. xxl.app.App

### Serialization

Spreadsheets are serialized and saved with a `.ser` extension. Serialization allows for the preservation of spreadsheet data between application sessions.

### User-Related Features

The user-related part of the project is not fully finished. This aspect was deemed irrelevant for the core functionality of the application and may be extended in future developments.

### Run the tests:

    chmod +x runtests.sh
  
    ./runtests.sh
