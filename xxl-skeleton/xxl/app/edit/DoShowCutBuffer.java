package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Spreadsheet;

import xxl.core.Cell;
import xxl.core.CutBuffer;

/**
 * Show cut buffer command.
 */
class DoShowCutBuffer extends Command<Spreadsheet> {

  DoShowCutBuffer(Spreadsheet receiver) {
    super(Label.SHOW_CUT_BUFFER, receiver);
  }

  @Override
  protected final void execute() {
    CutBuffer cutBuffer = _receiver.getCutBuffer();

    if (cutBuffer.getContents() == null) {
      return;
    }

    boolean variesInColumns = cutBuffer.variesInColumns();

    int col = 1;
    int row = 1;
    int maxCols = _receiver.getCols();

    for (int i = 0; i < cutBuffer.getContents().length; i++) {
      _display.addLine(row + ";" + col + "|" + cutBuffer.getContent(i).toString());

      if (variesInColumns) {
        // Increment the row when varying in columns
        col++;
      } else {
        // Increment the column when varying in rows
        row++;
      }
    }
  }
}
