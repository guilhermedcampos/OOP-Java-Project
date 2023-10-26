package xxl.core;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import xxl.core.exception.EvaluationException;

/**
 * Represents a cell in a spreadsheet.
 */
public class Cell implements Serializable {

    /**
     * The row position of the cell within the spreadsheet.
     */
    private int _row;

    /**
     * The column position of the cell within the spreadsheet.
     */
    private int _col;

    /**
     * The content stored in the cell.
     */
    private Content _content;

    private List<Observer> _observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        _observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        _observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : _observers) {
            observer.update();
        }
    }

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
     * @throws EvaluationException  if there is an error during evaluation.
     */
    protected Literal value() throws EvaluationException {
        return _content.value();
    }

    /**
     * Gets the content stored in the cell.
     *
     * @return the content stored in the cell.
     */
    public Content getContent() {
        return _content;
    }

    /**
     * Sets the content of the cell.
     *
     * @param content the content to set in the cell.
     */
    protected void setContent(Content content) {
        _content = content;
        notifyObservers();
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
