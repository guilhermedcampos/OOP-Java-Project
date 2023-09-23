public class Spreadsheet {
    private final int _rows;
    private final int _cols;
    private Cell[][] _cells;

    public Spreadsheet(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and columns must be greater than zero.");
        }
        this._rows = rows;
        this._cols = cols;
        this._cells = new Cell[rows][cols];
    }
    
}
