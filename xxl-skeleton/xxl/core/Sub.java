package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Class representing a subtraction operation between two contents.
 */
public class Sub extends BinaryFunction {
    public Sub(Content arg1, Content arg2) {
        super("SUB", arg1, arg2);
    }

    @Override
    public void compute() {
        try {
            _value = new LiteralInteger(_arg1.value().asInt() - _arg2.value().asInt());
        } catch (EvaluationException e) {
            _value = new LiteralException();
        }
    }
}
