package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;
import xxl.core.exception.UnavailableFileException;
import xxl.app.exception.FileOpenFailedException;

/**
 * Open existing file.
 */
public class DoOpen extends Command<Calculator> {

  DoOpen(Calculator receiver) {
    super(Label.OPEN, receiver);
    addStringField("fileName", Message.openFile());
  }

  @Override
  protected final void execute() throws CommandException {
    if (_receiver.getSpreadsheet() != null) {
      if (_receiver.getSpreadsheet().isChanged()) {
        boolean bool = Form.confirm(Message.saveBeforeExit());
        if (bool) {
          DoSave doSaveCommand = new DoSave(_receiver);
          doSaveCommand.performCommand();
        }
      }
    }

    String fileName = stringField("fileName");
    try {
      _receiver.load(fileName);
    } catch (UnavailableFileException e) {
      throw new FileOpenFailedException(e);
    }
  }
}
