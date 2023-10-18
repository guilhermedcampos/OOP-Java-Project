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
    for (int i = 0; i < cutBuffer.getCells().length; i++) {
      _display.addLine(cutBuffer.getCells()[i].toString() + "|" + cutBuffer.getContent(i).toString());

    }
  }
}
