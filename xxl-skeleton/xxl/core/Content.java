package xxl.core;

import java.io.Serializable;

import xxl.core.exception.EvaluationException;

/**
 * Represents the content of a cell in a spreadsheet.
 */
public abstract class Content implements Serializable {

    /**
     * Returns a string representation of the content.
     *
     * @return a string representation of the content.
     */
    public abstract String toString();

    /**
     * Gets the value of the content as a Literal.
     *
     * @return the value of the content as a Literal.
     * @throws EvaluationException  if there is an error during evaluation.
     */
    protected abstract Literal value() throws EvaluationException;

    public abstract void accept(ContentVisitor visitor, Cell cell);

    /**
     * Gets the content as a string.
     *
     * @return the content as a string.
     * @throws EvaluationException  if there is an error during evaluation.
     */
    public String asString() throws EvaluationException {
        return value().asString();
    }

    /**
     * Gets the content as an integer.
     *
     * @return the content as an integer.
     * @throws EvaluationException  if there is an error during evaluation.
     */
    public int asInt() throws EvaluationException {
        return value().asInt();
    }

    public boolean isReference(){
        return false;
    }
}
