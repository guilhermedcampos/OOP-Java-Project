package xxl.core;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import xxl.core.exception.EvaluationException;

/**
 * Represents a cell in a spreadsheet.
 */
public class Cell implements Serializable {

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

    public void notifyObservers() {
        for (Observer observer : _observers) {
            observer.update();
        }

    }

    protected void setContent(Content content) {
        if (_content == null) {
            System.out.println(this.toString());
        }
        if (_content != null && _content.isObserving()) {
            _content.stopObserving();
        }
        _content = content;
        _content.setConnectedCell(this);
        notifyObservers();
    }

    public Cell(int row, int col) {
        _row = row;
        _col = col;
        _content = new Null();
        _content.setConnectedCell(this);
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
