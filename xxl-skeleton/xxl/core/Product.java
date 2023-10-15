package xxl.core;

import xxl.core.exception.EvaluationException;

public class Product extends SequenceFunction {

    public Product(String rangeDescription){
        super("PRODUCT", rangeDescription);
        
    }

    @Override
    protected Literal compute() throws EvaluationException {
        int total = 1;

        Cell[] cells = getCellsFromRangeDescription(_rangeDescription);


        for (Cell cell : cells) {
            total *= cell.value().asInt();
        }

    return new LiteralInteger(total);
    }
}