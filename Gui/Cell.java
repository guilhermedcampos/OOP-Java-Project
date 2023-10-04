public class Cell {
    private int _row;
    private int _column;
    private String _content;

    public Cell( int row, int col) {
        this._row = row;
        this._column = col;
        this._content = "";
    }
    
    public int getRow() {
        return _row;
    }

    public int getColumn() {
        return _column;
    }

    public String getContent() {
        // O método getContent retorna o conteúdo atual da célula.
        return this._content;
    }

    public void setContent(String content) {
        _content = content;
    }


}