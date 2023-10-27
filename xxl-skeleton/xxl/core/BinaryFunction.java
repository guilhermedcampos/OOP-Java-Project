package xxl.core;

/**
 * Represents an abstract binary function that operates on two content objects.
 */
public abstract class BinaryFunction extends Function {
    protected Content _arg1;
    protected Content _arg2;

    public BinaryFunction(String name, Content arg1, Content arg2) {
        super(name);
        _arg1 = arg1;
        _arg2 = arg2;
        startObserving();
    }

    public void startObserving() {
        _arg1.setIsObserving(true, this);
        _arg2.setIsObserving(true, this);
        update();
    }

    public void stopObserving() {
        _arg1.setIsObserving(false, this);
        _arg2.setIsObserving(false, this);
    }

    public abstract void compute();

    public String cleanArgument(String arg) {
        if (arg.contains("=")) {
            int equalsIndex = arg.indexOf('=');
            return arg.substring(equalsIndex + 1);
        } else {
            return arg;
        }
    }

    @Override
    public String toString() {
        String result = _value.toString() + "=" + getName() + "(" + cleanArgument(_arg1.toString()) + ","
                + cleanArgument(_arg2.toString()) + ")";
        return result;
    }
}
