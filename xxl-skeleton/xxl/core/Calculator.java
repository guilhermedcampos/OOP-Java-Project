package xxl.core;

import java.io.IOException;
import java.io.FileNotFoundException;

import xxl.core.exception.ImportFileException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.UnavailableFileException;
import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.exception.EvaluationException;
import xxl.core.exception.OutOfBoundsException;
// FIXME import classes
//import xxl.app.main.DoNew;
//import xxl.app.main.DoOpen;
//import xxl.app.main.DoSave;

/**
 * Class representing a spreadsheet application.
 */
public class Calculator {
  /** The current spreadsheet. */
  private static Spreadsheet _currentSpreadsheet;
  AbstractDataStructure _dataStructure = new MatrixDataStructure();
  // FIXME add more fields and methods if needed
  private User _currentUser;
  private User[] _users;

  /**
   * Return the current spreadsheet.
   *
   * @returns the current spreadsheet of this application. This reference can be null.
   */
  public static Spreadsheet getSpreadsheet() {  //final
    return _currentSpreadsheet;
  }

  public static void setSpreadsheet(Spreadsheet s) {
    _currentSpreadsheet = s;
  }

  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    // FIXME implement serialization method
  }
  
  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    // FIXME implement serialization method
  }
  
  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   */
  public void load(String filename) throws UnavailableFileException {
    // FIXME implement serialization method
  }
  
  /**
   * Read text input file and create domain entities.
   *
   * @param filename name of the text input file
   * @throws ImportFileException
   */
    public void importFile(String filename) throws IOException, UnrecognizedEntryException, EvaluationException, OutOfBoundsException {
        try {
            Parser parser = new Parser(_dataStructure);
            _currentSpreadsheet = parser.parseFile(filename);
        } catch (IOException | UnrecognizedEntryException e) {
            // Handle or rethrow exceptions
            throw e;
        }
    }

    // Rest of your methods and class members...
}


