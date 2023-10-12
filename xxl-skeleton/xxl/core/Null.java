package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * A special literal representing a null value in the system.
 * This literal is used to represent missing or empty cells in a spreadsheet.
 */
public class Null extends Literal {

    /**
     * Throws an exception when attempting to convert a Null literal to an integer.
     *
     * @return This method always throws an EvaluationException.
     * @throws EvaluationException Always thrown when trying to convert a Null
     *                             literal to an integer.
     */
    @Override
    public int asInt() throws EvaluationException {
        throw new EvaluationException("Cannot convert Null Literal to integer.");
    }

    /**
     * Returns an empty string.
     *
     * @return An empty string.
     */
    @Override
    public String toString() {
        return "#VALUE";
    }

    /**
     * Throws an exception when attempting to convert a Null literal to a string.
     *
     * @return This method always throws an EvaluationException.
     * @throws EvaluationException Always thrown when trying to convert a Null
     *                             literal to a string.
     */
    @Override
    public String asString() throws EvaluationException {
        throw new EvaluationException("Cannot convert Null Literal to string.");
    }
}
