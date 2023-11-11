package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Range;
import xxl.core.Spreadsheet;
import xxl.core.exception.OutOfBoundsException;

/**
 * Paste command.
 */
class DoPaste extends Command<Spreadsheet> {

  /**
   * Constructs a new instance of the Paste command.
   *
   * @param receiver The spreadsheet on which the Paste command operates.
   */
  DoPaste(Spreadsheet receiver) {
    super(Label.PASTE, receiver);
    addStringField("range", Message.address());
  }


  /**
   * Executes the Paste command, inserting the content from the clipboard into the specified range.
   *
   * @throws CommandException if an error occurs during the execution of the Paste command.
   */
  @Override
  protected final void execute() throws CommandException {
    String range = stringField("range");
    Range parsedRange = Range.buildRange(range);
    if (_receiver.getSpreadsheet().getCutBuffer().getContents() != null) {
      try {
        if (parsedRange.isRangeValid()) {
          _receiver.getSpreadsheet().paste(range);
        }
      } catch (OutOfBoundsException e) {
        throw new InvalidCellRangeException(range);
      }
    }
  }
}
