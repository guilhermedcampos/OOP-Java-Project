package xxl.core;

import xxl.core.exception.EvaluationException;

public class Mul extends BinaryFunction {
    public Mul(Content arg1, Content arg2) throws EvaluationException {
        super("MUL", arg1, arg2);
    }

    @Override
    public Content evaluate() throws EvaluationException {
        if (arg1 instanceof IntegerLiteral && arg2 instanceof IntegerLiteral) {
            int result = ((IntegerLiteral) arg1).getValue() * ((IntegerLiteral) arg2).getValue();
            return new IntegerLiteral(result);
        } else {
            throw new EvaluationException("MUL function requires two IntegerLiteral arguments.");
        }
    }
}
