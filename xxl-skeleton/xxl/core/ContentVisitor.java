package xxl.core;

/**
 * Represents a visitor for content types in a spreadsheet cell.
 */
public interface ContentVisitor {
    void visit(LiteralString content);

    void visit(LiteralInteger content);

    void visit(LiteralException content);

    void visit(Null content);

    void visit(Reference content);

    void visit(Function content);

    void visit(Cell cell);
}