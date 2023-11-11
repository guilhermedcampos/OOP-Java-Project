package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Range;
import xxl.core.Spreadsheet;
import xxl.core.exception.OutOfBoundsException;

/**
 * Copy command.
 */
class DoCopy extends Command<Spreadsheet> {

  /**
   * Constructs a new Copy command with the specified receiver (spreadsheet).
   *
   * @param receiver the spreadsheet on which the command operates.
   */
  DoCopy(Spreadsheet receiver) {
    super(Label.COPY, receiver);
    addStringField("range", Message.address());
  }

  /**
   * Executes the Copy command, copying the content within the specified range.
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
      }
    } catch (OutOfBoundsException e) {
      throw new InvalidCellRangeException(range);
    }
  }
}