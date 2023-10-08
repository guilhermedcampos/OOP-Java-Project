package xxl.core;

import xxl.core.exception.EvaluationException;

public class LiteralString extends Literal{
    private String _value;

    public LiteralString(String value){
        _value = value;
    }

    @Override
    public String asString() {
        return _value;
    }

    @Override
    public String toString(){
        return _value;
    }

    @Override
    public int asInt() throws EvaluationException {
        throw new EvaluationException("Cannot convert IntegerLiteral to string.");
    }
}

