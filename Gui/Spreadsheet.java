import java.util.ArrayList;
import java.util.List; // Add these import statements

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

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this._cells[row][col] = new Cell(row + 1, col + 1);
            }
        }
    }
    public Cell[][] getCells() {
        return this._cells;
    }

    public Cell getCell(int row, int col) {
        return _cells[row-1][col-1];
    }

    public void viewRange(Range range) {
        Cell startCell = range.getStart();
        Cell endCell = range.getEnd();

        for (int row = startCell.getRow(); row <= endCell.getRow(); row++) {
            for (int col = startCell.getColumn(); col <= endCell.getColumn(); col++) {
                if (row >= 1 && row <= _rows && col >= 1 && col <= _cols) {
                    Cell cell = _cells[row - 1][col - 1];
                    String cellContent = cell.getContent();

                    String formattedCell = cell.getRow() + ";" + cell.getColumn() + "|";

                    if (cellContent.isEmpty()) {
                        System.out.println(formattedCell);
                    } else {
                        System.out.println(formattedCell + cellContent);
                    }
                }
            }
        }
    }

    public void insertContent(Range range, String content) {
        Cell startCell = range.getStart();
        Cell endCell = range.getEnd();

        for (int row = startCell.getRow(); row <= endCell.getRow(); row++) {
            for (int col = startCell.getColumn(); col <= endCell.getColumn(); col++) {
                if (isValidCell(row, col)) {
                    Cell cell = _cells[row - 1][col - 1];
                    cell.setContent(content); // Update the content of the cell in the _activeSpreadsheet
                }
            }
        }
    }

    public String concat(Range range) {
        Cell startCell = range.getStart();
        Cell endCell = range.getEnd();
        StringBuilder concatenated = new StringBuilder();

        for (int row = startCell.getRow(); row <= endCell.getRow(); row++) {
            for (int col = startCell.getColumn(); col <= endCell.getColumn(); col++) {
                if (isValidCell(row, col)) {
                    Cell cell = _cells[row - 1][col - 1];
                    String cellContent = cell.getContent();
                    
                    // Check if the cell's content is a string and not empty
                    if (cellContent != null && !cellContent.isEmpty()) {
                        concatenated.append(cellContent);
                    }
                }
            }
        }

        return concatenated.toString();
    }


    public void copy(Range range) {
        Cell startCell = range.getStart();
        Cell endCell = range.getEnd();
        String copiedContent = concat(range); // Use the concat function to get the concatenated content

        Application.setCutBuffer(copiedContent);
    }

    public void cut(Range range) {
        Cell startCell = range.getStart();
        Cell endCell = range.getEnd();
        String cutContent = concat(range); // Use the concat method to get the concatenated content

        // Clear the content of the cells within the range
        for (int row = startCell.getRow(); row <= endCell.getRow(); row++) {
            for (int col = startCell.getColumn(); col <= endCell.getColumn(); col++) {
                if (isValidCell(row, col)) {
                    Cell cell = _cells[row - 1][col - 1];
                    cell.setContent(""); // Clear the cell content
                }
            }
        }

        Application.setCutBuffer(cutContent);
    }

    public Cell[] searchContent(Range range, String content) {
        // Searches for given content
        List<Cell> matchingCellsList = new ArrayList<>();
        Cell startCell = range.getStart();
        Cell endCell = range.getEnd();

        for (int row = startCell.getRow(); row <= endCell.getRow(); row++) {
            for (int col = startCell.getColumn(); col <= endCell.getColumn(); col++) {
                if (isValidCell(row, col)) {
                    Cell cell = _cells[row - 1][col - 1];
                    if (cell.getContent().equals(content)) {
                        matchingCellsList.add(cell);
                    }
                }
            }
        }

        // Convert the List<Cell> to a Cell[] array
        Cell[] matchingCells = matchingCellsList.toArray(new Cell[matchingCellsList.size()]);
        
        return matchingCells;
    }

    private boolean isValidCell(int row, int col) {
        return row >= 1 && row <= _rows && col >= 1 && col <= _cols;
    }


}

    

