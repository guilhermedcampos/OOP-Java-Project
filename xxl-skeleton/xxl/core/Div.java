package xxl.core;

import xxl.core.exception.EvaluationException;
import xxl.core.exception.OutOfBoundsException;

/**
 * Represents a division operation between two content elements.
 */
public class Div extends BinaryFunction {

    /**
     * Constructs a new division operation with the given arguments.
     *
     * @param arg1 the first argument.
     * @param arg2 the second argument.
     * @throws EvaluationException  if there is an error during evaluation.
     * @throws OutOfBoundsException if there is an error accessing data.
     */
    public Div(Content arg1, Content arg2) throws EvaluationException, OutOfBoundsException {
        super("DIV", arg1, arg2);
    }

    /**
     * Computes the result of the division operation.
     *
     * @return the result of the division as a LiteralInteger.
     * @throws EvaluationException  if there is an error during evaluation.
     * @throws OutOfBoundsException if there is an error accessing data.
     */
    @Override
    public Literal compute() throws EvaluationException, OutOfBoundsException {
        if (_arg2.value().asInt() == 0) {
            return new LiteralString("#VALUE");
        }
        return new LiteralInteger(_arg1.value().asInt() / _arg2.value().asInt());
    }
}
