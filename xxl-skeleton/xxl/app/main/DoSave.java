package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.forms.Field;
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
 * Save to a file under the current name (if unnamed, query for name).
 */
class DoSave extends Command<Calculator> {

  DoSave(Calculator receiver) {
    super(Label.SAVE, receiver);
  }
  
  @Override
  protected final void execute() {
      Spreadsheet spreadsheet = _receiver.getSpreadsheet();
      Form form = new Form("Save Spreadsheet");

      /*
      if (spreadsheet == null) {
          _display.popup(Message.newSaveAs()); 
          return;
      }
      */

      String fileName = spreadsheet.getName(); // Initialize it with spreadsheet name

      if (fileName == null) {
          form.addStringField("fileName", Message.newSaveAs());
          form.parse();
          fileName = form.stringField("fileName");

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
          _display.addLine("Error: " + e.getMessage());
          _display.display();
      }
  }
}