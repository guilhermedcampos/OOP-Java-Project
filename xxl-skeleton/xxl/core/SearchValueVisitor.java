package xxl.core;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a visitor for searching values in the spreadsheet cells.
 */
public class SearchValueVisitor implements ContentVisitor {
    /**
     * The search term used to identify cells with matching values.
     */
    private String _searchTerm;

    /**
     * List to store cells with values that match the search term.
     */
    private List<Cell> _matchingCells;

    /**
     * The current cell being visited.
     */
    private Cell _cell;

    /**
     * Constructs a new search value visitor with the specified search term.
     *
     * @param searchTerm the search term used to identify cells with matching values.
     */
    public SearchValueVisitor(String searchTerm) {
        _searchTerm = searchTerm;
        _matchingCells = new ArrayList<>();
    }

    /**
     * Gets the list of cells with values that match the search term.
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
     * Handles the visit to a literal string content and checks for a match with the search term.
     *
     * @param content the literal string content.
     */
    @Override
    public void visit(LiteralString content) {
        if (content.value().toString().equals(_searchTerm)) {
            _matchingCells.add(_cell);
        }
    }

    /**
     * Handles the visit to a literal integer content and checks for a match with the search term.
     *
     * @param content the literal integer content.
     */
    @Override
    public void visit(LiteralInteger content) {
        if (content.value().toString().equals(_searchTerm)) {
            _matchingCells.add(_cell);
        }
    }

    /**
     * Handles the visit to a literal exception content.
     *
     * @param content the literal exception content.
     */
    @Override
    public void visit(LiteralException content) {
        // Do nothing for literals in the context of searching for values
    }

    /**
     * Handles the visit to a null content.
     *
     * @param content the null content.
     */
    @Override
    public void visit(Null content) {
        // Do nothing for literals in the context of searching for values
    }

    /**
     * Handles the visit to a reference content and checks for a match with the search term.
     *
     * @param content the reference content.
     */
    @Override
    public void visit(Reference content) {
        if (content.value().toString().equals(_searchTerm)) {
            _matchingCells.add(_cell);
        }
    }

    /**
     * Handles the visit to a function content and checks for a match with the search term.
     *
     * @param content the function content.
     */
    @Override
    public void visit(Function content) {
        if (content.value().toString().equals(_searchTerm)) {
            _matchingCells.add(_cell);
        }
    }
}