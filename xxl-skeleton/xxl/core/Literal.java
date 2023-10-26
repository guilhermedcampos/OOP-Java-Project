package xxl.core;

/**
 * Represents a literal value in a spreadsheet.
 */
public abstract class Literal extends Content {

    /**
     * Gets the value of the literal.
     *
     * @return the value of the literal.
     */
    protected Literal value() {
        return this;
    }

    public void update() {

    }

    public abstract void accept(ContentVisitor visitor);
}