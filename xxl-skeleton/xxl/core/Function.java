package xxl.core;
import xxl.core.exception.EvaluationException;

public abstract class Function {
    private String _name;

    public Function(String name) {
        _name = name;
    }
    
    protected String getName() {
        return _name;
    }

    public abstract Literal compute() throws EvaluationException;

    public String asString() throws EvaluationException {
        return compute().asString();
    }

    public int asInt() throws EvaluationException {
        return compute().asInt();
    }

    public Literal value() throws EvaluationException {
        return compute();
    }
}
