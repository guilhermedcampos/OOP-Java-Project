package xxl.app.main;

import xxl.core.Calculator;

/**
 * Menu builder for the calculator application.
 */
public class Menu extends pt.tecnico.uilib.menus.Menu {
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
