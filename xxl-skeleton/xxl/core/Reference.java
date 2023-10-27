package xxl.core;

/**
 * Represents a reference to a cell in the spreadsheet.
 */
public class Reference extends Content {

    private int _row;
    private int _col;
    private Literal _value;

    public Reference(int row, int col) {
        _row = row;
        _col = col;
        startObserving();
    }

    public void accept(ContentVisitor visitor) {
        visitor.visit(this);
    }

    public void startObserving() {
        setIsObserving(true, this);
        update();
    }

    public void stopObserving() {
        setIsObserving(false, this);
    }

    @Override
    public void compute() {
        _value = Calculator.getCalculator().getSpreadsheet().getCell(_row, _col).getContent().value();
    }

    public Literal value() {
        return _value;
    }

    @Override
    public Cell getConnectedCell() {
        return Calculator.getCalculator().getSpreadsheet().getCell(_row, _col);
    }

    @Override
    public String toString() {
        if (value().toString().equals("")) {
            return "#VALUE" + "=" + _row + ";" + _col;
        }
        return value().toString() + "=" + _row + ";" + _col;
    }
}
