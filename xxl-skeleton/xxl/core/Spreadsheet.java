package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import xxl.core.exception.OutOfBoundsException;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

  @Serial
  private static final long serialVersionUID = 202308312359L;

  /**
   * The name of the spreadsheet.
   */
  private String _name;

  /**
   * The cut buffer used for cut, copy, and paste operations.
   */
  private CutBuffer _cutBuffer;

  /**
   * The number of columns in the spreadsheet.
   */
  private int _numCols;

  /**
   * The number of rows in the spreadsheet.
   */
  private int _numRows;

  /**
   * The data structure representing the cells and content of the spreadsheet.
   */
  private AbstractDataStructure _dataStructure;

  /**
   * Indicates whether the spreadsheet has been changed.
   */
  private boolean _isChanged;

  /**
   * List of users associated with the spreadsheet.
   */
  private List<User> _users = new ArrayList<>();

  /**
   * Constructs a new spreadsheet with the specified number of rows and columns.
   *
   * @param rows the number of rows in the spreadsheet.
   * @param cols the number of columns in the spreadsheet.
   * @throws IllegalArgumentException if rows or columns are not greater than zero.
   */
  public Spreadsheet(int rows, int cols) {
    if (rows <= 0 || cols <= 0) {
      throw new IllegalArgumentException("Rows and columns must be greater than zero.");
    }
    _numRows = rows;
    _numCols = cols;
    _isChanged = true;
    _cutBuffer = new CutBuffer();

    _dataStructure = new MatrixDataStructure();
    _dataStructure.initialize(rows, cols);
  }

  /**
   * Gets the reference to the spreadsheet itself.
   *
   * @return the spreadsheet.
   */
  public Spreadsheet getSpreadsheet() {
    return this;
  }

  /**
   * Gets the name of the spreadsheet.
   *
   * @return the name of the spreadsheet.
   */
  public String getName() {
    return _name;
  }

  /**
   * Sets the name of the spreadsheet.
   *
   * @param name the new name for the spreadsheet.
   */
  public void setName(String name) {
    _name = name;
  }

  /**
   * Gets the number of columns in the spreadsheet.
   *
   * @return the number of columns.
   */
  public int getCols() {
    return _numCols;
  }

  /**
   * Gets the number of rows in the spreadsheet.
   *
   * @return the number of rows.
   */
  public int getRows() {
    return _numRows;
  }

  /**
   * Gets the cut buffer associated with the spreadsheet.
   *
   * @return the cut buffer.
   */
  public CutBuffer getCutBuffer() {
    return _cutBuffer;
  }

  /**
   * Sets a new cut buffer for the spreadsheet.
   *
   * @param cutbuffer the new cut buffer.
   */
  public void setCutBuffer(CutBuffer cutbuffer) {
    _cutBuffer = cutbuffer;
  }

  /**
   * Gets the content at the specified row and column.
   *
   * @param row the row index.
   * @param col the column index.
   * @return the content at the specified coordinates.
   */
  public Content getContentAt(int row, int col) {
    return _dataStructure.getContent(row, col);
  }

  /**
   * Checks if the spreadsheet has been changed.
   *
   * @return true if the spreadsheet has been changed, false otherwise.
   */
  public boolean isChanged() {
    return _isChanged;
  }

  /**
   * Sets the changed status of the spreadsheet.
   *
   * @param bool the new status (true if changed, false otherwise).
   */
  public void setChange(boolean bool) {
    _isChanged = bool;
  }

  /**
   * Gets the cell at the specified row and column.
   *
   * @param row the row index.
   * @param col the column index.
   * @return the cell at the specified coordinates.
   */
  public Cell getCell(int row, int col) {
    return _dataStructure.getCell(row, col);
  }
  
  /**
   * Gets a bidimensional array of cells representing the structure of the spreadsheet.
   *
   * @return a bidimensional array of cells.
   */
  public Cell[][] getCells() {
    return _dataStructure.getCells();
  }

  /**
   * Copies the contents of the specified range into the cut buffer.
   *
   * @param range the range of cells to copy.
   * @throws OutOfBoundsException if the range is invalid.
   */
  public void copy(String range) throws OutOfBoundsException {
    Range parsedRange = Range.buildRange(range);
    Cell[] cells = parsedRange.traverse();
    boolean variesInColumns = false;

    if (parsedRange.isRangeValid()) {
      CutBuffer cutBuffer = new CutBuffer();
      setChange(true);

      if (cells.length > 1) {
        if (cells[0].getCol() != cells[1].getCol()) {
          variesInColumns = true;
        }
      }

      cutBuffer.setContents(new Content[cells.length]);
      for (int i = 0; i < cells.length; i++) {
        Content originalContent = cells[i].getContent();
        cutBuffer.getContents()[i] = originalContent;
      }

      _cutBuffer = cutBuffer;

      _cutBuffer.setVariesInColumns(variesInColumns);
    }
  }

  /**
   * Clears the contents of the specified range in the spreadsheet.
   *
   * @param range the range of cells to clear.
   * @throws OutOfBoundsException if the range is invalid.
   */
  public void clear(String range) throws OutOfBoundsException {
    Range parsedRange = Range.buildRange(range);

    if (parsedRange.isRangeValid()) {
      Cell[] cells = parsedRange.traverse();
      for (Cell cell : cells) {
        insert(cell.getRow(), cell.getCol(), new Null());
      }
    }
  }

  /**
   * Pastes the contents of the cut buffer into the specified range.
   *
   * @param range the range of cells to paste into.
   * @throws OutOfBoundsException if the range is invalid.
   */
  public void paste(String range) throws OutOfBoundsException {
    Range parsedRange = Range.buildRange(range);
    Cell[] cells = parsedRange.traverse();

    if (parsedRange.isRangeValid()) {
      if (cells.length == 1) {
        boolean variesInColumns = _cutBuffer.variesInColumns();

        for (int i = 0; i < _cutBuffer.getContents().length; i++) {
          if (variesInColumns) {
            int newRow = cells[0].getRow();
            int newCol = cells[0].getCol() + i;

            if (newRow <= _numRows && newCol <= _numCols) {
              insert(newRow, newCol, _cutBuffer.getContent(i));
            }
          } else {
            int newRow = cells[0].getRow() + i;
            int newCol = cells[0].getCol();

            if (newRow <= _numRows && newCol <= _numCols) {
              insert(newRow, newCol, _cutBuffer.getContent(i));
            }
          }
        }
      } else if (_cutBuffer.getContents().length == cells.length) {
        for (int i = 0; i < cells.length; i++) {
          int newRow = cells[i].getRow();
          int newCol = cells[i].getCol();

          if (newRow <= _numRows && newCol <= _numCols) {
            insert(newRow, newCol, _cutBuffer.getContent(i));
          }
        }
      }
    }
  }

  /**
   * Adds a user to the list of users associated with the spreadsheet.
   *
   * @param user the user to add.
   */
  public void addUser(User user) {
    _users.add(user);
  }

  /**
   * Checks if the specified cell coordinates are valid within the spreadsheet.
   *
   * @param row the row index.
   * @param col the column index.
   * @return true if the cell is valid, false otherwise.
   */
  public boolean isValidCell(int row, int col) {
    return row >= 1 && row <= _numRows && col >= 1 && col <= _numCols;
  }

  /**
   * Inserts content into the cell at the specified row and column.
   *
   * @param row     the row index.
   * @param col     the column index.
   * @param content the content to insert.
   * @throws OutOfBoundsException if the cell is out of bounds.
   */
  public void insert(int row, int col, Content content) throws OutOfBoundsException {
    if (isValidCell(row, col)) {
      _dataStructure.getCell(row, col).setContent(content);
    } else {
      throw new OutOfBoundsException("Cell is out of bounds.");
    }
    setChange(true);
  }
}
