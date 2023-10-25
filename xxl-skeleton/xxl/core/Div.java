package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Represents a division operation between two content elements.
 */
public class Div extends BinaryFunction {

    /**
     * Constructs a new division operation with the given arguments.
     *
     * @param arg1 the first argument.
     * @param arg2 the second argument.
     */
    public Div(Content arg1, Content arg2) {
        super("DIV", arg1, arg2);
    }

    /**
     * Computes the result of the division operation.
     *
     * @return the result of the division as a LiteralInteger.
     * @throws EvaluationException  if there is an error during evaluation.
     */
    @Override
    public void update(){
        try {
        _value = new LiteralInteger(_arg1.value().asInt() / _arg2.value().asInt());
        } catch (EvaluationException | ArithmeticException e) {
        _value = new LiteralException();
        }
    }
}
