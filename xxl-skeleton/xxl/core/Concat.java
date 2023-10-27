package xxl.core;

import xxl.core.exception.EvaluationException;

public class Concat extends SequenceFunction {

    public Concat(String rangeDescription){
        super("CONCAT", rangeDescription);
        
    }

    @Override
    public void compute() {
        Cell[] cells = getCellsFromRangeDescription(_rangeDescription);
        String total = "";

        for (Cell c : cells) {
            try {
                total += c.getContent().asString();
            } catch (EvaluationException e){
                continue;
            }
        }


    _value = new LiteralString(total.toString());
    }
}