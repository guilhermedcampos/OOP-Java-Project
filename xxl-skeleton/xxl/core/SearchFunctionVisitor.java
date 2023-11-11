package xxl.core;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a visitor for searching functions in the spreadsheet cells.
 */
public class SearchFunctionVisitor implements ContentVisitor {

    /**
     * The search term used to identify matching functions.
     */
    private String _searchTerm;

    /**
     * List to store cells containing functions that match the search term.
     */
    private List<Cell> _matchingCells;

    /**
     * The current cell being visited.
     */
    private Cell _cell;

    /**
     * Constructs a new search function visitor with the specified search term.
     *
     * @param searchTerm the search term used to identify matching functions.
     */
    public SearchFunctionVisitor(String searchTerm) {
        _searchTerm = searchTerm;
        _matchingCells = new ArrayList<>();
    }

    /**
     * Gets the list of cells containing functions that match the search term.
     *
     * @return the list of matching cells.
     */
    public List<Cell> getMatchingCells() {
        return _matchingCells;
    }

    /**
     * Visits a cell and triggers the visit to its content.
     *
     * @param cell the cell being visited.
     */
    @Override
    public void visit(Cell cell) {
        Content content = cell.getContent();
        _cell = cell;
        content.accept(this);
    }

    /**
     * Handles the visit to a literal string content.
     *
     * @param content the literal string content.
     */
    @Override
    public void visit(LiteralString content) {
        // Do nothing for literals in the context of searching for functions
    }

    /**
     * Handles the visit to a literal integer content.
     *
     * @param content the literal integer content.
     */
    @Override
    public void visit(LiteralInteger content) {
        // Do nothing for literals in the context of searching for functions
    }

    /**
     * Handles the visit to a literal exception content.
     *
     * @param content the literal exception content.
     */
    @Override
    public void visit(LiteralException content) {
        // Do nothing for literals in the context of searching for functions
    }

    /**
     * Handles the visit to a null content.
     *
     * @param content the null content.
     */
    @Override
    public void visit(Null content) {
        // Do nothing for null contents in the context of searching for functions
    }

    /**
     * Handles the visit to a reference content.
     *
     * @param content the reference content.
     */
    @Override
    public void visit(Reference content) {
        // Do nothing for references in the context of searching for functions
    }

    /**
     * Handles the visit to a function content, checks for a match with the search term,
     * and adds the cell to the list of matching cells if applicable.
     *
     * @param content the function content.
     */
    @Override
    public void visit(Function content) {
        if (content.getName().contains(_searchTerm)) {
            _matchingCells.add(_cell);
        }
    }
}