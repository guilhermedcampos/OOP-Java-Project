package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Spreadsheet;
import xxl.core.CutBuffer;

/**
 * Show cut buffer command.
 */
class DoShowCutBuffer extends Command<Spreadsheet> {

  /**
   * Constructs a new instance of the Show Cut Buffer command.
   *
   * @param receiver The spreadsheet on which the Show Cut Buffer command operates.
   */
  DoShowCutBuffer(Spreadsheet receiver) {
    super(Label.SHOW_CUT_BUFFER, receiver);
  }

  /**
   * Executes the Show Cut Buffer command, displaying the contents of the cut buffer.
   * The displayed content includes cell addresses and their corresponding content.
   */
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
        col++;
      } else {
        row++;
      }
    }
  }
}
