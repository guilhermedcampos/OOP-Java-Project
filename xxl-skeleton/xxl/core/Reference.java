package xxl.core;

/**
 * Represents a reference to a cell in the spreadsheet.
 */
public class Reference extends Content {

    /**
     * The row coordinate of the referenced cell.
     */
    private int _row;

    /**
     * The column coordinate of the referenced cell.
     */
    private int _col;

    /**
     * The value of the referenced cell.
     */
    private Literal _value;

    /**
     * Constructs a new reference to a cell with the specified row and column coordinates.
     *
     * @param row the row coordinate of the referenced cell.
     * @param col the column coordinate of the referenced cell.
     */
    public Reference(int row, int col) {
        _row = row;
        _col = col;
        startObserving();
    }

    /**
     * Gets the connected cell corresponding to this reference.
     *
     * @return the connected cell.
     */
    @Override
    public Cell getConnectedCell() {
        return Calculator.getCalculator().getSpreadsheet().getCell(_row, _col);
    }

    /**
     * Computes the value of the referenced cell.
     */
    @Override
    public void compute() {
        _value = Calculator.getCalculator().getSpreadsheet().getCell(_row, _col).getContent().value();
    }

    /**
     * Gets the value of the referenced cell.
     *
     * @return the value of the referenced cell.
     */
    public Literal value() {
        return _value;
    }
    
    /**
     * Starts observing changes in the referenced cell.
     */
    public void startObserving() {
        setIsObserving(true, this);
        update();
    }

    /**
     * Stops observing changes in the referenced cell.
     */
    public void stopObserving() {
        setIsObserving(false, this);
    }

    /**
     * Accepts a visitor for content types in a spreadsheet cell.
     *
     * @param visitor the content visitor.
     */
    public void accept(ContentVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Returns a string representation of the reference.
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
}
