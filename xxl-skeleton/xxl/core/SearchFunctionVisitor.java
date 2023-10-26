package xxl.core;

import java.util.List;
import java.util.ArrayList;
import xxl.core.exception.EvaluationException;

public class SearchFunctionVisitor implements ContentVisitor {
    private String _searchTerm;
    private List<Cell> _matchingCells;
    private Cell _cell;

    public SearchFunctionVisitor(String searchTerm) {
        _searchTerm = searchTerm;
        _matchingCells = new ArrayList<>();
    }

    public List<Cell> getMatchingCells() {
        return _matchingCells;
    }

    @Override
    public void visit(Cell cell) {
        Content content = cell.getContent();
        _cell = cell;
        content.accept(this); // Visit the content to check for a match
    }

    @Override
    public void visit(LiteralString content) {
        // Do nothing for literals in the context of searching for functions
    }

    @Override
    public void visit(LiteralInteger content) {
        // Do nothing for literals in the context of searching for functions
    }

    @Override
    public void visit(LiteralException content) {
        // Do nothing for literals in the context of searching for functions
    }

    @Override
    public void visit(Null content) {
        // Do nothing for literals in the context of searching for functions
    }

    @Override
    public void visit(Reference content) {
        // Do nothing for references in the context of searching for functions
    }

    


    @Override
    public void visit(Function content) {
            if (content.getName().contains(_searchTerm)) {
                _matchingCells.add(_cell);
            }
    }
}