package xxl.core;

import xxl.core.exception.EvaluationException;

public class Add extends BinaryFunction {
    public Add(Content arg1, Content arg2) throws EvaluationException {
    }

    @Override
    public Content evaluate() throws EvaluationException {

        if ((arg1.evaluate() == "Integer") && (arg2.evaluate() == "Integer")) {
            int result = arg1.getValue() + arg2.getValue();
            return new IntegerLiteral(result);
        }
        else {
            throw new EvaluationException("ADD function requires two Integer arguments.");
        }
    }
}