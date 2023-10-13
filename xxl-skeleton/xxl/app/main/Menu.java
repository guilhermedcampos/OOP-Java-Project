package xxl.app.main;

import xxl.core.Calculator;

/**
 * Menu builder for the calculator application.
 */
public class Menu extends pt.tecnico.uilib.menus.Menu {

    /**
     * Constructs a new instance of the Menu class for the calculator application.
     *
     * @param receiver The calculator instance to which this menu is attached.
     */
    public Menu(Calculator receiver) {
        super(Label.TITLE, //
              new DoNew(receiver), //
              new DoOpen(receiver), //
              new DoSave(receiver), //
              new DoOpenEditMenu(receiver), //
              new DoOpenSearchMenu(receiver) //
              );
    }
}
