package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Spreadsheet;
import xxl.core.exception.OutOfBoundsException;

/**
 * Delete command.
 */
class DoDelete extends Command<Spreadsheet> {

  /**
   * Constructs a new Delete command with the specified receiver (spreadsheet).
   *
   * @param receiver the spreadsheet on which the command operates.
   */
  DoDelete(Spreadsheet receiver) {
    super(Label.DELETE, receiver);
    addStringField("range", Message.address());
  }

  /**
   * Executes the Delete command, removing the content from the specified range.
   *
   * @throws CommandException if an error occurs during command execution.
   */
  @Override
  protected final void execute() throws CommandException {
    String range = stringField("range");
    try {
      _receiver.getSpreadsheet().clear(range);
    } catch (OutOfBoundsException e) {
      throw new InvalidCellRangeException(range);
    }
  }
}
