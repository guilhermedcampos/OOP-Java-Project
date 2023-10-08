package xxl.core;

import xxl.core.exception.EvaluationException;
import xxl.core.exception.OutOfBoundsException;

public class Mul extends BinaryFunction {
    public Mul(Content arg1, Content arg2) throws EvaluationException, OutOfBoundsException {
        super("MUL", arg1, arg2);

    }

    @Override
    public LiteralInteger compute() throws EvaluationException, OutOfBoundsException {
        int res = _arg1.value().asInt() * _arg2.value().asInt();
        return new LiteralInteger(res);
    }
}
