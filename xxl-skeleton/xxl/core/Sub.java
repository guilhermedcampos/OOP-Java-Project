package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Class representing a subtraction operation between two contents.
 */
public class Sub extends BinaryFunction {

    /**
     * Constructs a new subtraction operation with the specified arguments.
     *
     * @param arg1 the first operand.
     * @param arg2 the second operand.
     */
    public Sub(Content arg1, Content arg2) {
        super("SUB", arg1, arg2);
    }

    /**
     * Computes the result of the subtraction operation and sets the value accordingly.
     * If an EvaluationException occurs during the computation, the result is set to a LiteralException.
     */
    @Override
    public void compute() {
        try {
            _value = new LiteralInteger(_arg1.value().asInt() - _arg2.value().asInt());
        } catch (EvaluationException e) {
            _value = new LiteralException();
        }
    }
}
