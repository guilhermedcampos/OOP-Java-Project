package xxl.core;

/*
 * Represents a sequence function in the spreadsheet. Sequence functions operate
 * on a range of cells.
 */
public abstract class SequenceFunction extends Function {

    /**
     * The range description specifying the range of cells on which the sequence function operates.
     */
    protected String _rangeDescription;

    /**
     * Constructs a new sequence function with the specified name and range description.
     *
     * @param name             the name of the sequence function.
     * @param rangeDescription the description of the range of cells on which the function operates.
     */
    public SequenceFunction(String name, String rangeDescription) {
        super(name);
        _rangeDescription = rangeDescription;
        startObserving();
    }

    /**
     * Gets the first cell in the specified range.
     *
     * @return the first cell in the range.
     */
    protected Cell getFirstCell() {
        return getCellsFromRangeDescription(_rangeDescription)[0];
    }

    /**
     * Gets the last cell in the specified range.
     *
     * @return the last cell in the range.
     */
    protected Cell getLastCell() {
        Cell[] cells = getCellsFromRangeDescription(_rangeDescription);
        return getCellsFromRangeDescription(_rangeDescription)[cells.length - 1];
    }

    /**
     * Starts observing the cells within the specified range.
     * It sets this sequence function as an observer for each cell in the range.
     * It also triggers an update after starting the observation.
     */
    public void startObserving() {
        Cell[] rangeCells = getCellsFromRangeDescription(_rangeDescription);
        for (Cell cell : rangeCells) {
            cell.getContent().setIsObserving(true, this);
        }
        update();
    }

    /**
     * Stops observing the cells within the specified range.
     * It sets this sequence function as no longer observing for each cell in the range.
     */
    public void stopObserving() {
        Cell[] rangeCells = getCellsFromRangeDescription(_rangeDescription);
        for (Cell cell : rangeCells) {
            cell.getContent().setIsObserving(false, this);
        }
    }

    /**
     * Computes the value of the sequence function based on the content of the specified range of cells.
     */
    public abstract void compute();

    /**
     * Gets the cells from the specified range description.
     *
     * @param rangeDescription the description of the range.
     * @return an array of cells within the specified range.
     */
    public Cell[] getCellsFromRangeDescription(String rangeDescription) {
        return Range.buildRange(_rangeDescription).traverse();
    }

    /**
     * Returns a string representation of the sequence function.
     *
     * @return a string representation of the sequence function.
     */
    @Override
    public String toString() {
        String result = value().toString() + "=" + getName() + "(" + getFirstCell().toString() + ":"
                + getLastCell().toString() + ")";
        return cleanStringAfterFirstEquals(result);
    }
}
