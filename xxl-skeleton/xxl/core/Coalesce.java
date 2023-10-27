package xxl.core;

import xxl.core.exception.EvaluationException;

public class Coalesce extends SequenceFunction {

    public Coalesce(String rangeDescription){
        super("COALESCE", rangeDescription);
        
    }

    @Override
    public void compute() {
        Cell[] cells = getCellsFromRangeDescription(_rangeDescription);
        String result;
        for (Cell c : cells) {
            try {
                result = c.getContent().asString();
                _value = new LiteralString(result);
                return;
            } catch (EvaluationException e){
                continue;
            }
        }
    _value = new LiteralString("");
    }
}