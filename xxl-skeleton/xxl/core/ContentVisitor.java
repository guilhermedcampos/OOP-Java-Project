package xxl.core;

public interface ContentVisitor {
    void visit(LiteralString content);

    void visit(LiteralInteger content);

    void visit(LiteralException content);

    void visit(Null content);

    void visit(Reference content);

    void visit(Function content);

    void visit(Cell cell);
}