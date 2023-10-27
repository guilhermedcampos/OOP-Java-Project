package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Represents the addition operation between two content objects.
 */
public class Add extends BinaryFunction {

    public Add(Content arg1, Content arg2) {
        super("ADD", arg1, arg2);
    }

    @Override
    public void compute() {
        try {
            _value = new LiteralInteger(_arg1.value().asInt() + _arg2.value().asInt());
        } catch (EvaluationException e) {
            _value = new LiteralException();
        }
    }
}