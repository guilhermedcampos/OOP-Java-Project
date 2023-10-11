package xxl.core;

import xxl.core.exception.OutOfBoundsException;

public class Cell {

    private int _row;
    private int _col;
    private Content _content;

    /**
     * Constructs a cell with the specified row and column indices.
     *
     * @param row the row index of the cell.
     * @param col the column index of the cell.
     */
    public Cell(int row, int col) {
        _row = row;
        _col = col;
        _content = new Null();
    }

    /**
     * Gets the column index of the cell.
     *
     * @return the column index of the cell.
     */
    public int getCol() {
        return _col;
    }

    /**
     * Gets the row index of the cell.
     *
     * @return the row index of the cell.
     */
    public int getRow() {
        return _row;
    }

    /**
     * Gets the value of the cell as a Literal.
     *
     * @return the value of the cell as a Literal.
     * @throws OutOfBoundsException if there is an error retrieving the value.
     */
    public Literal value() throws OutOfBoundsException {
        return _content.value();
    }

    /**
     * Gets the content stored in the cell.
     *
     * @return the content stored in the cell.
     */
    protected Content getContent() {
        return _content;
    }

    /**
     * Sets the content of the cell.
     *
     * @param content the content to set in the cell.
     */
    protected void setContent(Content content) {
        _content = content;
    }

    /**
     * Returns a string representation of the cell in the format "row;col".
     *
     * @return a string representation of the cell.
     */
    public String toString() {
        return _row + ";" + _col;
    }
}
