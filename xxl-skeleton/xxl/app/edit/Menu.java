package xxl.app.edit;

import xxl.core.Spreadsheet;

/**
 * Menu builder for editing operations.
 */
public class Menu extends pt.tecnico.uilib.menus.Menu {
  /**
   * Constructs a new menu for editing operations with the specified spreadsheet
   * instance.
   *
   * @param receiver The spreadsheet instance on which these editing operations
   *                 are performed.
   */
  public Menu(Spreadsheet receiver) {
    super(Label.TITLE, //
        new DoShow(receiver), //
        new DoInsert(receiver), //
        new DoCopy(receiver), //
        new DoDelete(receiver), //
        new DoCut(receiver), //
        new DoPaste(receiver), //
        new DoShowCutBuffer(receiver) //
    );
  }
}
