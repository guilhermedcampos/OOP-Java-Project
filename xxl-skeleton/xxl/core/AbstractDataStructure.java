package xxl.core;

public abstract class AbstractDataStructure {
    public abstract void initialize(int rows, int cols);
    public abstract void setValue(int row, int col, Content content);
    public abstract Content getValue(int row, int col);
}
