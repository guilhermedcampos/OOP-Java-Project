package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Class representing a subtraction operation between two contents.
 */
public class Sub extends BinaryFunction {
    /**
     * Constructs a subtraction operation with two content arguments.
     *
     * @param arg1 The first content argument.
     * @param arg2 The second content argument.
     */
    public Sub(Content arg1, Content arg2) {
        super("SUB", arg1, arg2);
    }

    /**
     * Computes the result of the subtraction operation.
     *
     * @return A LiteralInteger representing the result of the subtraction.
     * @throws EvaluationException  if there's an error during evaluation.
     */
    @Override
    public void update() {
       try{
        _value = new LiteralInteger(_arg1.value().asInt() - _arg2.value().asInt());
       } catch (EvaluationException e){
        _value = new LiteralException();
       }
    }
}
