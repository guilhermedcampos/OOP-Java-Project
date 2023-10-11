package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;
import xxl.core.Spreadsheet;
import xxl.core.Cell;
// FIXME import classes

/**
 * Class for searching functions.
 */
class DoShow extends Command<Spreadsheet> {

  DoShow(Spreadsheet receiver) {
    super(Label.SHOW, receiver);
    // FIXME add fields
  }
  
  @Override
  protected final void execute() throws CommandException {
    Spreadsheet spreadsheet = Calculator.getSpreadsheet();
    Cell[][] cells = spreadsheet.getCells();
    System.out.println("oi");
    for (Cell cell : cells) {
      System.out.println(cell.toString()+"|"+cell.getContent().toString());
    }

  }
}
