package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Represents a literal string value in the spreadsheet.
 */
public class LiteralString extends Literal {

    /**
     * The string value stored in the literal.
     */
    private String _value;
    
    /**
     * Constructs a new LiteralString with the specified string value.
     *
     * @param value the string value of the literal.
     */
    public LiteralString(String value) {
        _value = value;
    }

    /**
     * Gets the string value of the literal.
     *
     * @return the string value of the literal.
     */
    @Override
    public String asString() {
        return _value;
    }

    /**
     * Returns a string representation of the string value.
     *
     * @return a string representation of the string value.
     */
    @Override
    public String toString() {
        return "'" + _value;
    }

    /**
     * Throws an EvaluationException because a LiteralString cannot be converted to
     * an integer.
     *
     * @throws EvaluationException always throws an EvaluationException with the
     *                             message
     *                             "Cannot convert String Literal to int."
     */
    @Override
    public int asInt() throws EvaluationException {
        throw new EvaluationException("Cannot convert String Literal to int.");
    }

    @Override
    public void accept(ContentVisitor visitor){
        visitor.visit(this);
    }
}
