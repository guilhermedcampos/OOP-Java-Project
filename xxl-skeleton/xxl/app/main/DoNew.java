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

  DoNew(Calculator receiver) {
    super(Label.NEW, receiver);
    addIntegerField("lines", Message.lines());
    addIntegerField("columns", Message.columns());
  }

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
