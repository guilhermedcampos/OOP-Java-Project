package xxl.core;

import xxl.core.exception.EvaluationException;

public class Div extends BinaryFunction {
    public Div(Content arg1, Content arg2) throws EvaluationException {
        super("DIV", arg1, arg2);

    }

    @Override
    public LiteralInteger compute() throws EvaluationException {
        int res = _arg1.value().asInt() / _arg2.value().asInt();
        return new LiteralInteger(res);
    }

}
