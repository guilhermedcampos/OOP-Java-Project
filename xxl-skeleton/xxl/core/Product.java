package xxl.core;

import xxl.core.exception.EvaluationException;

public class Product extends SequenceFunction {

    public Product(String rangeDescription){
        super("PRODUCT", rangeDescription);
        
    }

    @Override
    public void compute() {
    int total = 1;
        try {

        Cell[] cells = getCellsFromRangeDescription(_rangeDescription);


        for (Cell cell : cells) {
            total *= cell.getContent().asInt();
        }
        _value = new LiteralInteger(total);
        } catch (EvaluationException e){
            _value = new LiteralException();
        }
    }
}