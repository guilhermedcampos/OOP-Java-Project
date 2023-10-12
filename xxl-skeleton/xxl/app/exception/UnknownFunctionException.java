package xxl.app.exception;

import pt.tecnico.uilib.menus.CommandException;

/**
 * Thrown when an unknown function is used.
 */
public class UnknownFunctionException extends CommandException {

  private final String _functionName;

  /**
   * Constructs an `UnknownFunctionException` with the given function name.
   *
   * @param functionName The name of the unknown function.
   */
  public UnknownFunctionException(String functionName) {
    super("A função '" + functionName + "' é desconhecida.");
    _functionName = functionName;
  }

  /**
   * Gets the name of the unknown function that caused the exception.
   *
   * @return The name of the unknown function.
   */
  public final String getFunctionName() {
    return _functionName;
  }
}
