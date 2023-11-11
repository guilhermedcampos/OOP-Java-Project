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

### Prerequisites

    Java version 17 or higher
    po-uilib.jar (October 3rd 2023)

### Project Structure

The project repository contains the following structure:

    app: Application-specific code
    core: Core functionality and classes
    tests: A set of 100 run tests for testing the code

### Compile the project:

  javac -cp path/to/po-uilib.jar:. `find xxl -name "*.java"`

#### Run the application:

  java -cp path/to/po-uilib.jar:. xxl.app.App

#### Run the tests:

  chmod +x runtests.sh
  ./runtests.sh
