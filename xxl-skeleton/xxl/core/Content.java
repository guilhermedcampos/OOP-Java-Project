package xxl.core;

import xxl.core.exception.EvaluationException;
import xxl.core.exception.SpreadsheetException;

public abstract class Content {
    public abstract String toString();
    protected abstract Literal value();
    
    public String asString() throws EvaluationException{
        return value().asString();
    }
    
    public int asInt() throws EvaluationException {
        return value().asInt();
    }
}
