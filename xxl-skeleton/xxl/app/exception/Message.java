package xxl.app.exception;

/**
 * Messages for error reporting.
 */
interface Message {
    
    /**
     * Generates a message for reporting a problem opening a file.
     *
     * @param cause The exception that caused the file open failure.
     * @return A descriptive message for the file open problem.
     */
    static String problemOpeningFile(Exception cause) {
        return "Problema ao abrir ficheiro: " + cause.getMessage();
    }
}
