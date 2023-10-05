package xxl.core;
public class Reference extends Content {
    private String _name;
    private int _row;
    private int _col;



    public Reference(String name) {
        this._name = name;
    }

    public Reference(int row, int col) {
        this._row = row;
        this._col = col;
    }

    public int getRow() {
        return _row;
    }

    public int getCol() {
        return _col;
    }

    public toString() {
        return "="+_row+"="+_col;
    }

    @Override
    public void evaluate() {
        // Implemente a lógica de avaliação para Referencia aqui
    }

    
}
