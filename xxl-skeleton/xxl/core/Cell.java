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

    public Literal value() {
        if (_content != null) {
            return _content.value();
        } else {
            return null; 
        }
    }

    protected Content getContent(){
        return this._content;
    }

    protected void setContent(Content content){
        _content = content;
    }

    public String toString() {
        return _row + ";" + _col;
    }
} 