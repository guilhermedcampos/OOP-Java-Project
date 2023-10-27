package xxl.core;

import java.util.List;
import java.util.ArrayList;

public class SearchValueVisitor implements ContentVisitor {
    private String _searchTerm;
    private List<Cell> _matchingCells;
    private Cell _cell;

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
        _cell = cell;
        content.accept(this);
    }

    @Override
    public void visit(LiteralString content) {
        if (content.value().toString().equals(_searchTerm)) {
            _matchingCells.add(_cell);
        }
    }

    @Override
    public void visit(LiteralInteger content) {
        if (content.value().toString().equals(_searchTerm)) {
            _matchingCells.add(_cell);
        }
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
        if (content.value().toString().equals(_searchTerm)) {
            _matchingCells.add(_cell);
        }
    }

    @Override
    public void visit(Function content) {
        if (content.value().toString().equals(_searchTerm)) {
            _matchingCells.add(_cell);
        }
    }
}