package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Represents a generic function in the spreadsheet.
 */
public abstract class Function extends Content {

    /**
     * The name of the function.
     */
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
     */
    protected abstract Literal compute() throws EvaluationException;

    /**
     * Gets the result of the function as a string.
     *
     * @return the result of the function as a string.
     * @throws EvaluationException  if there is an error during evaluation.
     */
    public String asString() throws EvaluationException {
        return compute().asString();
    }

    /**
     * Gets the result of the function as an integer.
     *
     * @return the result of the function as an integer.
     * @throws EvaluationException  if there is an error during evaluation.
     */
    public int asInt() throws EvaluationException {
        return compute().asInt();
    }

    /**
     * Gets the result of the function as a Literal.
     *
     * @return the result of the function as a Literal.
     * @throws EvaluationException  if there is an error during evaluation.
     */
    public Literal value() throws EvaluationException{
        return compute();
    }


        /**
     * Cleans the input string after the first equals sign ('=') by removing
     * everything between the first '(' and '=' (including '=') and removing
     * everything between ',' and the last '='. This method is used to format the
     * string representation of the binary function.
     *
     * @param input the input string to be cleaned.
     * @return a cleaned string representation.
     */
    protected static String cleanStringAfterFirstEquals(String input) {
        int firstEqualsIndex = input.indexOf('=');
        if (firstEqualsIndex >= 0) {
            String beforeFirstEquals = input.substring(0, firstEqualsIndex + 1);
            String afterFirstEquals = input.substring(firstEqualsIndex + 1);

            // Remove everything between the first '(' and '=' (including '=')
            afterFirstEquals = afterFirstEquals.replaceAll("\\([^=]+=", "(");

            // Remove everything between ',' and the last '='
            afterFirstEquals = afterFirstEquals.replaceAll(",[^=]+=", ",");

            return beforeFirstEquals + afterFirstEquals;
        }
        return input;
    }
}
