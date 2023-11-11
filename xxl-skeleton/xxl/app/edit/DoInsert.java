package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Cell;
import xxl.core.Range;
import xxl.core.Spreadsheet;
import xxl.core.Content;
import xxl.core.Parser;
import xxl.core.exception.OutOfBoundsException;
import xxl.core.exception.UnrecognizedEntryException;
import xxl.app.exception.UnknownFunctionException;
import xxl.app.exception.InvalidCellRangeException;

/**
 * Class for inserting data.
 */
class DoInsert extends Command<Spreadsheet> {

  /**
   * Constructs a new Insert command with the specified receiver (spreadsheet).
   *
   * @param receiver the spreadsheet on which the command operates.
   */
  DoInsert(Spreadsheet receiver) {
    super(Label.INSERT, receiver);
    addStringField("range", Message.address());
    addStringField("content", Message.contents());
  }

  /**
   * Executes the Insert command, inserting the specified content into the specified range.
   *
   * @throws CommandException if an error occurs during command execution.
   */
  @Override
  protected final void execute() throws CommandException {
    String range = stringField("range");
    String contents = stringField("content");
    Parser parser = new Parser();

    try {
      Range parsedRange = Range.buildRange(range);

      if (parsedRange.isRangeValid()) {
        Content content = parser.parseContent(contents);
        Cell[] cells = parsedRange.traverse();
        for (Cell cell : cells) {
          _receiver.getSpreadsheet().insert(cell.getRow(), cell.getCol(), content);
        }
      }
    } catch (UnrecognizedEntryException e) {
      if (e.getEntrySpecification().startsWith("Função inválida: ")) {
        String funcName = e.getEntrySpecification().substring("Função inválida: ".length());
        throw new UnknownFunctionException(funcName);
      }
    } catch (OutOfBoundsException e) {
      throw new InvalidCellRangeException(range);
    }
  }
}
