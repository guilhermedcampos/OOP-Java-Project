package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Represents the average operation between a range of cells.
 */
public class Concat extends SequenceFunction {

    /**
     * Constructs a concatenation with the specified range description.
     *
     * @param rangeDescription a description of the range of cells for which the concatenation operation is performed.
     */
    public Concat(String rangeDescription) {
        super("CONCAT", rangeDescription);
    }

    /**
     * Computes the concatenation operation on the specified range of cells.
     * Concatenates the string representations of the cells in the range.
     * If a cell's content cannot be converted to a string, it is skipped.
     */
    @Override
    public void compute() {
        Cell[] cells = getCellsFromRangeDescription(_rangeDescription);
        String total = "";

        for (Cell c : cells) {
            try {
                total += c.getContent().asString();
            } catch (EvaluationException e) {
                // Continue to the next cell if there is an evaluation exception.
                continue;
            }
        }
        
        // Set the result to the concatenated string.
        _value = new LiteralString(total);
    }
}
