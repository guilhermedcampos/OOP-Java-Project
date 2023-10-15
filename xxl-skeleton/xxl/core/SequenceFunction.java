package xxl.core;

import xxl.core.exception.EvaluationException;
import xxl.core.exception.OutOfBoundsException;

public abstract class SequenceFunction extends Function {

    protected String _rangeDescription;

    public SequenceFunction(String name, String range) {
        super(name);
        _rangeDescription = range;
    }

    protected abstract Literal compute() throws EvaluationException;

    public Cell[] getCellsFromRangeDescription(String rangeDescription) throws OutOfBoundsException {
        return Range.buildRange(_rangeDescription).traverse();

    }

    @Override
    public String toString() {
        /* 
        try {
            String result = value() + "=" + getName() + "(" + _arg1.toString() + ":" + _arg2.toString() + ")";
            return Function.cleanStringAfterFirstEquals(result);
        } catch (EvaluationException e) {
            String result = "#VALUE" + "=" + getName() + "(" + _arg1.toString() + "," + _arg2.toString() + ")";
            return Function.cleanStringAfterFirstEquals(result);
        }
        */
        return "";
    }
    
}
