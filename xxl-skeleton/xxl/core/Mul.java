package xxl.core;

import xxl.core.exception.EvaluationException;

/*
 * Represents a multiplication operation as a binary function.
 */
public class Mul extends BinaryFunction {

    public Mul(Content arg1, Content arg2) {
        super("MUL", arg1, arg2);
    }

    @Override
    public void compute() {
        try {
            _value = new LiteralInteger(_arg1.value().asInt() * _arg2.value().asInt());
        } catch (EvaluationException e) {
            _value = new LiteralException();
        }
    }
}