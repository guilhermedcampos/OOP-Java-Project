package xxl.core;

import xxl.core.exception.EvaluationException;

/*
* Represents an exceptional literal value in the spreadsheet.
*/
public class LiteralException extends Literal {

    @Override
    public int asInt() throws EvaluationException {
        throw new EvaluationException("Cannot convert Exception Literal to integer.");
    }

    @Override
    public String toString() {
        return "#VALUE";
    }

    @Override
    public String asString() throws EvaluationException {
        throw new EvaluationException("Cannot convert Exception Literal to string.");
    }

    @Override
    public void accept(ContentVisitor visitor) {
        visitor.visit(this);
    }
}
