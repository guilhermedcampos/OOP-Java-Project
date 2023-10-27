package xxl.core;

import xxl.core.exception.EvaluationException;

public class Average extends SequenceFunction {

    public Average(String rangeDescription){
        super("AVERAGE", rangeDescription);
        
    }

    @Override
    public void compute() {
        int total = 0;
        int numCells;
    
            try {
            Cell[] cells = getCellsFromRangeDescription(_rangeDescription);
            numCells = cells.length;
    
            for (Cell cell : cells) {
                total += cell.value().asInt();
            }
    
            if (numCells > 0) {
                _value = new LiteralInteger(total / numCells);}
            else {
                _value = new LiteralException();
            }
            } catch(EvaluationException e) {
                _value = new LiteralException();
            }
    }
}