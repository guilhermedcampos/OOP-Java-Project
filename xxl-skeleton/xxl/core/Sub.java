package xxl.core;

import xxl.core.exception.EvaluationException;

public class Sub extends BinaryFunction {
    public Sub(Content arg1, Content arg2) throws EvaluationException {
        super("SUB", arg1, arg2);
    }

    @Override
    public Content evaluate() throws EvaluationException {
        if (arg1 instanceof IntegerLiteral && arg2 instanceof IntegerLiteral) {
            int result = ((IntegerLiteral) arg1).getValue() - ((IntegerLiteral) arg2).getValue();
            return new IntegerLiteral(result);
        } else {
            throw new EvaluationException("SUB function requires two IntegerLiteral arguments.");
        }
    }
}
