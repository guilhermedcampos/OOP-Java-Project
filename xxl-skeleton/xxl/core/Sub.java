package xxl.core;

import xxl.core.exception.EvaluationException;
import xxl.core.exception.OutOfBoundsException;

/**
 * Class representing a subtraction operation between two contents.
 */
public class Sub extends BinaryFunction {
    /**
     * Constructs a subtraction operation with two content arguments.
     *
     * @param arg1 The first content argument.
     * @param arg2 The second content argument.
     * @throws EvaluationException if there's an error during evaluation.
     * @throws OutOfBoundsException if the operation goes out of bounds.
     */
    public Sub(Content arg1, Content arg2) throws EvaluationException, OutOfBoundsException {
        super("SUB", arg1, arg2);
    }

    /**
     * Computes the result of the subtraction operation.
     *
     * @return A LiteralInteger representing the result of the subtraction.
     * @throws EvaluationException if there's an error during evaluation.
     * @throws OutOfBoundsException if the operation goes out of bounds.
     */
    @Override
    public Literal compute() throws EvaluationException, OutOfBoundsException {
        return new LiteralInteger(_arg1.value().asInt() - _arg2.value().asInt());
    }
    
}
