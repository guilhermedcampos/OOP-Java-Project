package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;
import xxl.core.Spreadsheet;
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
    protected final void execute() {
        // Get the current spreadsheet from the calculator
        Spreadsheet spreadsheet = _receiver.getSpreadsheet();

        // Create a form for saving
        Form form = new Form("Save File");

        // Attempt to retrieve the file name associated with the spreadsheet
        String fileName = spreadsheet.getName();

        // If the file name is not set, prompt the user to provide a file name
        if (fileName == null) {
            form.addStringField("fileName", Message.newSaveAs());
            form.parse();
            fileName = form.stringField("fileName");

            // Set the spreadsheet's name to the provided file name
            spreadsheet.setName(fileName);
        }

        try {
            // Serialize and save the spreadsheet to a file with the specified name
            try (FileOutputStream fileOut = new FileOutputStream(fileName + ".ser");
                    ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
                objectOut.writeObject(spreadsheet);
            } catch (IOException e) {
                // Display an error message if there is a problem saving the file
                _display.popup(Message.problemOpeningFile(e));
            }
        } catch (Exception e) {
            // Display a generic error message in case of any other exceptions
            _display.addLine("Error: " + e.getMessage());
            _display.display();
        }
    }
}
