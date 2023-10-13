package xxl.core;

import xxl.core.exception.EvaluationException;
/**
 * Represents a multiplication operation as a binary function.
 */
public class Mul extends BinaryFunction {

    /**
     * Initializes a new multiplication operation with the specified arguments.
     *
     * @param arg1 the first argument.
     * @param arg2 the second argument.
     */
    public Mul(Content arg1, Content arg2) {
        super("MUL", arg1, arg2);
    }

    /**
     * Computes the result of the multiplication operation.
     *
     * @return the result as a `LiteralInteger`.
     * @throws EvaluationException  if there's an error in evaluating the
     *                              multiplication.
     */
    @Override
    protected Literal compute() throws EvaluationException{
        return new LiteralInteger(_arg1.value().asInt() * _arg2.value().asInt());
    }
}
