package xxl.core;

public interface ContentVisitor {
    void visit(Literal content, Cell cell);
    void visit(Reference content, Cell cell);
    void visit(Function content, Cell cell);
    void visit(Cell cell);
}