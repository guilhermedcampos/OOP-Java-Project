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
    int col = 1;
    for (int i = 0; i < cutBuffer.getContents().length; i++) {
      _display.addLine("1;" + col++ + "|" + cutBuffer.getContent(i).toString());
    }
  }
}
