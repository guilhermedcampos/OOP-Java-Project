package xxl.core;

import java.io.Serializable;

/**
 * Represents a matrix-based data structure for storing spreadsheet cells and
 * content.
 */
public class MatrixDataStructure implements AbstractDataStructure, Serializable {

    private Cell[][] _cells;

    @Override
    public void initialize(int rows, int cols) {
        _cells = new Cell[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                _cells[row][col] = new Cell(row + 1, col + 1);
            }
        }
    }

    @Override
    public Cell getCell(int row, int col) {
        return _cells[row - 1][col - 1];
    }

    @Override
    public void setContent(int row, int col, Content content) {
        _cells[row - 1][col - 1].setContent(content);
    }

    @Override
    public Content getContent(int row, int col) {
        return _cells[row - 1][col - 1].getContent();
    }

    @Override
    public Cell[][] getCells() {
        return _cells;
    }
}