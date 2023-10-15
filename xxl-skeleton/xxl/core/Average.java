package xxl.core;

import xxl.core.exception.EvaluationException;

public class Average extends SequenceFunction {

    public Average(String rangeDescription){
        super("AVERAGE", rangeDescription);
        
    }

    @Override
    protected Literal compute() throws EvaluationException {
        int total = 0;
        int numCells;
    
            Cell[] cells = getCellsFromRangeDescription(_rangeDescription);
            numCells = cells.length;
    
            for (Cell cell : cells) {
                total += cell.value().asInt();
            }
    
            if (numCells > 0) {
                return new LiteralInteger(total / numCells);
            } else {
                throw new EvaluationException("Division by zero: the range has no cells.");
            }
    }
}