package xxl.core;

import java.io.Serializable;
import xxl.core.exception.EvaluationException;

/**
 * Represents the content of a cell in a spreadsheet.
 */
public abstract class Content implements Serializable, Observer {

    /** The cell connected to this content. */
    private Cell _connectedCell;

    /** Flag indicating whether observing changes is enabled. */
    private boolean _isObserving = false;

    /**
     * Gets the connected cell of this content.
     *
     * @return the connected cell of this content.
     */
    public Cell getConnectedCell() {
        return _connectedCell;
    }

    /**
     * Sets the connected cell of this content.
     *
     * @param cell the cell to be connected to this content.
     */
    public void setConnectedCell(Cell cell) {
        _connectedCell = cell;
    }

    /**
     * Abstract method to be implemented by subclasses for starting observation of changes.
     */
    public abstract void startObserving();

    /**
     * Abstract method to be implemented by subclasses for stopping observation of changes.
     */
    public abstract void stopObserving();

    /**
     * Abstract method to be implemented by subclasses for computing the content.
     */
    public abstract void compute();

    /**
     * Updates the content and notifies connected cell observers.
     */
    public void update() {
        this.compute();
        if (_connectedCell != null) {
            _connectedCell.notifyObservers();
        }
    }

    /**
     * Sets whether observing changes is enabled for this content.
     * If enabled, adds or removes this content as an observer for the connected cell.
     *
     * @param isObserving true if observing changes is enabled, false otherwise.
     * @param content     the content to be added or removed as an observer.
     */
    public void setIsObserving(boolean isObserving, Content content) {
        _isObserving = isObserving;
        if (getConnectedCell() != null) {
            if (_isObserving) {
                getConnectedCell().addObserver(content);
            } else {
                getConnectedCell().removeObserver(content);
            }
        }
    }

    /**
     * Abstract method to be implemented by subclasses for getting the value of the content.
     *
     * @return the value of the content as a Literal.
     */
    protected abstract Literal value();

    /**
     * Abstract method to be implemented by subclasses for accepting a visitor.
     *
     * @param visitor the ContentVisitor to be accepted.
     */
    public abstract void accept(ContentVisitor visitor);

    /**
     * Converts the value of the content to a string.
     *
     * @return the string representation of the content's value.
     * @throws EvaluationException if an error occurs during the evaluation of the content.
     */
    public String asString() throws EvaluationException {
        return value().asString();
    }

    /**
     * Converts the value of the content to an integer.
     *
     * @return the integer representation of the content's value.
     * @throws EvaluationException if an error occurs during the evaluation of the content.
     */
    public int asInt() throws EvaluationException {
        return value().asInt();
    }

    /**
     * Abstract method to be implemented by subclasses for providing a string representation of the content.
     *
     * @return a string representation of the content.
     */
    public abstract String toString();
}
