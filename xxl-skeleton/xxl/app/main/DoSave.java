package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.forms.Field;
import xxl.app.main.Message;
import xxl.core.Spreadsheet;
import java.io.Serial;
import java.io.Serializable;
import java.io.IOException;

/**
 * Save to a file under the current name (if unnamed, query for name).
 */
class DoSave extends Command<Calculator> {

  DoSave(Calculator receiver) {
    super(Label.SAVE, receiver, xxl -> xxl.getSpreadsheet() != null);
  }
  
  @Override
  protected final void execute() {
      Spreadsheet spreadsheet = receiver.getSpreadsheet();

      if (spreadsheet == null) {
          display(Message.newSaveAs()); // Prompt for a name if there's no spreadsheet
          return;
      }

      String fileName = null;
      if (spreadsheet.getName() == null) {
          Form form = new Form("Save Spreadsheet");
          Field<String> fileNameField = form.addStringField("fileName", "Enter file name: ");
          form.parse();

          // Get the file name entered by the user
          fileName = fileNameField.value();
      } else {
          fileName = spreadsheet.getName();
      }

      try {
          // Serialize and save the spreadsheet with the specified file name in the current directory
          try (FileOutputStream fileOut = new FileOutputStream(fileName + ".ser");
              ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
              objectOut.writeObject(spreadsheet);
              display("Spreadsheet saved to: " + fileName + ".ser");
          } catch (IOException e) {
              display(Message.problemOpeningFile(e)); // Use problemOpeningFile message for IO exceptions
          }
      } catch (Exception e) {
          display("Error: " + e.getMessage());
      }
  }
}