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
        // Implement logic to evaluate the reference here
        // You should retrieve the content of the referenced cell and return it
        // This is a simplified example; you may need to access the Spreadsheet
        // and handle circular references in a real implementation.
        return _spreadsheet.getContentAt(row, col).evaluate();
    }
}


    
}
