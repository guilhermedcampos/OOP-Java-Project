package xxl.core;

public interface AbstractDataStructure {
    void initialize(int rows, int cols);
    void setContent(int row, int col, Content content);
    Content getContent(int row, int col);
}
