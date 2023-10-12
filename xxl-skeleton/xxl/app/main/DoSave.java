package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;
import xxl.core.Spreadsheet;
import xxl.core.exception.MissingFileAssociationException;
import xxl.app.main.Message;
import java.io.Serial;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Command to save the current state to a file under the current name or prompt
 * for a name if unnamed.
 */
class DoSave extends Command<Calculator> {

    /**
     * Constructs a new instance of the DoSave command.
     *
     * @param receiver The calculator instance to which this command is attached.
     */
    DoSave(Calculator receiver) {
        super(Label.SAVE, receiver, xxl -> xxl.getSpreadsheet() != null);
    }

    /**
     * Executes the "Save" command, which saves the current state to a file under
     * the current name or prompts for a name if unnamed.
     */
    @Override
    protected final void execute() throws CommandException {
        // Get the current spreadsheet from the calculator
        Spreadsheet spreadsheet = _receiver.getSpreadsheet();

        // see if spreadsheet was changed, if not, do nothing
        if (spreadsheet.isChanged() == false) {
            return;
        }

        // if spreadsheet was changed, save file
        try {
            // Attempt to retrieve the file name associated with the spreadsheet
            if (spreadsheet.getName() == null) {
                addStringField("fileName", Message.newSaveAs());
                String fileName = stringField("fileName");
                _receiver.saveAs(fileName);
                // after saving, set changes back to false
                _receiver.getSpreadsheet().setChange(false);
            } else {
                String fileName = spreadsheet.getName();
                _receiver.save();
                // after saving, set changes back to false
                _receiver.getSpreadsheet().setChange(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
