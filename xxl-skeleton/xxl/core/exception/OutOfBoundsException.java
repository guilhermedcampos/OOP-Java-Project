package xxl.core.exception;

/**
 * Represents an exception that is thrown when an operation exceeds valid bounds.
 */
public class OutOfBoundsException extends Exception {
    
    /**
     * Constructs an `OutOfBoundsException` with the specified detail message.
     *
     * @param message The detail message describing the out-of-bounds error.
     */
    public OutOfBoundsException(String message) {
        super(message);
    }
}
