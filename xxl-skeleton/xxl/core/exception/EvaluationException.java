package xxl.core.exception;

/**
 * Represents an evaluation error.
 */
public class EvaluationException extends Exception {

    /**
     * Constructs an `EvaluationException` with the specified detail message.
     *
     * @param message The error message.
     */
    public EvaluationException(String message) {
        super(message);
    }

    /**
     * Constructs an `EvaluationException` with the specified detail message and a cause.
     *
     * @param message The error message.
     * @param cause   The cause of the error.
     */
    public EvaluationException(String message, Throwable cause) {
        super(message, cause);
    }
}


