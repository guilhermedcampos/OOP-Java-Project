package xxl.core;

import xxl.core.exception.EvaluationException;

/**
 * Represents a generic function in the spreadsheet.
 */
public abstract class Function extends Content {
    private String _name;
    protected Literal _value;

    public Function(String name) {
        _name = name;
    }

    public void accept(ContentVisitor visitor) {
        visitor.visit(this);
    }

    protected String getName() {
        return _name;
    }

    public abstract void compute();

    public abstract void startObserving();

    public abstract void stopObserving();

    public String asString() throws EvaluationException {
        return _value.asString();
    }

    public int asInt() throws EvaluationException {
        return _value.asInt();
    }

    public Literal value() {
        return _value;
    }

    protected String cleanStringAfterFirstEquals(String input) {
        int firstEqualsIndex = input.indexOf('=');
        if (firstEqualsIndex >= 0) {
            String beforeFirstEquals = input.substring(0, firstEqualsIndex + 1);
            String afterFirstEquals = input.substring(firstEqualsIndex + 1);

            afterFirstEquals = afterFirstEquals.replaceAll("\\([^=]+=", "(");
            afterFirstEquals = afterFirstEquals.replaceAll(",[^=]+=", ",");

            return beforeFirstEquals + afterFirstEquals;
        }
        return input;
    }
}