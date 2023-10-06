package xxl.core;

public class StringLiteral extends Literal {
    private String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    public String getValue() {
        return "String";
    }
}
