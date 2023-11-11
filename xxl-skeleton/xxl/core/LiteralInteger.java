package xxl.core;

import xxl.core.exception.EvaluationException;

/*
 * Represents a literal integer value in the spreadsheet.
 */
public class LiteralInteger extends Literal {

    /** The integer value of the literal. */
    private int _value;

    /**
     * Constructs a LiteralInteger object with the specified integer value.
     *
     * @param value the integer value of the literal.
     */
    public LiteralInteger(int value) {
        _value = value;
    }

    /**
     * Returns the integer value of the literal.
     *
     * @return the integer value.
     */
    @Override
    public int asInt() {
        return _value;
    }

    /**
     * Throws an EvaluationException indicating that an Integer Literal cannot be converted to a string.
     *
     * @return does not return as an exception is thrown.
     * @throws EvaluationException always thrown with the message "Cannot convert Integer Literal to string."
     */
    @Override
    public String asString() throws EvaluationException {
        throw new EvaluationException("Cannot convert Integer Literal to string.");
    }

    /**
     * Returns the string representation of the integer value.
     *
     * @return the string representation of the integer value.
     */
    @Override
    public String toString() {
        return Integer.toString(_value);
    }

    /**
     * Accepts a visitor for visiting the Integer Literal.
     *
     * @param visitor the ContentVisitor to be accepted.
     */
    @Override
    public void accept(ContentVisitor visitor) {
        visitor.visit(this);
    }
}
