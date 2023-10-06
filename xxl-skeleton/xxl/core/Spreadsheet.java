package xxl.core;

// FIXME import classes

import java.io.Serial;
import java.io.Serializable;

import xxl.core.exception.UnrecognizedEntryException;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {
  @Serial
  private static final long serialVersionUID = 202308312359L;
  private Cell[][] _cells;
  private int _numCols;
  private int _numRows;
  
  // FIXME define attributes
  // FIXME define contructor(s)
  // FIXME define methods

  public Spreadsheet(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and columns must be greater than zero.");
        }
        this._numRows = rows;
        this._numCols = cols;
        this._cells = new Cell[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this._cells[row][col] = new Cell(row + 1, col + 1);
            }
        }
    }

  public Spreadsheet getSpreadsheet() {
    return this;
  }

  public Cell getCell(int row, int col) {
    return _cells[row-1][col-1];
  }
  public int getCols() {
    return _numCols;
  }

  public int getRows() {
    return this._numRows;
  }

  public Content getContentAt(int row, int col) {
        // Check if the provided row and column are valid
        if (isValidCell(row, col)) {
            Cell cell = cells[row - 1][col - 1];
            if (cell != null) {
                return cell.getContent();
            }
        }
        // Return null for empty or invalid cells
        return null;
    }

    private boolean isValidCell(int row, int col) {
        return row >= 1 && row <= _numRows && col >= 1 && col <= _numCols;
    }
  
  /**
   * Insert specified content in specified address.
   *
   * @param row the row of the cell to change 
   * param column the column of the cell to change
   * @param contentSpecification the specification in a string format of the content to put
   *        in the specified cell.
   */
  public void insertContent(int row, int column, String contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
    //FIXME implement method
  }
}
