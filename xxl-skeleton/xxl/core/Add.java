package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Represents the addition operation between two content objects.
 */
public class Add extends BinaryFunction {

    /**
     * Constructs an instance of the Add class with two content arguments.
     *
     * @param arg1 the first content argument.
     * @param arg2 the second content argument.
     */
    public Add(Content arg1, Content arg2) {
        super("ADD", arg1, arg2);
    }

    /**
     * Computes the result of the addition operation and returns it as a
     * LiteralInteger.
     *
     * @return the result of the addition as a LiteralInteger.
     * @throws EvaluationException if there is an error during evaluation.
     */
    @Override
    protected Literal compute() throws EvaluationException {
        return new LiteralInteger(_arg1.value().asInt() + _arg2.value().asInt());
    }
}