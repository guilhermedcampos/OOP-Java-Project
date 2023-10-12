package xxl.core;

import xxl.core.exception.EvaluationException;
import xxl.core.exception.OutOfBoundsException;

/**
 * Represents a multiplication operation as a binary function.
 */
public class Mul extends BinaryFunction {

    /**
     * Initializes a new multiplication operation with the specified arguments.
     *
     * @param arg1 the first argument.
     * @param arg2 the second argument.
     * @throws EvaluationException  if there's an error in evaluating the
     *                              multiplication.
     * @throws OutOfBoundsException if there's an error due to out-of-bounds access.
     */
    public Mul(Content arg1, Content arg2) throws EvaluationException, OutOfBoundsException {
        super("MUL", arg1, arg2);
    }

    /**
     * Computes the result of the multiplication operation.
     *
     * @return the result as a `LiteralInteger`.
     * @throws EvaluationException  if there's an error in evaluating the
     *                              multiplication.
     * @throws OutOfBoundsException if there's an error due to out-of-bounds access.
     */
    @Override
    public Literal compute() throws EvaluationException, OutOfBoundsException {
        return new LiteralInteger(_arg1.value().asInt() * _arg2.value().asInt());

    }
}
