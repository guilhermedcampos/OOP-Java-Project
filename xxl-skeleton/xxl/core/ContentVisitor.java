package xxl.core;

public interface ContentVisitor {
    void visit(LiteralString content, Cell cell);
    void visit(LiteralInteger content, Cell cell);
    void visit(LiteralException content, Cell cell);
    void visit(Null content, Cell cell);
    void visit(Reference content, Cell cell);
    void visit(Function content, Cell cell);
    void visit(Cell cell);
}