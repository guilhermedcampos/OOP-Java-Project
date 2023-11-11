package xxl.core;

/*
 * Represents an observer in the spreadsheet system.
 */
public interface Observer {

    /**
     * Notifies the observer about changes in the spreadsheet.
     */
    void update();
}
