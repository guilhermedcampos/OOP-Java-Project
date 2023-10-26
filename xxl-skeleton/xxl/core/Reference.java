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

    public void accept(ContentVisitor visitor, Cell cell) {
        visitor.visit(this, cell);
    }

    /**
     * Retrieves the value of the referenced cell.
     *
     * @return the value of the referenced cell as a `Literal`.
     */
    @Override
    protected Literal value() {
        return Calculator.getSpreadsheet().getContentAt(_row, _col).value();
    }

    /**
     * Returns a string representation of the reference in the format "=row;col".
     *
     * @return a string representation of the reference.
     */
    @Override
    public String toString() {
            if (value().toString().equals("")) {
                return "#VALUE" + "=" + _row + ";" + _col;
            }
            return value().toString() + "=" + _row + ";" + _col;
    }


    @Override
    public boolean isReference() {
        return true;
    }

    /**
     * Adds an observer to the referenced cell.
     *
     * @param function the function that is the observer.
     */
    public void addFunctionObserver(Function function) {
        // Add the function as an observer to the referenced cell
            Cell referencedCell = Calculator.getSpreadsheet().getCell(_row, _col);
            referencedCell.addObserver(function);
    }
}
