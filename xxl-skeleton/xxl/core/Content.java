package xxl.core;

import xxl.core.exception.EvaluationException;
import xxl.core.exception.OutOfBoundsException;

public abstract class Content {
    public abstract String toString();
    protected abstract Literal value() throws OutOfBoundsException;
    
    public String asString() throws EvaluationException, OutOfBoundsException{
        return value().asString();
    }
    
    public int asInt() throws EvaluationException, OutOfBoundsException {
        return value().asInt();
    }
}
