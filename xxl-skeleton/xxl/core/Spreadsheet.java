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
  private Cell[] _cells;
  
  // FIXME define attributes
  // FIXME define contructor(s)
  // FIXME define methods

  public Spreadsheet getSpreadsheet() {
    return this;
  }

  public getCell(int row, int col) {
    for (Cell cell : _cells) {
      if (cell.getRow() == row && cell.getCol() == col) {
        return cell;
      }
    }
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
        return row >= 1 && row <= numRows && col >= 1 && col <= numCols;
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
