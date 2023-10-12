package xxl.app.exception;

import pt.tecnico.uilib.menus.CommandException;

/**
 * Thrown when an invalid cell range is used.
 */
public class InvalidCellRangeException extends CommandException {
    
    private final String _range;
  
    /**
     * Constructs a new InvalidCellRangeException with the specified range.
     *
     * @param range The invalid cell range that caused the exception.
     */
    public InvalidCellRangeException(String range) {
        super("A gama '" + range + "' é inválida.");
        _range = range;
    }

    /**
     * Retrieves the invalid cell range that caused this exception.
     *
     * @return The invalid cell range.
     */
    public final String getInvalidRange() {
        return _range;
    }
}

