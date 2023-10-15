package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Cell;
import xxl.core.Range;
import xxl.core.Spreadsheet;
import xxl.core.Null;
import xxl.core.exception.OutOfBoundsException;

/**
 * Delete command.
 */
class DoDelete extends Command<Spreadsheet> {

  DoDelete(Spreadsheet receiver) {
    super(Label.DELETE, receiver);
    addStringField("range", Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String range = stringField("range");
        try {
            // Attempt to parse the given range string into a range object
            Range parsedRange = Range.buildRange(range);
            
            if (parsedRange.isRangeValid()) {
                // Traverse the cells within the range and display their content
                Cell[] cells = parsedRange.traverse();
                for (Cell cell : cells) {
                  _receiver.getSpreadsheet().insert(cell.getRow(),cell.getCol(), new Null());
                }
            }
        } catch (OutOfBoundsException e) {
            // If an out-of-bounds exception occurs, throw an InvalidCellRangeException
            throw new InvalidCellRangeException(range);
        }
    }
  }

