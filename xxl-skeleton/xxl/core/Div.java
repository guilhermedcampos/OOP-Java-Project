package xxl.core;

import xxl.core.exception.EvaluationException;

public class Div extends BinaryFunction {
    public Div(Content arg1, Content arg2) throws EvaluationException {
        super("DIV", arg1, arg2);
    }

    @Override
    public Content evaluate() throws EvaluationException {
        if (arg1 instanceof IntegerLiteral && arg2 instanceof IntegerLiteral) {
            int divisor = ((IntegerLiteral) arg2).getValue();
            if (divisor == 0) {
                throw new EvaluationException("Division by zero is not allowed.");
            }
            int result = ((IntegerLiteral) arg1).getValue() / divisor;
            return new IntegerLiteral(result);
        } else {
            throw new EvaluationException("DIV function requires two IntegerLiteral arguments.");
        }
    }
}
