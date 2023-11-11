package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Represents a null value. This literal is used to represent empty cells in a spreadsheet.
 */
public class Null extends Literal {

    /**
     * Throws an EvaluationException indicating that a Null Literal cannot be converted to an integer.
     *
     * @return does not return as an exception is thrown.
     * @throws EvaluationException always thrown with the message "Cannot convert Null Literal to integer."
     */
    @Override
    public int asInt() throws EvaluationException {
        throw new EvaluationException("Cannot convert Null Literal to integer.");
    }

    /**
     * Throws an EvaluationException indicating that a Null Literal cannot be converted to a string.
     *
     * @return does not return as an exception is thrown.
     * @throws EvaluationException always thrown with the message "Cannot convert Null Literal to string."
     */
    @Override
    public String asString() throws EvaluationException {
        throw new EvaluationException("Cannot convert Null Literal to string.");
    }

    /**
     * Returns an empty string representation for a Null Literal.
     *
     * @return an empty string.
     */
    @Override
    public String toString() {
        return "";
    }

    /**
     * Accepts a ContentVisitor for visiting the Null Literal.
     *
     * @param visitor the ContentVisitor to be accepted.
     */
    @Override
    public void accept(ContentVisitor visitor) {
        visitor.visit(this);
    }
}