package xxl.core;

/**
 * Represents a literal value in a spreadsheet.
 */
public abstract class Literal extends Content {
    protected Literal value() {
        return this;
    }

    public void compute() {

    }

    public void startObserving() {

    }

    public void stopObserving() {

    }

    public abstract void accept(ContentVisitor visitor);
}