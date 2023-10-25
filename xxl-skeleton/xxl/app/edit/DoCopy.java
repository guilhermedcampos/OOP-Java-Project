package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Range;
import xxl.core.Spreadsheet;
// FIXME import classes
import xxl.core.exception.OutOfBoundsException;

/**
 * Copy command.
 */
class DoCopy extends Command<Spreadsheet> {

  DoCopy(Spreadsheet receiver) {
    super(Label.COPY, receiver);
    addStringField("range", Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String range = stringField("range");
    Range parsedRange = Range.buildRange(range);
    try {
      
      if (parsedRange.isRangeValid()) {
        // Call the clear method with the specified range
        _receiver.getSpreadsheet().copy(range);

      }
            
        } catch (OutOfBoundsException e) {
            // If an out-of-bounds exception occurs, throw an InvalidCellRangeException
            throw new InvalidCellRangeException(range);
        }
  }
}
