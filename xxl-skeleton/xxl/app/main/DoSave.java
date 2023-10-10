package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.forms.Field;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Display;
import xxl.core.Calculator;
import xxl.core.Spreadsheet;
import xxl.app.main.Message;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Save the current spreadsheet to a file.
 */
class DoSave extends Command<Calculator> {

  DoSave(Calculator receiver) {
    super(Label.SAVE, receiver);
  }

  @Override
  protected void execute() throws CommandException {
    Spreadsheet spreadsheet = _receiver.getSpreadsheet();

    if (spreadsheet == null) {
      _display.popup("Sem ficheiro para guardar."); // Prompt for a name if there's no spreadsheet
      return;
    }

    String fileName = null;
    if (spreadsheet.getName() == null) {
      Form form = new Form();
      Field<String> fileNameField = form.addStringField("fileName", Message.saveAs());
      form.parse();

      // Get the file name entered by the user using the key
      fileName = form.stringField("fileName"); // Use the key to obtain the value
      spreadsheet.setName(fileName); // Set the spreadsheet name
    } else {
      fileName = spreadsheet.getName();
    }

    try {
      // Serialize and save the spreadsheet with the specified file name in the current directory
      try (FileOutputStream fileOut = new FileOutputStream(fileName + ".ser");
           ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
        objectOut.writeObject(spreadsheet);
      } catch (IOException e) {
        _display.popup(Message.problemOpeningFile(e)); // Use problemOpeningFile message for IO exceptions
      }
    } catch (Exception e) {
      _display.popup("Error: " + e.getMessage());
    }
  }
}
