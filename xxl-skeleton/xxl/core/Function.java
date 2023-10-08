package xxl.core;
import xxl.core.exception.EvaluationException;
import xxl.core.exception.OutOfBoundsException;

public abstract class Function {
    private String _name;

    public Function(String name) {
        _name = name;
    }
    
    protected String getName() {
        return _name;
    }

    public abstract Literal compute() throws EvaluationException, OutOfBoundsException;

    public String asString() throws EvaluationException, OutOfBoundsException {
        return compute().asString();
    }

    public int asInt() throws EvaluationException, OutOfBoundsException {
        return compute().asInt();
    }

    public Literal value() throws EvaluationException, OutOfBoundsException {
        return compute();
    }
}
