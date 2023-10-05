package xxl.core;

public class Cell {
    private int _row;
    private int _col;
    private Content _content;

    public Cell(int row, int col){
        _row = row;
        _col = col;
    }

    public int getCol(){
        return _col;
    }

    public int getRow(){
        return _row;
    }

    public Content getContent(){
        return _content;
    }

    public void setRow(int row) {
            _row = row;
        }

    public void setCol(int col) {
        _col = col;
    }

    public void setContent(Content content){
        _content = content;
    }

    public String toString() {
        return _row + ":" + _col;
    }
} 