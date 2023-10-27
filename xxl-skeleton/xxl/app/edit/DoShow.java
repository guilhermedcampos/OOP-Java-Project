package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Range;
import xxl.core.Spreadsheet;
import xxl.core.exception.OutOfBoundsException;
import xxl.core.Cell;

/**
 * Class for showing the spreadsheet in a given range.
 */
class DoShow extends Command<Spreadsheet> {

    DoShow(Spreadsheet receiver) {
        super(Label.SHOW, receiver);
        addStringField("range", Message.address());
    }

    @Override
    protected final void execute() throws CommandException {
        String range = stringField("range");
        try {
            Range parsedRange = Range.buildRange(range);

            if (parsedRange.isRangeValid()) {
                Cell[] cells = parsedRange.traverse();
                for (Cell cell : cells) {
                    _display.addLine(
                            cell.toString() + "|" + _receiver.getContentAt(cell.getRow(), cell.getCol()).toString());
                }
            }

            _display.display();
        } catch (OutOfBoundsException e) {
            throw new InvalidCellRangeException(range);
        }
    }
}
