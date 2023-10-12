package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Calculator;
import xxl.core.Spreadsheet;
import xxl.core.exception.OutOfBoundsException;
import xxl.core.Cell;
// FIXME import classes

/**
 * Class for searching functions.
 */
class DoShow extends Command<Spreadsheet> {

  DoShow(Spreadsheet receiver) {
    super(Label.SHOW, receiver);
    addStringField("range", Message.address());
  }

  @Override
  protected final void execute() throws CommandException {
    String range = stringField("range");
    String[] rangeCoords = range.split(":");
    try {
      if (rangeCoords.length == 2) {
        // This is a range
        String[] startCoords = rangeCoords[0].split(";");
        String[] endCoords = rangeCoords[1].split(";");

        if (startCoords.length == 2 && endCoords.length == 2) {
          int startRow = Integer.parseInt(startCoords[0]);
          int startCol = Integer.parseInt(startCoords[1]);
          int endRow = Integer.parseInt(endCoords[0]);
          int endCol = Integer.parseInt(endCoords[1]);

          // Iterate through the range and display cell contents
          for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
              // Print cell and content to String
              _display
                  .addLine(_receiver.getCell(row, col).toString() + "|" + _receiver.getContentAt(row, col).toString());
            }
          }
        }
      } else {
        // This is a single cell
        String[] cellCoords = range.split(";");
        if (cellCoords.length == 2) {
          int cellRow = Integer.parseInt(cellCoords[0]);
          int cellCol = Integer.parseInt(cellCoords[1]);

          // Display the content of the single cell
          _display.addLine(_receiver.getCell(cellRow, cellCol).toString() + "|"
              + _receiver.getContentAt(cellRow, cellCol).toString());
        }
      }

      // Display the results
      _display.display();
    } catch (OutOfBoundsException e) {
      throw new InvalidCellRangeException(range);
    }
  }
}
