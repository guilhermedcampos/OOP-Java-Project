package xxl.app;

import pt.tecnico.uilib.Dialog;
import xxl.core.Calculator;
import xxl.core.exception.EvaluationException;
import xxl.core.exception.ImportFileException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.OutOfBoundsException;
import xxl.core.exception.UnrecognizedEntryException;

import java.io.IOException;

/**
 * The main class representing the spreadsheet's textual interface.
 */
public class App {

  /**
   * The entry point for the spreadsheet application.
   * 
   * @param args Command-line arguments (not used).
   */
  public static void main(String[] args) {
    try (var ui = Dialog.UI) {
      var receiver = Calculator.getCalculator();
      String datafile = System.getProperty("import");
      if (datafile != null) {
        try {
          receiver.importFile(datafile);
        } catch (ImportFileException e) {
          e.printStackTrace();
        }
      }
      (new xxl.app.main.Menu(receiver)).open();
    }
  }
}
