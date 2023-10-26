package xxl.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;

import xxl.core.exception.ImportFileException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.OutOfBoundsException;
import xxl.core.exception.UnavailableFileException;
import xxl.core.exception.UnrecognizedEntryException;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Class representing a spreadsheet application.
 */
public class Calculator {

  /**
   * The currently active spreadsheet.
   */
  private static Spreadsheet _currentSpreadsheet;

  /**
   * The current user who is using the application.
   */
  private User _currentUser;

  /**
   * A list of users who have access to the application.
   */
  private List<User> _users;

  private static Calculator _calculator;

  /**
   * Constructs a new Calculator object.
   * Initializes the list of users as an empty ArrayList.
   */
  private Calculator() {
    _users = new ArrayList<>();
  }

  public static Calculator getCalculator() {
    if (_calculator == null) {
      _calculator = new Calculator();
    }
    return _calculator;
  }

  /**
   * Return the current spreadsheet.
   *
   * @return the current spreadsheet of this application. This reference can be
   *         null.
   */
  public Spreadsheet getSpreadsheet() {
    return _currentSpreadsheet;
  }

  /**
   * Define a new current spreadsheet.
   *
   * @param spreadsheet The spreadsheet to set as the current one.
   */
  public static void setSpreadsheet(Spreadsheet spreadsheet) {
    _currentSpreadsheet = spreadsheet;
  }

  public CutBuffer getCutBuffer() {
    return _currentSpreadsheet.getCutBuffer();
  }

  /**
   * Creates a new user and adds them to the collection of users.
   *
   * @param name The name of the user to create.
   * @return true if the user was successfully created and added; false otherwise.
   */
  public boolean createUser(String name) {
    User user = new User(name);
    return _users.add(user);
  }

  /**
   * Saves the serialized application's state into the specified file. The current
   * network is
   * associated to this file.
   *
   * @throws FileNotFoundException           if for some reason the file cannot be
   *                                         created or opened.
   * @throws MissingFileAssociationException if the current network does not have
   *                                         a file.
   * @throws IOException                     if there is some error while
   *                                         serializing the state of the network
   *                                         to disk.
   */
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    ObjectOutputStream objectOut = null;
    try {
      try (FileOutputStream fileOut = new FileOutputStream(_currentSpreadsheet.getName() + ".ser")) {
        objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(_currentSpreadsheet);
      }
    } finally {
      if (objectOut != null) {
        objectOut.close();
      }
    }
  }

  /**
   * Saves the serialized application's state into the specified file. The current
   * network is
   * associated to this file.
   *
   * @param fileName the name of the file.
   * @throws FileNotFoundException           if for some reason the file cannot be
   *                                         created or opened.
   * @throws MissingFileAssociationException if the current network does not have
   *                                         a file.
   * @throws IOException                     if there is some error while
   *                                         serializing the state of the network
   *                                         to disk.
   */
  public void saveAs(String fileName) throws FileNotFoundException, MissingFileAssociationException, IOException {
    ObjectOutputStream objectOut = null;
    try {

      // Serialize and save the spreadsheet with the specified file name in the
      // current directory
      try (FileOutputStream fileOut = new FileOutputStream(fileName + ".ser")) {
        _currentSpreadsheet.setName(fileName);
        objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(_currentSpreadsheet);
      }
    } finally {
      if (objectOut != null) {
        objectOut.close();
      }
    }
  }

  /**
   * @param fileName name of the file containing the serialized application's
   *                 state
   *                 to load.
   * @throws UnavailableFileException if the specified file does not exist or
   *                                  there is
   *                                  an error while processing this file.
   */
  public void load(String fileName) throws UnavailableFileException {
    try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(fileName + ".ser"))) {
      Spreadsheet spreadsheet = (Spreadsheet) objectIn.readObject();
      spreadsheet.setChange(false);
      Calculator.getCalculator().setSpreadsheet(spreadsheet);
    } catch (FileNotFoundException | ClassNotFoundException e) {
      throw new UnavailableFileException(fileName);
    } catch (IOException e) {
      throw new UnavailableFileException(fileName);
    }
  }

  /**
   * Read text input file and create domain entities.
   *
   * @param filename name of the text input file.
   * @throws ImportFileException when an error occurs importing the file.
   */
  public void importFile(String filename)
      throws ImportFileException {
    try {
      Parser parser = new Parser();
      _currentSpreadsheet = parser.parseFile(filename);
    } catch (IOException | UnrecognizedEntryException | OutOfBoundsException e) {
      throw new ImportFileException(filename, e);
    }
  }
}
