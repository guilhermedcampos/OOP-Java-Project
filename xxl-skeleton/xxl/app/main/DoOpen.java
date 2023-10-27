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

  /**
   * Constructs a new instance of the "DoOpen" command.
   *
   * @param receiver The calculator instance to which this command is attached.
   */
  DoOpen(Calculator receiver) {
    super(Label.OPEN, receiver);
    addStringField("fileName", Message.openFile());
  }

  /**
   * Executes the "Open" command, allowing the user to open an existing file.
   *
   * @throws CommandException If an error occurs during command execution.
   */
  @Override
  protected final void execute() throws CommandException {
    

    // Check if a file is currently opened
    if (_receiver.getSpreadsheet() != null) {
      
      // Check if it's been changed
      if (_receiver.getSpreadsheet().isChanged()) {

        // If it's been changed, ask to save before exiting
        boolean bool = Form.confirm(Message.saveBeforeExit());
        if (bool) {
          DoSave doSaveCommand = new DoSave(_receiver);
          doSaveCommand.performCommand();
        }
      }
    }

    String fileName = stringField("fileName");
    try {
      _receiver.load(fileName);
    } catch (UnavailableFileException e) {
      throw new FileOpenFailedException(e);
    }
  }
}