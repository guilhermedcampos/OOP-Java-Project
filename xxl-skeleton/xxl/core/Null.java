package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Represents a null value in the system. This literal is used to represent
 * empty cells in a spreadsheet.
 */
public class Null extends Literal {

    @Override
    public int asInt() throws EvaluationException {
        throw new EvaluationException("Cannot convert Null Literal to integer.");
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public String asString() throws EvaluationException {
        throw new EvaluationException("Cannot convert Null Literal to string.");
    }

    @Override
    public void accept(ContentVisitor visitor) {
        visitor.visit(this);
    }
}