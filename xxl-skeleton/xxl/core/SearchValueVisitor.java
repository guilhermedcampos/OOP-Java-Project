package xxl.core;

import java.util.List;
import java.util.ArrayList;
import xxl.core.exception.EvaluationException;

public class SearchValueVisitor implements ContentVisitor {
    private String _searchTerm;
    private List<Cell> _matchingCells;

    public SearchValueVisitor(String searchTerm) {
        _searchTerm = searchTerm;
        _matchingCells = new ArrayList<>();
    }

    public List<Cell> getMatchingCells() {
        return _matchingCells;
    }

    @Override
    public void visit(Cell cell) {
        Content content = cell.getContent();
        content.accept(this, cell); // Visit the content to check for a match
    }

    @Override
    public void visit(Literal content, Cell cell) {
        try {
            if (content.value().asString().equals(_searchTerm)) {
                _matchingCells.add(cell);
            }
        } catch (EvaluationException e) {
            // Handle the exception, e.g., log it or ignore
        }
    }

    @Override
    public void visit(Reference content, Cell cell) {
        try {
            if (content.value().asString().equals(_searchTerm)) {
                _matchingCells.add(cell);
            }
        } catch (EvaluationException e) {
            // Handle the exception, e.g., log it or ignore
        }
    }

    @Override
    public void visit(Function content, Cell cell) {
            if (content.value().toString().equals(_searchTerm)) {
                _matchingCells.add(cell);
            }
    }
}