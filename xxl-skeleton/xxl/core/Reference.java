package xxl.core;
import xxl.core.exception.OutOfBoundsException;

public class Reference extends Content {
    private int _row;
    private int _col;

    public Reference(int row, int col) {
        _row = row;
        _col = col;
    }

    protected Literal value() throws OutOfBoundsException {
        return Calculator.getSpreadsheet().getCell(_row, _col).value();
    }

    @Override
    public String toString() {
        return "(" + _row + ";" + _col + ")";
    }
} 

