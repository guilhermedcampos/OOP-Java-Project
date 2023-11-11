package xxl.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;

/**
 * Command for opening the search menu.
 */
class DoOpenSearchMenu extends Command<Calculator> {


    /**
     * Constructs a new DoOpenSearchMenu command with the specified receiver.
     *
     * @param receiver The calculator instance to associate with this command.
     */
    DoOpenSearchMenu(Calculator receiver) {
        super(Label.MENU_SEARCH, receiver, r -> r.getSpreadsheet() != null);
    }

    /**
     * Executes the DoOpenSearchMenu command, opening the search menu associated with the calculator's spreadsheet.
     *
     * @throws CommandException if an error occurs during command execution.
     */
    @Override
    protected final void execute() throws CommandException {
        (new xxl.app.search.Menu(_receiver.getSpreadsheet())).open();
    }
}
