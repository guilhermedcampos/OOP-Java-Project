package xxl.core;

import java.io.Serializable;

import xxl.core.exception.EvaluationException;
import xxl.core.exception.OutOfBoundsException;

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
     * @throws OutOfBoundsException if there is an error retrieving the value.
     */
    protected abstract Literal value() throws EvaluationException;

    /**
     * Gets the content as a string.
     *
     * @return the content as a string.
     * @throws EvaluationException  if there is an error evaluating the content.
     * @throws OutOfBoundsException if there is an error retrieving the value.
     */
    public String asString() throws EvaluationException {
        return value().asString();
    }

    /**
     * Gets the content as an integer.
     *
     * @return the content as an integer.
     * @throws EvaluationException  if there is an error evaluating the content.
     * @throws OutOfBoundsException if there is an error retrieving the value.
     */
    public int asInt() throws EvaluationException {
        return value().asInt();
    }
}
