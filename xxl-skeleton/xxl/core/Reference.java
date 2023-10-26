package xxl.core;

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

    private Literal _value;

    /**
     * Initializes a new reference to a cell with the specified row and column.
     *
     * @param row the row of the referenced cell.
     * @param col the column of the referenced cell.
     */
    public Reference(int row, int col) {
        _row = row;
        _col = col;
        Calculator.getCalculator().getSpreadsheet().getCell(row, col).addObserver(this);
        update();
    }

    public void accept(ContentVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Retrieves the value of the referenced cell.
     *
     * @return the value of the referenced cell as a `Literal`.
     */
    @Override
    public void update() {
        _value = Calculator.getCalculator().getSpreadsheet().getCell(_row, _col).getContent().value();
    }

    public Literal value() {
        return _value;
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
}
