package xxl.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;

/**
 * Command for opening the edit menu.
 */
class DoOpenEditMenu extends Command<Calculator> {

  /**
   * Constructs a new DoOpenEditMenu command with the specified receiver.
   *
   * @param receiver The calculator instance to associate with this command.
   */
  DoOpenEditMenu(Calculator receiver) {
    super(Label.MENU_CALC, receiver, r -> r.getSpreadsheet() != null);
  }

  /**
   * Executes the DoOpenEditMenu command, opening the edit menu associated with the calculator's spreadsheet.
   *
   * @throws CommandException if an error occurs during command execution.
   */
  @Override
  protected final void execute() throws CommandException {
    (new xxl.app.edit.Menu(_receiver.getSpreadsheet())).open();
  }
}
