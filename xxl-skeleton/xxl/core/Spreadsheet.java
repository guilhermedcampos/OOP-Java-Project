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
  private Cell[] _cutBuffer;
  private int _numCols;
  private int _numRows;
  private boolean _changed;
  private AbstractDataStructure _dataStructure;
  
  public Spreadsheet(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and columns must be greater than zero.");
        }
        _numRows = rows;
        _numCols = cols;

        _dataStructure = new MatrixDataStructure();

        // Initialize the data structure here
        _dataStructure.initialize(rows, cols);
    }

  public Spreadsheet getSpreadsheet() {
    return this;
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

  // Se if certain row and position corresponds to a valid cell
  public boolean isValidCell(int row, int col){
    if (row < 1 || row > _numRows || col < 1 || col > _numCols) {
      return false;
    }
    return true;
  }


  // funções que envolvem a DataStructure implementada (Matriz)
  public Cell getCell(int row, int col) throws OutOfBoundsException {
  if (isValidCell(row,col)){
    return _dataStructure.getCell(row, col);
  } else {
    throw new OutOfBoundsException("Invalid cell coordinates: Row " + row + ", Column " + col);
  }
  }

  public void insert(int row, int col, Content content) throws OutOfBoundsException{
  if (isValidCell(row,col)){
    _dataStructure.setContent(row,col,content);
  } else {
    throw new OutOfBoundsException("Invalid cell coordinates: Row " + row + ", Column " + col);
  }
  }

  public Content getContentAt(int row, int col) throws OutOfBoundsException{
  if (isValidCell(row,col)){
    return _dataStructure.getContent(row, col);
  } else {
    throw new OutOfBoundsException("Invalid cell coordinates: Row " + row + ", Column " + col);
  }
  }
}

  /**
   * Insert specified content in specified address.
   *
   * @param row the row of the cell to change 
   * param column the column of the cell to change
   * @param contentSpecification the specification in a Conent format of the content to put
   *        in the specified cell.
   */
