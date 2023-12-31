package xxl.app.search;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.exception.OutOfBoundsException;
import xxl.core.Calculator;
import xxl.core.Range;
import xxl.core.Spreadsheet;
import xxl.core.Cell;
import xxl.core.SearchValueVisitor;
import java.util.List;

/**
 * Command for searching content values.
 */
class DoShowValues extends Command<Spreadsheet> {
  
  /**
   * Constructs a new command for showing cells with specified content values.
   *
   * @param receiver The spreadsheet to perform the search on.
   */
  DoShowValues(Spreadsheet receiver) {
    super(Label.SEARCH_VALUES, receiver);
    addStringField("value", Message.searchValue());
  }

  /**
   * Executes the command by searching for cells with specified content values in the spreadsheet.
   */
  @Override
  protected final void execute() {
    String searchTerm = stringField("value");

    // Create a visitor to search for content values
    SearchValueVisitor valueVisitor = new SearchValueVisitor(searchTerm);

    // Traverse the cells and apply the search visitor
    for (Cell[] row : _receiver.getSpreadsheet().getCells()) {
      for (Cell cell : row) {
        valueVisitor.visit(cell);
      }
    }

    // Get the matching cells from the visitor
    List<Cell> matchingValueCells = valueVisitor.getMatchingCells();

    // Process and display the matching cells
    for (Cell cell : matchingValueCells) {
      _display.addLine(cell.toString() + "|" + cell.getContent().toString()); // Display the matching cells
    }
    _display.display();
  }
}
