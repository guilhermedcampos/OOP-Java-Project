package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Represents a division operation between two content elements.
 */
public class Div extends BinaryFunction {

    /**
     * Constructs a division with the specified content arguments.
     *
     * @param arg1 the first content object to be divided.
     * @param arg2 the second content object used as the divisor.
     */
    public Div(Content arg1, Content arg2) {
        super("DIV", arg1, arg2);
    }

    /**
     * Computes the division of the two content objects and sets the result.
     * If an evaluation exception or arithmetic exception occurs during computation, sets the result to a LiteralException.
     */
    @Override
    public void compute() {
        try {
            _value = new LiteralInteger(_arg1.value().asInt() / _arg2.value().asInt());
        } catch (EvaluationException | ArithmeticException e) {
            _value = new LiteralException();
        }
    }
}
