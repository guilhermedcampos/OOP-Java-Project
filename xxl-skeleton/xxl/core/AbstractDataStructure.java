package xxl.core;

public abstract class AbstractDataStructure {
    public abstract void initialize(int rows, int cols);
    public abstract void setContent(int row, int col, Content content);
    public abstract Content getContent(int row, int col);
}
