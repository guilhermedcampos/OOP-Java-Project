package xxl.core;

import xxl.core.exception.EvaluationException;

/*
 * Represents a multiplication operation as a binary function.
 */
public class Mul extends BinaryFunction {

    /**
     * Constructs a multiplication with the specified content arguments.
     *
     * @param arg1 the first content object to be multiplied.
     * @param arg2 the second content object to be multiplied.
     */
    public Mul(Content arg1, Content arg2) {
        super("MUL", arg1, arg2);
    }

    /**
     * Computes the multiplication of the two content objects and sets the result.
     * If an evaluation exception occurs during computation, sets the result to a LiteralException.
     */
    @Override
    public void compute() {
        try {
            _value = new LiteralInteger(_arg1.value().asInt() * _arg2.value().asInt());
        } catch (EvaluationException e) {
            _value = new LiteralException();
        }
    }
}