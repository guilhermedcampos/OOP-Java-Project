package xxl.core;

// FIXME import classes

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
  private Cell[][] _cells;
  private Cell[] _cutBuffer;
  private int _numCols;
  private int _numRows;
  private boolean _changed;
  private AbstractDataStructure _dataStructure;
  
  public Spreadsheet(int rows, int cols, AbstractDataStructure dataStructure) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and columns must be greater than zero.");
        }
        _numRows = rows;
        _numCols = cols;
        _dataStructure = dataStructure;

        // Initialize the data structure here
        _dataStructure.initialize(rows, cols);
    }

  public Spreadsheet getSpreadsheet() {
    return this;
  }

  public Cell getCell(int row, int col) throws OutOfBoundsException {
      if (row < 1 || row > _numRows || col < 1 || col > _numCols) {
          throw new OutOfBoundsException("Invalid cell coordinates: Row " + row + ", Column " + col);
      }
      return _cells[row - 1][col - 1];
  }

  public String getName() {
    return _name;
  }

  public void setName(String name) {
    _name = name;
  }

  public int getCols() {
    return _numCols;
  }

  public int getRows() {
    return _numRows;
  }

  public Cell[] getCutBufer() {
    return _cutBuffer;
  }

  public void copy(String range) {

  }

  public void clear(String range) {

  }

  public void addUser(User u) {
  }

  /**
   * Insert specified content in specified address.
   *
   * @param row the row of the cell to change 
   * param column the column of the cell to change
   * @param contentSpecification the specification in a Conent format of the content to put
   *        in the specified cell.
   */
  public void insert(int row, int col, Content content) throws OutOfBoundsException{
    _dataStructure.setContent(row,col,content);
  }

  public Content getContentAt(int row, int col) {
      return _dataStructure.getContent(row, col);
    }
}
