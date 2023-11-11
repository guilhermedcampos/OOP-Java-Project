package xxl.core;

import java.io.Serializable;

/**
 * Represents a matrix-based data structure for storing spreadsheet cells and
 * content.
 */
public class MatrixDataStructure implements AbstractDataStructure, Serializable {

    /** The matrix of cells storing spreadsheet data. */
    private Cell[][] _cells;

    /**
     * Initializes the matrix-based data structure with the specified number of rows and columns.
     *
     * @param rows the number of rows for the spreadsheet.
     * @param cols the number of columns for the spreadsheet.
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
     * Gets a cell at the specified row and column.
     *
     * @param row the row of the cell to retrieve.
     * @param col the column of the cell to retrieve.
     * @return the cell object at the specified row and column.
     */
    @Override
    public Cell getCell(int row, int col) {
        return _cells[row - 1][col - 1];
    }

    /**
     * Sets the content of a cell at the specified row and column.
     *
     * @param row     the row of the cell to change.
     * @param col     the column of the cell to change.
     * @param content the content to put in the specified cell.
     */
    @Override
    public void setContent(int row, int col, Content content) {
        _cells[row - 1][col - 1].setContent(content);
    }

    /**
     * Gets the content of a cell at the specified row and column.
     *
     * @param row the row of the cell to retrieve.
     * @param col the column of the cell to retrieve.
     * @return the content of the cell at the specified row and column.
     */
    @Override
    public Content getContent(int row, int col) {
        return _cells[row - 1][col - 1].getContent();
    }

    /**
     * Gets the entire matrix of cells.
     *
     * @return the matrix of cells representing the spreadsheet.
     */
    @Override
    public Cell[][] getCells() {
        return _cells;
    }
}