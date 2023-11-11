package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Represents a generic function in the spreadsheet.
 */
public abstract class Function extends Content {

    /** The name of the function. */
    private String _name;

    /** The result of the function. */
    protected Literal _value;

    /**
     * Constructs a Function object with the specified name.
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
     * Accepts a visitor for visiting the function.
     *
     * @param visitor the ContentVisitor to be accepted.
     */
    public void accept(ContentVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Abstract method to be implemented by subclasses for computing the result of the function.
     */
    public abstract void compute();

    /**
     * Abstract method to be implemented by subclasses for starting observation of changes.
     */
    public abstract void startObserving();

    /**
     * Abstract method to be implemented by subclasses for stopping observation of changes.
     */
    public abstract void stopObserving();

    /**
     * Converts the value of the function to a string.
     *
     * @return the string representation of the function's value.
     * @throws EvaluationException if an error occurs during the evaluation of the function.
     */
    public String asString() throws EvaluationException {
        return _value.asString();
    }

    /**
     * Converts the value of the function to an integer.
     *
     * @return the integer representation of the function's value.
     * @throws EvaluationException if an error occurs during the evaluation of the function.
     */
    public int asInt() throws EvaluationException {
        return _value.asInt();
    }

    /**
     * Gets the Literal value of the function.
     *
     * @return the Literal value of the function.
     */
    public Literal value() {
        return _value;
    }

    /**
     * Cleans the input string after the first equals sign.
     * Removes variable names and equal signs after the first equals sign in the function's string representation.
     *
     * @param input the input string.
     * @return the cleaned string.
     */
    protected String cleanStringAfterFirstEquals(String input) {
        int firstEqualsIndex = input.indexOf('=');
        if (firstEqualsIndex >= 0) {
            String beforeFirstEquals = input.substring(0, firstEqualsIndex + 1);
            String afterFirstEquals = input.substring(firstEqualsIndex + 1);

            afterFirstEquals = afterFirstEquals.replaceAll("\\([^=]+=", "(");
            afterFirstEquals = afterFirstEquals.replaceAll(",[^=]+=", ",");

            return beforeFirstEquals + afterFirstEquals;
        }
        return input;
    }
}