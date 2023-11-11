package xxl.core;

import xxl.core.exception.OutOfBoundsException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a range of cells within a spreadsheet, defined by its starting and
 * ending row and column coordinates.
 */
public class Range {
    /**
     * The starting row coordinate of the range.
     */
    private int _startRow;

    /**
     * The ending row coordinate of the range.
     */
    private int _endRow;

    /**
     * The starting column coordinate of the range.
     */
    private int _startCol;

    /**
     * The ending column coordinate of the range.
     */
    private int _endCol;

    /**
     * Constructs a new Range with the specified coordinates.
     *
     * @param startRow the starting row coordinate.
     * @param endRow   the ending row coordinate.
     * @param startCol the starting column coordinate.
     * @param endCol   the ending column coordinate.
     */
    public Range(int startRow, int endRow, int startCol, int endCol) {
        _startRow = startRow;
        _endRow = endRow;
        _startCol = startCol;
        _endCol = endCol;
    }

    /**
     * Builds a Range object from the given range description.
     *
     * @param rangeDescription the range description in the format "startRow;startCol:endRow;endCol".
     *                         If a single cell is specified, the format is "row;col".
     * @return the constructed Range object.
     */
    public static Range buildRange(String rangeDescription) {
        String[] rangeCoordinates;
        int startRow, startCol, endRow, endCol;

        if (rangeDescription.indexOf(':') != -1) {
            rangeCoordinates = rangeDescription.split("[:;]");
            startRow = Integer.parseInt(rangeCoordinates[0]);
            startCol = Integer.parseInt(rangeCoordinates[1]);
            endRow = Integer.parseInt(rangeCoordinates[2]);
            endCol = Integer.parseInt(rangeCoordinates[3]);
        } else {
            rangeCoordinates = rangeDescription.split(";");
            startRow = endRow = Integer.parseInt(rangeCoordinates[0]);
            startCol = endCol = Integer.parseInt(rangeCoordinates[1]);
        }
        return new Range(startRow, endRow, startCol, endCol);
    }

    /**
     * Traverses the range and returns an array of cells within the specified
     * range in the spreadsheet.
     *
     * @return an array of Cell objects within the range.
     */
    public Cell[] traverse() {
        Spreadsheet spreadsheet = Calculator.getCalculator().getSpreadsheet();
        List<Cell> cells = new ArrayList<>();

        for (int row = _startRow; row <= _endRow; row++) {
            for (int col = _startCol; col <= _endCol; col++) {
                cells.add(spreadsheet.getCell(row, col));
            }
        }
        return cells.toArray(new Cell[cells.size()]);
    }

    /**
     * Checks if the range is valid, ensuring that the coordinates are within
     * the bounds of the spreadsheet.
     *
     * @return true if the range is valid, false otherwise.
     * @throws OutOfBoundsException if the range is invalid.
     */
    public boolean isRangeValid() throws OutOfBoundsException {
        Spreadsheet spreadsheet = Calculator.getCalculator().getSpreadsheet();

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