package xxl.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;

import xxl.core.exception.ImportFileException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.UnavailableFileException;
import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.exception.EvaluationException;
import xxl.core.exception.OutOfBoundsException;

import java.io.Serial;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
//import xxl.app.main.DoNew;
import xxl.app.main.DoOpen;
//import xxl.app.main.DoSave;

/**
 * Class representing a spreadsheet application.
 */
public class Calculator {
  /** The current spreadsheet. */
  private static Spreadsheet _currentSpreadsheet;
  private static ArrayList<Spreadsheet> _spreadsheets;

  // FIXME add more fields and methods if needed
  private User _currentUser;
  private List<User> _users;

  public Calculator() {
    _spreadsheets = new ArrayList<>();
    _users = new ArrayList<>();
  }

  /**
   * Return the current spreadsheet.
   *
   * @returns the current spreadsheet of this application. This reference can be
   *          null.
   */
  public static Spreadsheet getSpreadsheet() { // final
    return _currentSpreadsheet;
  }

  public static void setSpreadsheet(Spreadsheet s) {
    _currentSpreadsheet = s;
  }

  public void addSpreadsheet(Spreadsheet s) {
    _spreadsheets.add(s);
  }

  public boolean createUser(String name) {
    User u = new User(name);
    return _users.add(u);
  }

  public void createNewSpreadsheet() {
    // App.DoNew();
  }

  /**
   * Saves the serialized application's state into the file associated to the
   * current network.
   *
   * @throws FileNotFoundException           if for some reason the file cannot be
   *                                         created or opened.
   * @throws MissingFileAssociationException if the current network does not have
   *                                         a file.
   * @throws IOException                     if there is some error while
   *                                         serializing the state of the network
   *                                         to disk.
   */
  public void save() throws IOException {
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
   * @param filename the name of the file.
   * @throws FileNotFoundException           if for some reason the file cannot be
   *                                         created or opened.
   * @throws MissingFileAssociationException if the current network does not have
   *                                         a file.
   * @throws IOException                     if there is some error while
   *                                         serializing the state of the network
   *                                         to disk.
   */
  public void saveAs(String fileName) throws IOException {
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
   * @param filename name of the file containing the serialized application's
   *                 state
   *                 to load.
   * @throws UnavailableFileException if the specified file does not exist or
   *                                  there is
   *                                  an error while processing this file.
   */
  public void load(String fileName)
      throws UnavailableFileException, FileNotFoundException, IOException, ClassNotFoundException

  {
    ObjectInputStream objectIn = null;
    try {
      objectIn = new ObjectInputStream(new FileInputStream(fileName + ".ser"));
      Spreadsheet spreadsheet = (Spreadsheet) objectIn.readObject();
      // set changes as false
      spreadsheet.setChange(false);
      Calculator.setSpreadsheet(spreadsheet);
    } finally {
      if (objectIn != null) {
        objectIn.close();
      }
    }
  }

  /**
   * Read text input file and create domain entities.
   *
   * @param filename name of the text input file
   * @throws ImportFileException
   */
  public void importFile(String filename)
      throws ImportFileException, IOException, UnrecognizedEntryException, EvaluationException, OutOfBoundsException {
    try {
      Parser parser = new Parser();
      _currentSpreadsheet = parser.parseFile(filename);
    } catch (IOException | UnrecognizedEntryException e) {
      // Handle or rethrow exceptions
      throw e;
    }
  }

  // Rest of your methods and class members...
}
