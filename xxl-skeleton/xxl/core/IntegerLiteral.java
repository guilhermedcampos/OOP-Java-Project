package xxl.core;

public class IntegerLiteral extends Literal {
    private int value;

    public IntegerLiteral(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String evaluate(){
        return "Integer";
    }
}
