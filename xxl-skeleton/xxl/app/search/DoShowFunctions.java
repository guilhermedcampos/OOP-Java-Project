package xxl.app.search;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.exception.OutOfBoundsException;
import xxl.core.Calculator;
import xxl.core.Spreadsheet;
import xxl.core.Range;
import xxl.core.Cell;
import xxl.core.SearchFunctionVisitor;
import xxl.core.FunctionComparator;
import java.util.List;

/**
 * Command for searching function names.
 */
class DoShowFunctions extends Command<Spreadsheet> {

  DoShowFunctions(Spreadsheet receiver) {
    super(Label.SEARCH_FUNCTIONS, receiver);
    addStringField("function", Message.searchFunction());
  }

  @Override
  protected final void execute() {
    String functionToSearch = stringField("function");

    // Create a visitor to search for function names
    SearchFunctionVisitor functionVisitor = new SearchFunctionVisitor(functionToSearch);

    // Traverse the cells and apply the search visitor
    for (Cell[] row : _receiver.getSpreadsheet().getCells()) {
      for (Cell cell : row) {
        functionVisitor.visit(cell);
      }
    }

    // Get the matching cells from the visitor
    List<Cell> matchingFunctionCells = functionVisitor.getMatchingCells();

    // sort matching functions
    matchingFunctionCells.sort(new FunctionComparator());

    // Process and display the matching cells
    for (Cell cell : matchingFunctionCells) {
      _display.addLine(cell.toString() + "|" + cell.getContent().toString()); // Display the matching cells
    }
    _display.display();
  }
}
