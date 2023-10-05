package xxl.core;

public class Reference extends Content {
    private int row;
    private int col;

    public Reference(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public Content evaluate() {
        return _spreadsheet.getContentAt(row, col).evaluate();
    }
}


    

