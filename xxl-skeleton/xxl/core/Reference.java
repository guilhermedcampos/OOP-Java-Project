package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Represents a reference to a cell in the spreadsheet.
 */
public class Reference extends Content {

    /**
     * The row position referred to by this reference.
     */
    private int _row;

    /**
     * The column position referred to by this reference.
     */
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
     * @throws EvaluationException  if there is an error during evaluation.
     */
    @Override
    protected Literal value() throws EvaluationException {
        return Calculator.getSpreadsheet().getContentAt(_row, _col).value();
    }

    /**
     * Returns a string representation of the reference in the format "=row;col".
     *
     * @return a string representation of the reference.
     */
    @Override
    public String toString() {
        try {
            // if the value is an empty cell
            if (value().toString() == "") {
                return "#VALUE" + "=" + _row + ";" + _col;
            }
            return value().toString() + "=" + _row + ";" + _col;
        } catch (EvaluationException e) {
            return "#VALUE" + "=" + _row + ";" + _col;
        }
    }
}
