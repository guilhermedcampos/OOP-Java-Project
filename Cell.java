public class Cell {
    private int _row;
    private int _column;
    private String _content;
    private String _typeOfContent;

    public Cell( int row, int col) {
        this._row = row;
        this._column = col;
        this._content = null;
        this._typeOfContent = null;
    }

    public String getContent() {
        // O método getContent retorna o conteúdo atual da célula.
        return this._content;
    }

    public String evaluate
}