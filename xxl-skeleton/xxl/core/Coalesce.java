package xxl.core;

import xxl.core.exception.EvaluationException;

public class Coalesce extends SequenceFunction {

    public Coalesce(String rangeDescription){
        super("COALESCE", rangeDescription);
        
    }

    @Override
    protected Literal compute() throws EvaluationException {
        Cell[] cells = getCellsFromRangeDescription(_rangeDescription);
        String result;
        for (Cell c : cells) {
            try {
                result = c.getContent().asString();
                return new LiteralString(result);
            } catch (EvaluationException e){
                continue;
            }
        }
    return new LiteralString("");
    }
}