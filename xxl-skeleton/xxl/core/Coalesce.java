package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Represents the coalesce operation between a range of cells.
 */
public class Coalesce extends SequenceFunction {

    /**
     * Constructs a coalesce operation with the specified range description.
     *
     * @param rangeDescription a description of the range of cells for which the coalesce operation is performed.
     */
    public Coalesce(String rangeDescription){
        super("COALESCE", rangeDescription);
        
    }

    /**
     * Computes the coalesce operation on the specified range of cells.
     * The result is the first non-null, non-empty string encountered in the range.
     * If all cells in the range are empty or evaluate to null, the result is an empty string.
     */
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
                // Continue to the next cell if there is an evaluation exception.
                continue;
            }
        }

    // If no non-null, non-empty string is found, set the result to an empty string.
    _value = new LiteralString("");
    }
}