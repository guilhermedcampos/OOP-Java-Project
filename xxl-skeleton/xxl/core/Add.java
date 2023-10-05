package xxl.core;

import xxl.core.exception.EvaluationException;

public class Add extends BinaryFunction {

    public Add(Content arg1, Content arg2) {
        super("ADD", arg1, arg2);
    }

    @Override
    public Literal evaluate() throws EvaluationException {
        // Evaluate the two arguments
        Literal arg1Value = evaluateArgument(arg1);
        Literal arg2Value = evaluateArgument(arg2);

        // Check if both arguments are either IntegerLiteral or StringLiteral
        if ((arg1Value instanceof IntegerLiteral || arg1Value instanceof StringLiteral) &&
            (arg2Value instanceof IntegerLiteral || arg2Value instanceof StringLiteral)) {

            // If both arguments are of the same type, perform addition
            if (arg1Value instanceof IntegerLiteral && arg2Value instanceof IntegerLiteral) {
                int result = ((IntegerLiteral) arg1Value).getValue() + ((IntegerLiteral) arg2Value).getValue();
                return new IntegerLiteral(result);
            } else {
                // If at least one argument is a StringLiteral, concatenate them
                String result = arg1Value.toString() + arg2Value.toString();
                return new StringLiteral(result);
            }
        } else {
            throw new EvaluationException("ADD function requires two IntegerLiteral or StringLiteral arguments.");
        }
    }
}
