package xxl.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;

/**
 * Command for opening the edit menu.
 */
class DoOpenEditMenu extends Command<Calculator> {

  /**
   * Constructs a new instance of the `DoOpenEditMenu` command.
   *
   * @param receiver The calculator instance to which this command is attached.
   */
  DoOpenEditMenu(Calculator receiver) {
    super(Label.MENU_CALC, receiver, r -> r.getSpreadsheet() != null);
  }

  /**
   * Executes the "Open Edit Menu" command, opening the edit menu for the current
   * spreadsheet.
   *
   * @throws CommandException If an error occurs during command execution.
   */
  @Override
  protected final void execute() throws CommandException {
    (new xxl.app.edit.Menu(_receiver.getSpreadsheet())).open();
  }
}
