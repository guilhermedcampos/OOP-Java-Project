package xxl.core;

import xxl.core.exception.EvaluationException;

public abstract class SequenceFunction extends Function {

    protected String _rangeDescription;

    public SequenceFunction(String name, String range) {
        super(name);
        _rangeDescription = range;
    }

    protected abstract Literal compute() throws EvaluationException;

    public Cell[] getCellsFromRangeDescription(String rangeDescription)  {
        return Range.buildRange(_rangeDescription).traverse();

    }

    protected Cell getFirstCell() {
        return getCellsFromRangeDescription(_rangeDescription)[0];
    }

    protected Cell getLastCell() {
        Cell[] cells = getCellsFromRangeDescription(_rangeDescription);
        return getCellsFromRangeDescription(_rangeDescription)[cells.length-1];
    }

    @Override
    public String toString() {

        try {
            String result = value() + "=" + getName() + "(" + getFirstCell().toString() + ":" + getLastCell().toString() + ")";
            return cleanStringAfterFirstEquals(result);
        } catch (EvaluationException e) {
            String result = "#VALUE" + "=" + getName() + "(" + getFirstCell().toString() + ":" + getLastCell().toString() + ")";
            return cleanStringAfterFirstEquals(result);
        }
    }
    
}
