package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.CommandException;

import xxl.core.Calculator;
import xxl.core.Spreadsheet;
import xxl.core.exception.UnavailableFileException;

import xxl.app.exception.FileOpenFailedException;
import xxl.app.main.Message;

import java.io.Serial;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Open existing file.
 */
public class DoOpen extends Command<Calculator> {

  DoOpen(Calculator receiver) {
    super(Label.OPEN, receiver);
    addStringField("fileName", Message.openFile());
  }

  @Override
  protected final void execute() throws CommandException {

    // see if a file is opened
    if (_receiver.getSpreadsheet() != null) {

      // see if its changed
      if (_receiver.getSpreadsheet().isChanged()) {

        // if its changed, ask to save before exit
        boolean bool = Form.confirm(Message.saveBeforeExit());
        if (bool == true) {
          DoSave doSaveCommand = new DoSave(_receiver);
          doSaveCommand.performCommand();
        }
      }
    }

    String fileName = stringField("fileName");
    try {
      _receiver.load(fileName);
    } catch (Exception e) {
      throw new FileOpenFailedException(e);
    }
  }
}
