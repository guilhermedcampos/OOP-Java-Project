package xxl.core;

import xxl.core.exception.EvaluationException;

/* 
 * Represents the product operation between a range of cells.
 */
public class Product extends SequenceFunction {

    /**
     * Constructs a new product operation with the specified range description.
     *
     * @param rangeDescription the description of the range for the product operation.
     */
    public Product(String rangeDescription) {
        super("PRODUCT", rangeDescription);
    }

    /**
     * Computes the product of the values within the specified range of cells and sets the result.
     * If any evaluation exception occurs during computation, the result is set to a LiteralException.
     */
    @Override
    public void compute() {
        int total = 1;
        try {
            Cell[] cells = getCellsFromRangeDescription(_rangeDescription);

            for (Cell cell : cells) {
                total *= cell.getContent().asInt();
            }
            _value = new LiteralInteger(total);
        } catch (EvaluationException e) {
            _value = new LiteralException();
        }
    }
}
