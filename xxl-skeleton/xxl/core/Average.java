package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Represents the average operation between a range of cells.
 */
public class Average extends SequenceFunction {

    /**
     * Constructs an average with the specified range description.
     *
     * @param rangeDescription a description of the range of cells for which the average is calculated.
     */
    public Average(String rangeDescription) {
        super("AVERAGE", rangeDescription);
    }

    /**
     * Computes the average of the values in the specified range of cells and sets the result.
     * If an evaluation exception occurs during computation, sets the result to a LiteralException.
     */
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
                _value = new LiteralInteger(total / numCells);
            } else {
                _value = new LiteralException();
            }
        } catch (EvaluationException e) {
            _value = new LiteralException();
        }
    }
}
