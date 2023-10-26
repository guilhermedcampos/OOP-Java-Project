package xxl.core;

import java.io.Serial;
import java.io.Serializable;

import xxl.core.exception.OutOfBoundsException;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

  /**
   * A unique identifier for serialization.
   */
  @Serial
  private static final long serialVersionUID = 202308312359L;

  /**
   * The name of the spreadsheet.
   */
  private String _name;

  /**
   * An array used to store cut cells for clipboard operations.
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
   * The data structure used to store cells in the spreadsheet.
   */
  private AbstractDataStructure _dataStructure;

  /**
   * A flag indicating whether the spreadsheet has been changed.
   */
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

    // Initializes the data structure
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
  public CutBuffer getCutBuffer() {
    return _cutBuffer;
  }

/**
 * Copy data from the specified range.
 *
 * @param range The range from which to copy data.
 */
public void copy(String range) throws OutOfBoundsException {
    Range parsedRange = Range.buildRange(range);
    Cell[] cells = parsedRange.traverse();
    boolean variesInColumns = false;

    if (parsedRange.isRangeValid()) {
        CutBuffer cutBuffer = new CutBuffer();
        
        if (cells.length > 1) {
            if (cells[0].getCol() != cells[1].getCol()){
             variesInColumns = true;
            }
        }

        cutBuffer.setContents(new Content[cells.length]);
        for (int i = 0; i < cells.length; i++) {
            Content originalContent = cells[i].getContent();
            cutBuffer.getContents()[i] = originalContent;
        }

        _cutBuffer = cutBuffer;

        // Set the flag indicating whether the range varies in columns
        _cutBuffer.setVariesInColumns(variesInColumns);
    }
}



  /**
   * Clear data in the specified range.
   *
   * @param range The range from which to clear data.
   */
  public void clear(String range) throws OutOfBoundsException {
    // Attempt to parse the given range string into a range object
    Range parsedRange = Range.buildRange(range);
    
    if (parsedRange.isRangeValid()) {
        // Traverse the cells within the range and display their content
        Cell[] cells = parsedRange.traverse();
        for (Cell cell : cells) {
          insert(cell.getRow(),cell.getCol(), new Null());
        }
    }
  }

  /**
   * Add a user to the spreadsheet.
   *
   * @param user The user to add to the spreadsheet.
   */
  public void addUser(User user) {

  }

  /**
   * Check if a certain row and position corresponds to a valid cell.
   *
   * @param row The row to check.
   * @param col The column to check.
   * @return true if the cell is valid, false otherwise.
   */
  public boolean isValidCell(int row, int col) {
    return row >= 1 && row <= _numRows && col >= 1 && col <= _numCols;
  }

  /**
   * Get the cell at the specified row and column.
   *
   * @param row The row of the cell to retrieve.
   * @param col The column of the cell to retrieve.
   * @return The cell at the specified position.
   * @throws OutOfBoundsException if the specified cell is out of bounds.
   */
  public Cell getCell(int row, int col) {
        return _dataStructure.getCell(row, col);
  }
  
  /**
  * Insert content into the cell at the specified row and column.
  *
  * @param row     The row of the cell to change.
  * @param col     The column of the cell to change.
  * @param content The content to put in the specified cell.
  * @throws OutOfBoundsException if the specified cell is out of bounds.
  */
  public void insert(int row, int col, Content content) throws OutOfBoundsException {
    if (isValidCell(row, col)) {
        _dataStructure.setContent(row, col, content);
    } else {
        throw new OutOfBoundsException("Cell is out of bounds.");
    }
    setChange(true);
  }

  public void paste(String range) throws OutOfBoundsException {
    Range parsedRange = Range.buildRange(range);
    Cell[] cells = parsedRange.traverse();

    if (parsedRange.isRangeValid()) {
        if (cells.length == 1) {
            // If the range consists of a single cell, check how the cut buffer varies
            boolean variesInColumns = _cutBuffer.variesInColumns();

            for (int i = 0; i < _cutBuffer.getContents().length; i++) {
                if (variesInColumns) {
                    // If the cut buffer varies in columns, paste along the line
                    int newRow = cells[0].getRow();
                    int newCol = cells[0].getCol() + i;

                    if (newRow <= _numRows && newCol <= _numCols) {
                        insert(newRow, newCol, _cutBuffer.getContent(i));
                    }
                } else {
                    // If the cut buffer varies in rows, paste along the column
                    int newRow = cells[0].getRow() + i;
                    int newCol = cells[0].getCol();

                    if (newRow <= _numRows && newCol <= _numCols) {
                        insert(newRow, newCol, _cutBuffer.getContent(i));
                    }
                }
            }
        } else {
            // If the range consists of multiple cells, paste along the provided range
            for (int i = 0; i < cells.length && i < _cutBuffer.getContents().length; i++) {
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
  * Get the content at the specified row and column.
  *
  * @param row The row of the cell to change.
  * @param col The column of the cell to change.
  * @return The content in the specified cell.
  */
  public Content getContentAt(int row, int col) {
    return _dataStructure.getContent(row, col);
  }

  /**
  * Check if the spreadsheet has been changed.
  *
  * @return True if the spreadsheet has been changed, false otherwise.
  */
  public boolean isChanged() {
    return _isChanged;
  }

  /**
  * Set the change status of the spreadsheet.
  *
  * @param bool True if the spreadsheet has been changed, false otherwise.
  */
  public void setChange(boolean bool) {
    _isChanged = bool;
  }

}
