package xxl.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;
import xxl.core.Spreadsheet;
import xxl.core.exception.MissingFileAssociationException;
import xxl.app.exception.FileOpenFailedException;
import java.io.IOException;

/**
 * Command to save the current state to a file under the current name or prompt
 * for a name if unnamed.
 */
class DoSave extends Command<Calculator> {

    DoSave(Calculator receiver) {
        super(Label.SAVE, receiver, xxl -> xxl.getSpreadsheet() != null);
    }

    @Override
    protected final void execute() throws CommandException {
        Spreadsheet spreadsheet = _receiver.getSpreadsheet();

        if (!spreadsheet.isChanged()) {
            return;
        }

        try {
            if (spreadsheet.getName() == null) {
                addStringField("fileName", Message.newSaveAs());
                String fileName = stringField("fileName");
                _receiver.saveAs(fileName);
            } else {
                String fileName = spreadsheet.getName();
                _receiver.save();
            }

            _receiver.getSpreadsheet().setChange(false);
        } catch (MissingFileAssociationException | IOException e) {
            throw new FileOpenFailedException(e);
        }
    }
}
