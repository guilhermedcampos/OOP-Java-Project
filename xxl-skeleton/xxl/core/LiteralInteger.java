package xxl.core;

import xxl.core.exception.EvaluationException;

/*
 * Represents a literal integer value in the spreadsheet.
 */
public class LiteralInteger extends Literal {
    private int _value;

    public LiteralInteger(int value) {
        _value = value;
    }

    @Override
    public int asInt() {
        return _value;
    }

    @Override
    public String toString() {
        return Integer.toString(_value);
    }

    @Override
    public String asString() throws EvaluationException {
        throw new EvaluationException("Cannot convert Integer Literal to string.");
    }

    @Override
    public void accept(ContentVisitor visitor) {
        visitor.visit(this);
    }
}
