package xxl.core;

import xxl.core.exception.EvaluationException;

/*
* Represents an exceptional literal value in the spreadsheet.
*/
public class LiteralException extends Literal {

    /**
     * Throws an EvaluationException indicating that an Exception Literal cannot be converted to an integer.
     *
     * @return does not return as an exception is thrown.
     * @throws EvaluationException always thrown with the message "Cannot convert Exception Literal to integer."
     */
    @Override
    public int asInt() throws EvaluationException {
        throw new EvaluationException("Cannot convert Exception Literal to integer.");
    }

    /**
     * Returns the string representation of an Exception Literal, which is "#VALUE".
     *
     * @return the string "#VALUE".
     */
    @Override
    public String toString() {
        return "#VALUE";
    }

    /**
     * Throws an EvaluationException indicating that an Exception Literal cannot be converted to a string.
     *
     * @return does not return as an exception is thrown.
     * @throws EvaluationException always thrown with the message "Cannot convert Exception Literal to string."
     */
    @Override
    public String asString() throws EvaluationException {
        throw new EvaluationException("Cannot convert Exception Literal to string.");
    }

    /**
     * Accepts a visitor for visiting the Exception Literal.
     *
     * @param visitor the ContentVisitor to be accepted.
     */
    @Override
    public void accept(ContentVisitor visitor) {
        visitor.visit(this);
    }
}
