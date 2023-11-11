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

  /**
   * Constructs a new DoOpen command with the specified receiver.
   *
   * @param receiver The calculator instance to associate with this command.
   */
  DoOpen(Calculator receiver) {
    super(Label.OPEN, receiver);
    addStringField("fileName", Message.openFile());
  }


  /**
   * Executes the DoOpen command, prompting the user to open an existing file and loading its contents into the calculator.
   *
   * @throws CommandException if an error occurs during command execution.
   *                         Throws a FileOpenFailedException if the file cannot be opened.
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

    String fileName = stringField("fileName");
    try {
      _receiver.load(fileName);
    } catch (UnavailableFileException e) {
      throw new FileOpenFailedException(e);
    }
  }
}
