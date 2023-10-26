package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Represents a literal integer value in the spreadsheet.
 */
public class LiteralInteger extends Literal {
    
    /**
     * The integer value stored in the literal.
     */
    private int _value;

    /**
     * Constructs a new LiteralInteger with the specified integer value.
     *
     * @param value the integer value of the literal.
     */
    public LiteralInteger(int value) {
        _value = value;
    }

    /**
     * Gets the integer value of the literal.
     *
     * @return the integer value of the literal.
     */
    @Override
    public int asInt() {
        return _value;
    }

    /**
     * Returns a string representation of the integer value.
     *
     * @return a string representation of the integer value.
     */
    @Override
    public String toString() {
        return Integer.toString(_value);
    }

    /**
     * Throws an EvaluationException because a LiteralInteger cannot be converted to a string.
     *
     * @throws EvaluationException always throws an EvaluationException with the message
     *                              "Cannot convert Integer Literal to string."
     */
    @Override
    public String asString() throws EvaluationException {
        throw new EvaluationException("Cannot convert Integer Literal to string.");
    }

    @Override
    public void accept(ContentVisitor visitor, Cell cell){
        visitor.visit(this, cell);
    }
}
