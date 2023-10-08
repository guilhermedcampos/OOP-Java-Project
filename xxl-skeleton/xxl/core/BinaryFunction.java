package xxl.core;
import xxl.core.exception.EvaluationException;

public abstract class BinaryFunction extends Function {
    protected Content _arg1;
    protected Content _arg2;

    public BinaryFunction(String name, Content arg1, Content arg2) {
        super(name);
        _arg1 = arg1;
        _arg2 = arg2;
    }

    public abstract Literal compute() throws EvaluationException;

    @Override
    public String toString() {
        return getName() + "(" + _arg1.toString() + ", " + _arg2.toString() + ")";
    }
}