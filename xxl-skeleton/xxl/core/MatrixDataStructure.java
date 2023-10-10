package xxl.core;

public class MatrixDataStructure implements AbstractDataStructure {
    private Cell[][] _cells;

    public MatrixDataStructure() {
        
    }

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
    public Cell getCell(int row, int col){
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
}
