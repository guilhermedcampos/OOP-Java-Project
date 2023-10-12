package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;
import xxl.core.Spreadsheet;
import xxl.app.main.DoSave;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

  DoNew(Calculator receiver) {
    super(Label.NEW, receiver);
    addIntegerField("lines", Message.lines());
    addIntegerField("columns", Message.columns());
  }

  @Override
  protected final void execute() throws CommandException {
    // see if a file is opened
    if (_receiver.getSpreadsheet() != null) {

      // see if its changed
      if (_receiver.getSpreadsheet().isChanged()) {

        // if its changed, ask to save before exit
        boolean bool = Form.confirm(Message.saveBeforeExit());
        if (bool == true) {
          DoSave doSaveCommand = new DoSave(_receiver);
          try {
            doSaveCommand.performCommand();
          } catch (CommandException e) {
            e.printStackTrace();
          }
        }
      }
    }

    // Ask for the number of columns and lines
    int lines = integerField("lines");
    int columns = integerField("columns");

    // Create a new empty spreadsheet with the specified dimensions
    Spreadsheet newSpreadsheet = new Spreadsheet(lines, columns);

    // Set the newly created spreadsheet as the current one
    Calculator.setSpreadsheet(newSpreadsheet);
  }
}
