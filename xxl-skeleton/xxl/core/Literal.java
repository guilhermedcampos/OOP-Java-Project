package xxl.core;

/**
 * Represents a literal value in a spreadsheet.
 */
public abstract class Literal extends Content {

    /**
     * Returns the literal value itself.
     *
     * @return the literal value.
     */
    protected Literal value() {
        return this;
    }

    /**
     * Literal values do not require computation, as they represent constant values.
     */
    public void compute() {
        // Empty implementation for literal values.
    }

    /**
     * Literal values do not observe changes as they are constant.
     */
    public void startObserving() {
        // Empty implementation for literal values.
    }

    /**
     * Literal values do not observe changes as they are constant.
     */
    public void stopObserving() {
        // Empty implementation for literal values.
    }

    /**
     * Abstract method to be implemented by subclasses for accepting a ContentVisitor.
     *
     * @param visitor the ContentVisitor to be accepted.
     */
    public abstract void accept(ContentVisitor visitor);
}