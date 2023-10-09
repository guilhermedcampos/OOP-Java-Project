package xxl.core;

public class MatrixDataStructure extends AbstractDataStructure {
    private Cell[][] cells;
    private int numRows;
    private int numCols;

    @Override
    public void initialize(int rows, int cols) {
        numRows = rows;
        numCols = cols;
        cells = new Cell[rows][cols];
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col] = new Cell(row + 1, col + 1);
            }
        }
    }

    @Override
    public void setValue(int row, int col, Content content) {
        // Check if the provided row and column are valid
        if (isValidCell(row, col)) {
            cells[row - 1][col - 1].setContent(content);
        }
    }

    @Override
    public Content getValue(int row, int col) {
        // Check if the provided row and column are valid
        if (isValidCell(row, col)) {
            return cells[row - 1][col - 1].getContent();
        }
        return null; // Return null for empty or invalid cells
    }

    private boolean isValidCell(int row, int col) {
        return row >= 1 && row <= numRows && col >= 1 && col <= numCols;
    }
}
