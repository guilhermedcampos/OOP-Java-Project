package xxl.core;

/**
 * An interface representing the abstract data structure for a spreadsheet.
 */
public interface AbstractDataStructure {
    /**
     * Initializes the data structure with the specified number of rows and columns.
     *
     * @param rows the number of rows for the spreadsheet.
     * @param cols the number of columns for the spreadsheet.
     */
    void initialize(int rows, int cols);

    /**
     * Sets the content of a cell at the given row and column.
     *
     * @param row     the row of the cell to change.
     * @param col     the column of the cell to change.
     * @param content the content to put in the specified cell.
     */
    void setContent(int row, int col, Content content);

    /**
     * Gets a cell at the given row and column.
     *
     * @param row the row of the cell to retrieve.
     * @param col the column of the cell to retrieve.
     * @return the cell object at the specified row and column.
     */
    Cell getCell(int row, int col);

    /**
     * Gets the content of a cell at the given row and column.
     *
     * @param row the row of the cell to retrieve.
     * @param col the column of the cell to retrieve.
     * @return the content of the cell at the specified row and column.
     */
    Content getContent(int row, int col);

    /**
     * Gets a bidimensdional array of all the cells in the spreadsheet.
     *
     * @return the bidimensional Cell array.
     */
    Cell[][] getCells();
}
