package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Represents an abstract binary function that operates on two content objects.
 */
public abstract class BinaryFunction extends Function {

    /**
     * The first content argument of the binary function.
     */
    protected Content _arg1;

    /**
     * The second content argument of the binary function.
     */
    protected Content _arg2;

    /**
     * Constructs a binary function with a specified name and two content arguments.
     *
     * @param name the name of the binary function.
     * @param arg1 the first content argument.
     * @param arg2 the second content argument.
     */
    public BinaryFunction(String name, Content arg1, Content arg2) {
        super(name);
        _arg1 = arg1;
        _arg2 = arg2;

        if (_arg1.getConnectedCell() != null) {
            _arg1.getConnectedCell().addObserver(this);
            _arg1.setIsObserving(true);
        }
        if (arg2.getConnectedCell() != null) {
            _arg2.getConnectedCell().addObserver(this);
            _arg2.setIsObserving(true);
        }
        update();
    }

    public void stopObserving() {
        if (_arg1.getConnectedCell() != null) {
            _arg1.getConnectedCell().removeObserver(this);
            _arg1.setIsObserving(false);
        }
        if (_arg2.getConnectedCell() != null) {
            _arg2.getConnectedCell().removeObserver(this);
            _arg2.setIsObserving(false);
        }
    }

    /**
     * Computes the result of the binary function.
     *
     * @return the result of the binary function as a Literal.
     */
    public abstract void compute();

    public String cleanArgument(String arg) {
        if (arg.contains("=")) {
            // If there's an '=', keep everything after it
            int equalsIndex = arg.indexOf('=');
            return arg.substring(equalsIndex + 1);
        } else {
            // If no '=', or if there's a '#VALUE', return the argument as is
            return arg;
        }
    }

    /**
     * Returns a string representation of the binary function.
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