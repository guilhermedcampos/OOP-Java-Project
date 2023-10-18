package xxl.core;

public class CutBuffer { 
    private Cell[] _cells;

    public Cell[] getCells() {
        return _cells;
    }

    public void setCells(Cell[] cells) {
        _cells = cells;
    }

    public Content getContent(int index) {
        return _cells[index].getContent();

    }

}