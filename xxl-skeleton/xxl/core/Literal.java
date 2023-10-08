package xxl.core;
import xxl.core.exception.EvaluationException;

public abstract class Literal extends Content {

    protected Literal value() {
        return this;
    }
}
