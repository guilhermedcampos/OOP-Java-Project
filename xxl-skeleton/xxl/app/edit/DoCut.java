package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Spreadsheet;
import xxl.core.Range;
import xxl.core.exception.OutOfBoundsException;
import xxl.app.exception.InvalidCellRangeException;

/**
 * Cut command.
 */
class DoCut extends Command<Spreadsheet> {

  /**
   * Constructs a new Cut command with the specified receiver (spreadsheet).
   *
   * @param receiver the spreadsheet on which the command operates.
   */
  DoCut(Spreadsheet receiver) {
    super(Label.CUT, receiver);
    addStringField("range", Message.address());
  }
  
  /**
   * Executes the Cut command, cutting the content within the specified range.
   *
   * @throws CommandException if an error occurs during command execution.
   */
  @Override
  protected final void execute() throws CommandException {
    String range = stringField("range");
    Range parsedRange = Range.buildRange(range);
    try {
      if (parsedRange.isRangeValid()) {
        _receiver.getSpreadsheet().copy(range);
        _receiver.getSpreadsheet().clear(range);
      }
    } catch (OutOfBoundsException e) {
      throw new InvalidCellRangeException(range);
    }
  }
}
