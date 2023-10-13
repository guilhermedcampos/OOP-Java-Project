package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;
import xxl.core.Spreadsheet;
import xxl.app.main.DoSave;

/**
 * Command for creating a new file or opening an existing one.
 */
class DoNew extends Command<Calculator> {

  /**
   * Constructs a new instance of the "DoNew" command.
   *
   * @param receiver The calculator instance to which this command is attached.
   */
  DoNew(Calculator receiver) {
    super(Label.NEW, receiver);
    addIntegerField("lines", Message.lines());
    addIntegerField("columns", Message.columns());
  }

  /**
   * Executes the "New" command, allowing the user to create a new empty
   * spreadsheet.
   *
   * @throws CommandException If an error occurs during command execution.
   */
  @Override
  protected final void execute() throws CommandException {
    // Check if a file is currently opened
    if (_receiver.getSpreadsheet() != null) {

      // Check if it's been changed
      if (_receiver.getSpreadsheet().isChanged()) {

        // If it's been changed, ask to save before exiting
        boolean bool = Form.confirm(Message.saveBeforeExit());
        if (bool) {
          DoSave doSaveCommand = new DoSave(_receiver);
          doSaveCommand.performCommand();
        }
      }
    }

    // Prompt the user for the number of columns and lines
    int lines = integerField("lines");
    int columns = integerField("columns");

    // Create a new empty spreadsheet with the specified dimensions
    Spreadsheet newSpreadsheet = new Spreadsheet(lines, columns);

    // Set the newly created spreadsheet as the current one
    Calculator.setSpreadsheet(newSpreadsheet);
  }
}
