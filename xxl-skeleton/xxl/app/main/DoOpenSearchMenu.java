package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;

/**
 * Command for opening the search menu.
 */
class DoOpenSearchMenu extends Command<Calculator> {

    /**
     * Constructs a new instance of the DoOpenSearchMenu command.
     *
     * @param receiver The calculator instance to which this command is attached.
     */
    DoOpenSearchMenu(Calculator receiver) {
        super(Label.MENU_SEARCH, receiver, r -> r.getSpreadsheet() != null);
    }

    /**
     * Executes the "Open Search Menu" command, opening the search menu for the current spreadsheet.
     *
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    protected final void execute() throws CommandException {
        (new xxl.app.search.Menu(_receiver.getSpreadsheet())).open();
    }
}
