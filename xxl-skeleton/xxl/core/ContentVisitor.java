package xxl.core;

/**
 * Represents a visitor for content types in a spreadsheet cell.
 */
public interface ContentVisitor {

    /**
     * Visits a LiteralString content type.
     *
     * @param content the LiteralString content to be visited.
     */
    void visit(LiteralString content);

    /**
     * Visits a LiteralInteger content type.
     *
     * @param content the LiteralInteger content to be visited.
     */
    void visit(LiteralInteger content);

    /**
     * Visits a LiteralException content type.
     *
     * @param content the LiteralException content to be visited.
     */
    void visit(LiteralException content);

    /**
     * Visits a Null content type.
     *
     * @param content the Null content to be visited.
     */
    void visit(Null content);

    /**
     * Visits a Reference content type.
     *
     * @param content the Reference content to be visited.
     */
    void visit(Reference content);

    /**
     * Visits a Function content type.
     *
     * @param content the Function content to be visited.
     */
    void visit(Function content);

    /**
     * Visits a Cell type.
     *
     * @param cell the Cell to be visited.
     */
    void visit(Cell cell);
}
