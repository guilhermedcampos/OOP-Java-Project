package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;

import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Calculator;
import xxl.core.Range;
import xxl.core.Spreadsheet;
import xxl.core.exception.OutOfBoundsException;
import xxl.core.Cell;

/**
 * Class for searching functions.
 */
class DoShow extends Command<Spreadsheet> {

    /**
     * Constructs a new DoShow command.
     *
     * @param receiver The spreadsheet instance on which this command operates.
     */
    DoShow(Spreadsheet receiver) {
        super(Label.SHOW, receiver);
        addStringField("range", Message.address());
    }

    /**
     * Executes the command to display the contents of a specified cell range.
     *
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    protected final void execute() throws CommandException {
        String range = stringField("range");
        try {
            // Attempt to parse the given range string into a range object
            Range parsedRange = Range.buildRange(range);

            // Traverse the cells within the range and display their content
            Cell[] cells = parsedRange.traverse();
            for (Cell cell : cells) {
                _display.addLine(cell.toString() + "|" + _receiver.getContentAt(cell.getRow(), cell.getCol()).toString());
            }

            _display.display();
        } catch (OutOfBoundsException e) {
            // If an out-of-bounds exception occurs, throw an InvalidCellRangeException
            throw new InvalidCellRangeException(range);
        }
    }
}