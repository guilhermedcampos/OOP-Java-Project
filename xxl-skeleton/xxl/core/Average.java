package xxl.core;

import xxl.core.exception.EvaluationException;
import xxl.core.exception.OutOfBoundsException;

public class Average extends SequenceFunction {

    public Average(String rangeDescription){
        super("AVERAGE", rangeDescription);
        
    }

    @Override
    protected Literal compute() throws EvaluationExceptionn {
        int total = 0;
        int numCells;
    
        try {
            Cell[] cells = getCellsFromRangeDescription(_rangeDescription);
            numCells = Range.getNumCells(cells);
    
            for (Cell cell : cells) {
                total += cell.value().asInt();
            }
    
            if (numCells > 0) {
                return new LiteralInteger(total / numCells);
            } else {
                throw new EvaluationException("Division by zero: the range has no cells.");
            }
        } catch (OutOfBoundsException e) {
            throw new OutOfBoundsException("Gama inválida");
        }
    }
    
    
}