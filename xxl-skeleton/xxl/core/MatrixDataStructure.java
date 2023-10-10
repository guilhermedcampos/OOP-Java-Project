package xxl.core;

/**
 * Represents a matrix-based data structure for storing spreadsheet cells and content.
 */
public class MatrixDataStructure implements AbstractDataStructure {
    
    private Cell[][] _cells;

    /**
     * Initializes the matrix data structure with the specified number of rows and columns.
     *
     * @param rows the number of rows.
     * @param cols the number of columns.
     */
    @Override
    public void initialize(int rows, int cols) {
        _cells = new Cell[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                _cells[row][col] = new Cell(row + 1, col + 1);
            }
        }
    }

    /**
     * Retrieves the cell at the specified row and column.
     *
     * @param row the row of the cell (1-based).
     * @param col the column of the cell (1-based).
     * @return the cell at the specified location.
     */
    @Override
    public Cell getCell(int row, int col) {
        return _cells[row - 1][col - 1];
    }

    /**
     * Sets the content of the cell at the specified row and column.
     *
     * @param row     the row of the cell (1-based).
     * @param col     the column of the cell (1-based).
     * @param content the content to set in the cell.
     */
    @Override
    public void setContent(int row, int col, Content content) {
        _cells[row - 1][col - 1].setContent(content);
    }

    /**
     * Retrieves the content of the cell at the specified row and column.
     *
     * @param row the row of the cell (1-based).
     * @param col the column of the cell (1-based).
     * @return the content of the cell.
     */
    @Override
    public Content getContent(int row, int col) {
        return _cells[row - 1][col - 1].getContent();
    }
}
