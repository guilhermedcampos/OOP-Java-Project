package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import xxl.core.exception.OutOfBoundsException;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {
  @Serial
  private static final long serialVersionUID = 202308312359L;
  private String _name;
  private Cell[] _cutBuffer;
  private int _numCols;
  private int _numRows;
  private AbstractDataStructure _dataStructure;
  private boolean _isChanged;

  /**
   * Constructor for creating a new spreadsheet with the specified number of rows
   * and columns.
   *
   * @param rows The number of rows in the spreadsheet.
   * @param cols The number of columns in the spreadsheet.
   * @throws IllegalArgumentException if rows or columns are less than or equal to
   *                                  zero.
   */
  public Spreadsheet(int rows, int cols) {
    if (rows <= 0 || cols <= 0) {
      throw new IllegalArgumentException("Rows and columns must be greater than zero.");
    }
    _numRows = rows;
    _numCols = cols;
    _isChanged = true;

    _dataStructure = new MatrixDataStructure();

    // Initialize the data structure here
    _dataStructure.initialize(rows, cols);
  }

  /**
   * Get the current instance of the spreadsheet.
   *
   * @return The current instance of the spreadsheet.
   */
  public Spreadsheet getSpreadsheet() {
    return this;
  }

  /**
   * Get the name of the spreadsheet.
   *
   * @return The name of the spreadsheet.
   */
  public String getName() {
    return _name;
  }

  /**
   * Set the name of the spreadsheet.
   *
   * @param name The name to set for the spreadsheet.
   */
  public void setName(String name) {
    _name = name;
  }

  /**
   * Get the number of columns in the spreadsheet.
   *
   * @return The number of columns in the spreadsheet.
   */
  public int getCols() {
    return _numCols;
  }

  /**
   * Get the number of rows in the spreadsheet.
   *
   * @return The number of rows in the spreadsheet.
   */
  public int getRows() {
    return _numRows;
  }

  /**
   * Get the cut buffer of the spreadsheet.
   *
   * @return The cut buffer of the spreadsheet.
   */
  public Cell[] getCutBufer() {
    return _cutBuffer;
  }

  /**
   * Copy data from the specified range.
   *
   * @param range The range from which to copy data.
   */
  public void copy(String range) {
    // Implementation for copying data goes here
  }

  /**
   * Clear data in the specified range.
   *
   * @param range The range from which to clear data.
   */
  public void clear(String range) {
    // Implementation for clearing data goes here
  }

  /**
   * Add a user to the spreadsheet.
   *
   * @param u The user to add to the spreadsheet.
   */
  public void addUser(User u) {
    // Implementation for adding a user goes here
  }

  /**
   * Check if a certain row and position corresponds to a valid cell.
   *
   * @param row The row to check.
   * @param col The column to check.
   * @return true if the cell is valid, false otherwise.
   */
  public boolean isValidCell(int row, int col) {
    if (row < 1 || row > _numRows || col < 1 || col > _numCols) {
      return false;
    }
    return true;
  }

  /**
   * Get the cell at the specified row and column.
   *
   * @param row The row of the cell to retrieve.
   * @param col The column of the cell to retrieve.
   * @return The cell at the specified position.
   * @throws OutOfBoundsException if the cell coordinates are invalid.
   */
  public Cell getCell(int row, int col) throws OutOfBoundsException {
    if (isValidCell(row, col)) {
      return _dataStructure.getCell(row, col);
    } else {
      throw new OutOfBoundsException("Invalid cell coordinates: Row " + row + ", Column " + col);
    }
  }

  /**
   * Insert content into the cell at the specified row and column.
   *
   * @param row     The row of the cell to change.
   * @param col     The column of the cell to change.
   * @param content The content to put in the specified cell.
   * @throws OutOfBoundsException if the cell coordinates are invalid.
   */
  public void insert(int row, int col, Content content) throws OutOfBoundsException {
    if (isValidCell(row, col)) {
      _dataStructure.setContent(row, col, content);
    } else {
      throw new OutOfBoundsException("Invalid cell coordinates: Row " + row + ", Column " + col);
    }
  }

  /**
   * Get the content at the specified row and column.
   *
   * @param row The row of the cell to change.
   * @param col The column of the cell to change.
   * @return The content in the specified cell.
   * @throws OutOfBoundsException if the cell coordinates are invalid.
   */
  public Content getContentAt(int row, int col) throws OutOfBoundsException {
    if (isValidCell(row, col)) {
      return _dataStructure.getContent(row, col);
    } else {
      throw new OutOfBoundsException("Invalid cell coordinates: Row " + row + ", Column " + col);
    }
  }

  public boolean isChanged() {
    return _isChanged;
  }

  public void setChange(boolean bool) {
    _isChanged = bool;
  }
}
