package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;
import xxl.core.Spreadsheet;
import xxl.core.exception.UnavailableFileException;
import xxl.app.exception.FileOpenFailedException;

import java.io.Serial;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import xxl.app.main.Message;
// FIXME import classes

/**
 * Open existing file.
 */
public class DoOpen extends Command<Calculator> {

  DoOpen(Calculator receiver) {
    super(Label.OPEN, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    Form form = new Form("Open File");
    form.addStringField("fileName", Message.openFile());
    form.parse();
    String fileName = form.stringField("fileName");

    try {
      _receiver.load(fileName);
    } catch (IOException | ClassNotFoundException | UnavailableFileException e) {
      _display.popup(Message.problemOpeningFile(e));
      throw new FileOpenFailedException(e);
    }
  }
}
