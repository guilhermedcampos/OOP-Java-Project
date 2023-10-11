package xxl.core;

import xxl.core.exception.EvaluationException;
import xxl.core.exception.OutOfBoundsException;

/**
 * Represents a reference to a cell in the spreadsheet.
 */
public class Reference extends Content {
    private int _row;
    private int _col;

    /**
     * Initializes a new reference to a cell with the specified row and column.
     *
     * @param row the row of the referenced cell.
     * @param col the column of the referenced cell.
     */
    public Reference(int row, int col) {
        _row = row;
        _col = col;
    }

    /**
     * Retrieves the value of the referenced cell.
     *
     * @return the value of the referenced cell as a `Literal`.
     * @throws OutOfBoundsException if there's an error due to out-of-bounds access.
     */
    @Override
    protected Literal value() throws OutOfBoundsException, EvaluationException {
        return Calculator.getSpreadsheet().getCell(_row, _col).value();
    }

    /**
     * Returns a string representation of the reference in the format "=row;col".
     *
     * @return a string representation of the reference.
     */
    @Override
    public String toString() {
        return "=" + _row + ";" + _col;
    }
}
