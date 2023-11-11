package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;
import xxl.core.Spreadsheet;

/**
 * Command for creating a new file or opening an existing one.
 */
class DoNew extends Command<Calculator> {

  /**
   * Constructs a new DoNew command with the specified receiver.
   *
   * @param receiver The calculator instance to associate with this command.
   */
  DoNew(Calculator receiver) {
    super(Label.NEW, receiver);
    addIntegerField("lines", Message.lines());
    addIntegerField("columns", Message.columns());
  }

  /**
   * Executes the DoNew command, creating a new spreadsheet with the specified number of lines and columns.
   *
   * @throws CommandException if an error occurs during command execution.
   */
  @Override
  protected final void execute() throws CommandException {
    if (_receiver.getSpreadsheet() != null) {
      if (_receiver.getSpreadsheet().isChanged()) {
        if (Form.confirm(Message.saveBeforeExit())) {
          DoSave doSaveCommand = new DoSave(_receiver);
          doSaveCommand.performCommand();
        }
      }
    }

    int lines = integerField("lines");
    int columns = integerField("columns");
    Spreadsheet newSpreadsheet = _receiver.createNewSpreadsheet(lines, columns);
    _receiver.setSpreadsheet(newSpreadsheet);
  }
}
