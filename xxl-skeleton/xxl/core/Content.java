package xxl.core;

import java.io.Serializable;

import xxl.core.exception.EvaluationException;

/**
 * Represents the content of a cell in a spreadsheet.
 */
public abstract class Content implements Serializable, Observer {
    private Cell _connectedCell;
    private boolean _isObserving = false;

    public boolean isObserving() {
        return _isObserving;
    }

    public void setIsObserving(boolean isObserving) {
        _isObserving = isObserving;
    }

    public Cell getConnectedCell() {
        return _connectedCell;
    }

    public void setConnectedCell(Cell cell) {
        _connectedCell = cell;
    }

    public abstract void startObserving();

    public abstract void stopObserving();

    public abstract void compute();

    public void update() {
        this.compute();
        if (_connectedCell != null) {
            _connectedCell.notifyObservers();
        }
    }

    public abstract String toString();

    protected abstract Literal value();

    public abstract void accept(ContentVisitor visitor);

    public String asString() throws EvaluationException {
        return value().asString();
    }

    public int asInt() throws EvaluationException {
        return value().asInt();
    }
}
