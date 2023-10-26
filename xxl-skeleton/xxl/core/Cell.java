package xxl.core;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import xxl.core.exception.EvaluationException;

/**
 * Represents a cell in a spreadsheet.
 */
public class Cell implements Serializable, Observer {

    private int _row;
    private int _col;
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

    protected void setContent(Content content) {
        _content = content;
        _content.setConnectedCell(this);
        notifyObservers();
    }

    public void update() {
        _content.update();
    }

    public Cell(int row, int col) {
        _row = row;
        _col = col;
        _content = new Null();
    }

    public int getCol() {
        return _col;
    }

    public int getRow() {
        return _row;
    }

    protected Literal value() throws EvaluationException {
        return _content.value();
    }

    public Content getContent() {
        return _content;
    }

    public String toString() {
        return _row + ";" + _col;
    }
}
