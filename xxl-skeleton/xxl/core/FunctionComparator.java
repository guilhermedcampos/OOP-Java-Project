package xxl.core;

import java.util.Comparator;

/**
 * A comparator for sorting cells containing functions based on their function
 * name, row, and column.
 */
public class FunctionComparator implements Comparator<Cell> {
    @Override
    public int compare(Cell cell1, Cell cell2) {
        // Compare by function name
        int nameComparison = ((Function) cell1.getContent()).getName()
                .compareTo(((Function) cell2.getContent()).getName());
        if (nameComparison != 0) {
            return nameComparison;
        }

        // Compare by row
        int rowComparison = Integer.compare(cell1.getRow(), cell2.getRow());
        if (rowComparison != 0) {
            return rowComparison;
        }

        // Compare by column
        return Integer.compare(cell1.getCol(), cell2.getCol());
    }
}
