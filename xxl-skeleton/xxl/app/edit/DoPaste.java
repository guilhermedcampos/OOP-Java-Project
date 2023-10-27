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

  DoPaste(Spreadsheet receiver) {
    super(Label.PASTE, receiver);
    addStringField("range", Message.address());
  }

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
