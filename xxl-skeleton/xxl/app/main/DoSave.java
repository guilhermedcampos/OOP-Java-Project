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
        super(Label.SAVE, receiver);
    }

    /**
 * Executes the "Save" command, which saves the current state to a file under
 * the current name or prompts for a name if unnamed.
 */
    @Override
    protected final void execute() throws CommandException{
        // Get the current spreadsheet from the calculator
        Spreadsheet spreadsheet = _receiver.getSpreadsheet();
        
        try {
        // Attempt to retrieve the file name associated with the spreadsheet
        String fileName = spreadsheet.getName();
        if (fileName == null) {
            addStringField("fileName", Message.newSaveAs());
            _receiver.saveAs(fileName);
        } else {
            // if changed, save
            _receiver.save();
        }  
        } catch (MissingFileAssociationException | IOException e) {
            e.printStackTrace();
        }

    }
}
