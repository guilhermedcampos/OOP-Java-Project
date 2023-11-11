package xxl.core;

import java.util.Comparator;

/**
 * A comparator for sorting cells containing functions based on their function
 * name, row, and column.
 */
public class FunctionComparator implements Comparator<Cell> {

    /**
     * Compares two cells containing functions based on their function name, row, and column.
     *
     * @param firstCell the first cell to compare.
     * @param secondCell the second cell to compare.
     * @return a negative integer, zero, or a positive integer if the first cell is less than, equal to, or greater than
     *         the second cell, respectively, based on function name, row, and column.
     */
    @Override
    public int compare(Cell firstCell, Cell secondCell) {
        // Compare by function name
        int nameComparison = ((Function) firstCell.getContent()).getName()
                .compareTo(((Function) secondCell.getContent()).getName());
        if (nameComparison != 0) {
            return nameComparison;
        }

        // Compare by row
        int rowComparison = Integer.compare(firstCell.getRow(), secondCell.getRow());
        if (rowComparison != 0) {
            return rowComparison;
        }

        // Compare by column
        return Integer.compare(firstCell.getCol(), secondCell.getCol());
    }
}
