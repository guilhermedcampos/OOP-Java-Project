package xxl.app.search;

import xxl.core.Spreadsheet;

/**
 * Menu builder for search operations within the spreadsheet application.
 */
public class Menu extends pt.tecnico.uilib.menus.Menu {

    /**
     * Constructs a new instance of the Menu class for search operations within the spreadsheet application.
     *
     * @param receiver The spreadsheet instance to which this menu is attached.
     */
    public Menu(Spreadsheet receiver) {
        super(Label.TITLE, //
              new DoShowValues(receiver), //
              new DoShowFunctions(receiver) //
              );
    }
}
