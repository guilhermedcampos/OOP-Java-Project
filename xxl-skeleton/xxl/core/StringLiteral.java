package xxl.core;

public class StringLiteral extends Literal {
    private String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public Content evaluate() {
        return this; // StringLiteral is already in its evaluated form
    }
}
