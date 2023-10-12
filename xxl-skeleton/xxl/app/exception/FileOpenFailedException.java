package xxl.app.exception;

import pt.tecnico.uilib.menus.CommandException;

/**
 * Exception for reporting general problems opening and processing files.
 */
public class FileOpenFailedException extends CommandException {
    
    /**
     * Constructs a new FileOpenFailedException with the specified cause.
     *
     * @param e The exception that caused the file open failure.
     */
    public FileOpenFailedException(Exception e) {
        super(Message.problemOpeningFile(e), e);
    }
}
