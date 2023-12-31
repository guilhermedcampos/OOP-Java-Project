package xxl.core;

/**
 * Represents an abstract binary function that operates on two content objects.
 */
public abstract class BinaryFunction extends Function {

    /** The first argument for the binary function. */
    protected Content _arg1;

    /** The second argument for the binary function. */
    protected Content _arg2;

    /**
     * Constructs a binary operation with the specified name and content arguments.
     *
     * @param name the name of the binary function.
     * @param arg1 the first content object to be used as an argument.
     * @param arg2 the second content object to be used as an argument.
     */
    public BinaryFunction(String name, Content arg1, Content arg2) {
        super(name);
        _arg1 = arg1;
        _arg2 = arg2;
        startObserving();
    }

    /**
     * Starts observing changes in the content objects used as arguments.
     */
    public void startObserving() {
        _arg1.setIsObserving(true, this);
        _arg2.setIsObserving(true, this);
        update();
    }

    /**
     * Stops observing changes in the content objects used as arguments.
     */
    public void stopObserving() {
        _arg1.setIsObserving(false, this);
        _arg2.setIsObserving(false, this);
    }

    /**
     * Abstract method to be implemented by subclasses for computing the result of the binary function.
     */
    public abstract void compute();

    /**
     * Cleans up the argument string by removing any assignment operators.
     *
     * @param arg the argument string.
     * @return the cleaned-up argument string.
     */
    public String cleanArgument(String arg) {
        if (arg.contains("=")) {
            int equalsIndex = arg.indexOf('=');
            return arg.substring(equalsIndex + 1);
        } else {
            return arg;
        }
    }

    /**
     * Returns a string representation of the binary function in the format:
     * result = functionName(arg1, arg2)
     *
     * @return a string representation of the binary function.
     */
    @Override
    public String toString() {
        String result = _value.toString() + "=" + getName() + "(" + cleanArgument(_arg1.toString()) + ","
                + cleanArgument(_arg2.toString()) + ")";
        return result;
    }
}
