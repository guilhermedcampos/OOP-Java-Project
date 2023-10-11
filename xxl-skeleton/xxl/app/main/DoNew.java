package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;
import xxl.core.Spreadsheet;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

  DoNew(Calculator receiver) {
    super(Label.NEW, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    // Ask for the number of columns and lines
    addIntegerField("lines", Message.lines());
    addIntegerField("columns", Message.columns());
    int lines = integerField("lines");
    int columns = integerField("columns");
    
    // Create a new empty spreadsheet with the specified dimensions
    Spreadsheet newSpreadsheet = new Spreadsheet(lines, columns);

    // Set the newly created spreadsheet as the current one
    Calculator.setSpreadsheet(newSpreadsheet);
  }
}
