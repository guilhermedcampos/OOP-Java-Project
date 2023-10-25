package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Spreadsheet;
import xxl.core.Range;
import xxl.core.exception.OutOfBoundsException;
import xxl.app.exception.InvalidCellRangeException;
// FIXME import classes

/**
 * Cut command.
 */
class DoCut extends Command<Spreadsheet> {

  DoCut(Spreadsheet receiver) {
    super(Label.CUT, receiver);
    addStringField("range", Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String range = stringField("range");
    Range parsedRange = Range.buildRange(range);
    try {
      
      if (parsedRange.isRangeValid()) {
        // Call the copy method with the specified range
        _receiver.getSpreadsheet().copy(range);
        // Call the clear method with the specified range
        _receiver.getSpreadsheet().clear(range);

      }
            
      } catch (OutOfBoundsException e) {
            // If an out-of-bounds exception occurs, throw an InvalidCellRangeException
            throw new InvalidCellRangeException(range);
      }
  }
}
