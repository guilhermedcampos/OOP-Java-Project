package xxl.core;

import xxl.core.exception.EvaluationException;
import xxl.core.exception.OutOfBoundsException;

/**
 * Represents the addition operation between two content objects.
 */
public class Add extends BinaryFunction {

    /**
     * Constructs an instance of the Add class with two content arguments.
     *
     * @param arg1 the first content argument.
     * @param arg2 the second content argument.
     * @throws EvaluationException if there is an error during evaluation.
     */
    public Add(Content arg1, Content arg2) throws EvaluationException {
        super("ADD", arg1, arg2);
    }

    /**
     * Computes the result of the addition operation and returns it as a LiteralInteger.
     *
     * @return the result of the addition as a LiteralInteger.
     * @throws EvaluationException if there is an error during evaluation.
     * @throws OutOfBoundsException if the operation exceeds valid bounds.
     */
    @Override
    public Literal compute() throws EvaluationException, OutOfBoundsException {
        int res = _arg1.value().asInt() + _arg2.value().asInt();
        return new LiteralInteger(res);
    }
}