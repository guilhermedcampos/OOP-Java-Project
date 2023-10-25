package xxl.core;

import xxl.core.exception.OutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a range of cells within a spreadsheet, defined by its starting and ending row and column coordinates.
 */
public class Range {
    private int _startRow;
    private int _endRow;
    private int _startCol;
    private int _endCol;

    /**
     * Constructs a Range object with the specified coordinates.
     *
     * @param startRow The starting row of the range.
     * @param endRow   The ending row of the range.
     * @param startCol The starting column of the range.
     * @param endCol   The ending column of the range.
     */
    public Range(int startRow, int endRow, int startCol, int endCol) {
        _startRow = startRow;
        _endRow = endRow;
        _startCol = startCol;
        _endCol = endCol;
    }

    public int getFirstRow(){
        return _startRow;
    }

    public int getFirstCol(){
        return _startCol;
    }

    /**
     * Creates a Range object from a range description string.
     *
     * @param rangeDescription A string describing the range in the format "startRow;startCol:endRow;endCol".
     *                        If a single cell is specified, it can be in the format "row;column".
     * @return A Range object representing the specified range.
     */
    public static Range buildRange(String rangeDescription) {
        String[] rangeCoordinates;
        int startRow, startCol, endRow, endCol;

        // Check if the range has more than one cell
        if (rangeDescription.indexOf(':') != -1) {
            rangeCoordinates = rangeDescription.split("[:;]");
            startRow = Integer.parseInt(rangeCoordinates[0]);
            startCol = Integer.parseInt(rangeCoordinates[1]);
            endRow = Integer.parseInt(rangeCoordinates[2]);
            endCol = Integer.parseInt(rangeCoordinates[3]);
        } else {
            // If the range has only one cell
            rangeCoordinates = rangeDescription.split(";");
            startRow = endRow = Integer.parseInt(rangeCoordinates[0]);
            startCol = endCol = Integer.parseInt(rangeCoordinates[1]);
        }
        return new Range(startRow, endRow, startCol, endCol);
    }

    /**
     * Traverses the cells within the range and returns an array of cells.
     *
     * @return An array of Cell objects representing the cells within the range.
     * @throws OutOfBoundsException if the traversal exceeds the bounds of the associated spreadsheet.
     */
    public Cell[] traverse() {
        Spreadsheet spreadsheet = Calculator.getSpreadsheet();
        List<Cell> cells = new ArrayList<>();

        for (int row = _startRow; row <= _endRow; row++) {
            for (int col = _startCol; col <= _endCol; col++) {
                cells.add(spreadsheet.getCell(row, col));
            }
        }
        return cells.toArray(new Cell[cells.size()]);
    }

    public boolean isRangeValid() throws OutOfBoundsException {
        Spreadsheet spreadsheet = Calculator.getSpreadsheet();

        if (_startCol != _endCol && _startRow != _endRow) {
            throw new OutOfBoundsException("Invalid range.");
        }

        for (int row = _startRow; row <= _endRow; row++) {
            for (int col = _startCol; col <= _endCol; col++) {
                if (!spreadsheet.isValidCell(row, col)) {
                    throw new OutOfBoundsException("Invalid range.");
                }
            }
        }
        return true;
    }


}
