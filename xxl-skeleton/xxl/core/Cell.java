package xxl.core;

import xxl.core.exception.EvaluationException;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a cell in a spreadsheet.
 */
public class Cell implements Serializable {

    /** The row index of the cell. */
    private int _row;

    /** The column index of the cell. */
    private int _col;

    /** The content of the cell. */
    private Content _content;

    /** List of observers registered for this cell. */
    private List<Observer> _observers = new ArrayList<>();

    /**
     * Constructs a Cell object with the specified row and column.
     *
     * @param row the row of the cell.
     * @param col the column of the cell.
     */
    public Cell(int row, int col) {
        _row = row;
        _col = col;
        _content = new Null();
        _content.setConnectedCell(this);
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
     * Gets the content of the cell.
     *
     * @return the content of the cell.
     */
    public Content getContent() {
        return _content;
    }

    /**
     * Sets the content of the cell, stops observing the previous content,
     * and notifies observers of the change.
     *
     * @param content the new content for the cell.
     */
    protected void setContent(Content content) {
        _content.stopObserving();
        _content = content;
        _content.setConnectedCell(this);
        notifyObservers();
    }

    /**
     * Adds an observer to the list of observers for this cell.
     *
     * @param observer the observer to be added.
     */
    public void addObserver(Observer observer) {
        _observers.add(observer);
    }

    /**
     * Removes an observer from the list of observers for this cell.
     *
     * @param observer the observer to be removed.
     */
    public void removeObserver(Observer observer) {
        _observers.remove(observer);
    }

    /**
     * Notifies all registered observers that the content of this cell has changed.
     */
    public void notifyObservers() {
        for (Observer observer : _observers) {
            observer.update();
        }
    }

    /**
     * Gets the value of the cell's content.
     *
     * @return the value of the cell's content.
     * @throws EvaluationException if an error occurs during the evaluation of the content.
     */
    protected Literal value() throws EvaluationException {
        return _content.value();
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
