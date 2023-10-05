package xxl.core;
public class Range {
    private Cell _startCell;
    private Cell _endCell;

    public Range(Cell start, Cell end) {
        this._startCell = start;
        this._endCell = end;
    }

    public Cell getStartCell() {
        return _startCell;
    }

    public Cell getEndCell() {
        return _endCell;
    }

    public Cell[] traverse() {

        String[] rangeCoordinates;
        int firstRow, firstColumn, lastRow, lastColumn;
        
        if (range.indexOf(':') != -1) {
            rangeCoordinates = range.split("[:;]");
            firstRow = Integer.parseInt(rangeCoordinates[0]);
            firstColumn = Integer.parseInt(rangeCoordinates[1]);
            lastRow = Integer.parseInt(rangeCoordinates[2]);
            lastColumn = Integer.parseInt(rangeCoordinates[3]);
        } else {
            rangeCoordinates = range.split(";");
            firstRow = lastRow = Integer.parseInt(rangeCoordinates[0]);
            firstColumn = lastColumn = Integer.parseInt(rangeCoordinates[1]);
        }
        Cell start = new Cell(firstRow,firstColumn);
        Cell end = new Cell(lastColumn, lastColumn);

        Range range = Range(start,end);
        return range.traverse();
    }


    /* Na classe Spreadsheet preciso de algo com a seguinte funcionalidade
  Range createRange(String range) throws ? {
    

    // check if coordinates are valid
    // if yes
    return new Range with firstRow, firstColumn, lastRow, lastColumn and spreadsheet;
  }
  */

    public Cell[] traverse() {
        int numRows, numCols;
        
        // Determine if the range is in the same column or row
        if (_startCell.getRow() == _endCell.getRow()) {
            // Same row, calculate the number of columns
            numCols = Math.abs(_startCell.getCol() - _endCell.getCol()) + 1;
            numRows = 1; // Only one row
        } else if (_startCell.getCol() == _endCell.getCol()) {
            // Same column, calculate the number of rows
            numRows = Math.abs(_startCell.getRow() - _endCell.getRow()) + 1;
            numCols = 1; // Only one column
        } 
        
        Cell[] result = new Cell[numRows * numCols];
        
        int currentIndex = 0;
        
        if (_startCell.getRow() == _endCell.getRow()) {
            // Same row, iterate through columns
            int colStep = _startCell.getCol() <= _endCell.getCol() ? 1 : -1;
            for (int col = _startCell.getCol(); col != _endCell.getCol() + colStep; col += colStep) {
                result[currentIndex] = new Cell(_startCell.getRow(), col);
                currentIndex++;
            }
        } else {
            // Same column, iterate through rows
            int rowStep = _startCell.getRow() <= _endCell.getRow() ? 1 : -1;
            for (int row = _startCell.getRow(); row != _endCell.getRow() + rowStep; row += rowStep) {
                result[currentIndex] = new Cell(row, _startCell.getCol());
                currentIndex++;
            }
        }

        return result;
    }
}