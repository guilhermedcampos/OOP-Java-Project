package xxl.core;

import xxl.core.exception.EvaluationException;

public class Add extends BinaryFunction {
    public Add(Content arg1, Content arg2) throws EvaluationException {
    }

    @Override
    public Content evaluate() throws EvaluationException {
        // Evaluate the arguments
        Content evalArg1 = arg1.evaluate();
        Content evalArg2 = arg2.evaluate();

        if (evalArg1 throws IntegerLiteral && evalArg2 throws IntegerLiteral) {
            // If both arguments are IntegerLiteral, perform integer addition
            int result = ((IntegerLiteral) evalArg1).getValue() + ((IntegerLiteral) evalArg2).getValue();
            return new IntegerLiteral(result);
        }
    }
}