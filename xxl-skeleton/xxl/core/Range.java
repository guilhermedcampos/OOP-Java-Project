package xxl.core;

import java.util.*;
import xxl.core.exception.OutOfBoundsException;

public class Range {
    private int _startRow;
    private int _endRow;
    private int _startCol;
    private int _endCol;

    public Range(int startRow, int endRow, int startCol, int endCol) { // exception de range valida dentro spreadsheet, e 0
        this._startRow = startRow;
        this._endRow = endRow;
        this._startCol = startCol;
        this._endCol = endCol;
    }

    
    public Range buildRange(String rangeDescription) {
        String[] rangeCoordinates;
        int startRow, startCol, endRow, endCol;
        
        // caso a range tenha mais do que uma célula
        if (rangeDescription.indexOf(':') != -1) { 
            rangeCoordinates = rangeDescription.split("[:;]");
            startRow = Integer.parseInt(rangeCoordinates[0]);
            startCol = Integer.parseInt(rangeCoordinates[1]);
            endRow = Integer.parseInt(rangeCoordinates[2]);
            endCol = Integer.parseInt(rangeCoordinates[3]);

        // caso a range tenha só uma celula
        } else {
            rangeCoordinates = rangeDescription.split(";");
            startRow = endRow = Integer.parseInt(rangeCoordinates[0]);
            startCol = endCol = Integer.parseInt(rangeCoordinates[1]);
        }
        return new Range(startRow, endRow, startCol, endCol);
    }

    public Cell[] traverse() throws OutOfBoundsException {
        Spreadsheet spreadsheet = Calculator.getSpreadsheet();
        List<Cell> cells = new ArrayList<>();

        for (int row = _startRow; row <= _endRow; row++) {
            for (int col = _startCol; col <= _endCol; col++) {
                cells.add(spreadsheet.getCell(row, col));
            }
        }
        return cells.toArray(new Cell[cells.size()]);
    }



}