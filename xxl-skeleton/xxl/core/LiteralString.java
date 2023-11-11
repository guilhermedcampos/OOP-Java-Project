package xxl.core;

import xxl.core.exception.EvaluationException;

/*
 * Represents a literal string value in the spreadsheet.
 */
public class LiteralString extends Literal {

    /** The string value of the literal. */
    private String _value;

    /**
     * Constructs a LiteralString object with the specified string value.
     *
     * @param value the string value of the literal.
     */
    public LiteralString(String value) {
        _value = value;
    }

    /**
     * Throws an EvaluationException indicating that a String Literal cannot be converted to an integer.
     *
     * @return does not return as an exception is thrown.
     * @throws EvaluationException always thrown with the message "Cannot convert String Literal to int."
     */
    @Override
    public int asInt() throws EvaluationException {
        throw new EvaluationException("Cannot convert String Literal to int.");
    }

    /**
     * Returns the string value of the literal.
     *
     * @return the string value.
     */
    @Override
    public String asString() {
        return _value;
    }

    /**
     * Returns the string representation of the string value.
     *
     * @return the string representation of the string value.
     */
    @Override
    public String toString() {
        return "'" + _value;
    }

    /**
     * Accepts a visitor for visiting the String Literal.
     *
     * @param visitor the ContentVisitor to be accepted.
     */
    @Override
    public void accept(ContentVisitor visitor) {
        visitor.visit(this);
    }
}