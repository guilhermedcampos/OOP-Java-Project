package xxl.core;

import xxl.core.exception.EvaluationException;
import xxl.core.exception.OutOfBoundsException;

/**
 * Represents an abstract binary function that operates on two content objects.
 */
public abstract class BinaryFunction extends Function {

    protected Content _arg1;
    protected Content _arg2;

    /**
     * Constructs a binary function with a specified name and two content arguments.
     *
     * @param name the name of the binary function.
     * @param arg1 the first content argument.
     * @param arg2 the second content argument.
     */
    public BinaryFunction(String name, Content arg1, Content arg2) {
        super(name);
        _arg1 = arg1;
        _arg2 = arg2;
    }

    /**
     * Computes the result of the binary function.
     *
     * @return the result of the binary function as a Literal.
     * @throws EvaluationException  if there is an error during evaluation.
     * @throws OutOfBoundsException if the operation exceeds valid bounds.
     */
    public abstract Literal compute() throws EvaluationException;

    /**
     * Returns a string representation of the binary function.
     *
     * @return a string representation of the binary function.
     */
    @Override
    public String toString() {
        try {
            String result = value() + "=" + getName() + "(" + _arg1.toString() + "," + _arg2.toString() + ")";
            return cleanStringAfterFirstEquals(result);
        } catch (EvaluationException e) {
            String result = "#VALUE" + "=" + getName() + "(" + _arg1.toString() + "," + _arg2.toString() + ")";
            return cleanStringAfterFirstEquals(result);
        }
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
    private String cleanStringAfterFirstEquals(String input) {
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