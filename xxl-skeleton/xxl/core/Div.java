package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Represents a division operation between two content elements.
 */
public class Div extends BinaryFunction {
    public Div(Content arg1, Content arg2) {
        super("DIV", arg1, arg2);
    }

    @Override
    public void compute() {
        try {
            _value = new LiteralInteger(_arg1.value().asInt() / _arg2.value().asInt());
        } catch (EvaluationException | ArithmeticException e) {
            _value = new LiteralException();
        }
    }
}
