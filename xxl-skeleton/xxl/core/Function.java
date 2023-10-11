package xxl.core;

import xxl.core.exception.EvaluationException;
import xxl.core.exception.OutOfBoundsException;

/**
 * Represents a generic function in the spreadsheet.
 */
public abstract class Function extends Content {
    private String _name;

    /**
     * Constructs a new function with the given name.
     *
     * @param name the name of the function.
     */
    public Function(String name) {
        _name = name;
    }

    /**
     * Gets the name of the function.
     *
     * @return the name of the function.
     */
    protected String getName() {
        return _name;
    }

    /**
     * Computes the result of the function.
     *
     * @return the result of the function as a Literal.
     * @throws EvaluationException  if there is an error during evaluation.
     * @throws OutOfBoundsException if there is an error accessing data.
     */
    public abstract Literal compute() throws EvaluationException, OutOfBoundsException;

    /**
     * Gets the result of the function as a string.
     *
     * @return the result of the function as a string.
     * @throws EvaluationException  if there is an error during evaluation.
     * @throws OutOfBoundsException if there is an error accessing data.
     */
    public String asString() throws EvaluationException, OutOfBoundsException {
        return compute().asString();
    }

    /**
     * Gets the result of the function as an integer.
     *
     * @return the result of the function as an integer.
     * @throws EvaluationException  if there is an error during evaluation.
     * @throws OutOfBoundsException if there is an error accessing data.
     */
    public int asInt() throws EvaluationException, OutOfBoundsException {
        return compute().asInt();
    }

    /**
     * Gets the result of the function as a Literal.
     *
     * @return the result of the function as a Literal.
     * @throws EvaluationException  if there is an error during evaluation.
     * @throws OutOfBoundsException if there is an error accessing data.
     */
    public Literal value() throws EvaluationException, OutOfBoundsException {
        return compute();
    }
}
