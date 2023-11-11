package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Represents the addition operation between two content objects.
 */
public class Add extends BinaryFunction {

    /**
     * Constructs an addition with the specified content arguments.
     *
     * @param arg1 the first content to be added.
     * @param arg2 the second content to be added.
     */
    public Add(Content arg1, Content arg2) {
        super("ADD", arg1, arg2);
    }
    
    /**
     * Computes the addition of the two content objects and sets the result.
     * If an evaluation exception occurs during computation, sets the result to a LiteralException.
     */
    @Override
    public void compute() {
        try {
            _value = new LiteralInteger(_arg1.value().asInt() + _arg2.value().asInt());
        } catch (EvaluationException e) {
            _value = new LiteralException();
        }
    }
}