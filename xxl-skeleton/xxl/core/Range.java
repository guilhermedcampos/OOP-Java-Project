package xxl.core;

import xxl.core.exception.OutOfBoundsException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a range of cells within a spreadsheet, defined by its starting and
 * ending row and column coordinates.
 */
public class Range {
    private int _startRow;
    private int _endRow;
    private int _startCol;
    private int _endCol;

    public Range(int startRow, int endRow, int startCol, int endCol) {
        _startRow = startRow;
        _endRow = endRow;
        _startCol = startCol;
        _endCol = endCol;
    }

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