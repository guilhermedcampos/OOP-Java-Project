package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import xxl.core.exception.OutOfBoundsException;
import xxl.core.User;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

  @Serial
  private static final long serialVersionUID = 202308312359L;

  private String _name;

  private CutBuffer _cutBuffer;

  private int _numCols;

  private int _numRows;

  private AbstractDataStructure _dataStructure;

  private boolean _isChanged;

  private List<User> _users = new ArrayList<>();

  public Spreadsheet(
      int rows,
      int cols) {
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

  public CutBuffer getCutBuffer() {
    return _cutBuffer;
  }

  public void setCutBuffer(CutBuffer cutbuffer) {
    _cutBuffer = cutbuffer;
  }

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

  public void clear(String range) throws OutOfBoundsException {
    Range parsedRange = Range.buildRange(range);

    if (parsedRange.isRangeValid()) {
      Cell[] cells = parsedRange.traverse();
      for (Cell cell : cells) {
        insert(cell.getRow(), cell.getCol(), new Null());
      }
    }
  }

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

  public void addUser(User user) {
  }

  public boolean isValidCell(int row, int col) {
    return row >= 1 && row <= _numRows && col >= 1 && col <= _numCols;
  }

  public Cell getCell(int row, int col) {
    return _dataStructure.getCell(row, col);
  }

  public void insert(int row, int col, Content content) throws OutOfBoundsException {
    if (isValidCell(row, col)) {
      _dataStructure.getCell(row, col).setContent(content);
    } else {
      throw new OutOfBoundsException("Cell is out of bounds.");
    }
    setChange(true);
  }

  public Content getContentAt(int row, int col) {
    return _dataStructure.getContent(row, col);
  }

  public boolean isChanged() {
    return _isChanged;
  }

  public void setChange(boolean bool) {
    _isChanged = bool;
  }

  public Cell[][] getCells() {
    return _dataStructure.getCells();
  }
}
