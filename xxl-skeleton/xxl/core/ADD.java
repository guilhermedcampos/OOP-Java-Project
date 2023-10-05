package xxl.core.function;

import xxl.core.Cell;
import xxl.core.Content;
import xxl.core.exception.UnrecognizedEntryException;

public class ADD extends Function {
    public ADD(Cell... args) {
        super("ADD", args);
    }

    @Override
    public Content evaluate() throws UnrecognizedEntryException {
        if (args.length < 2) {
            throw new UnrecognizedEntryException("ADD function requires at least two arguments.");
        }

        double result = 0.0;

        for (Cell arg : args) {
            if (arg != null) {
                Content content = arg.getContent();
                try {
                    content.evaluate();
                    if (content instanceof Literal) {
                        double value = ((Literal) content).getValue();
                        result += value;
                    } else {
                        throw new UnrecognizedEntryException("ADD function only accepts literal values as arguments.");
                    }
                } catch (UnrecognizedEntryException e) {
                    throw new UnrecognizedEntryException("Error evaluating argument: " + e.getMessage(), e);
                }
            }
        }

        return new Literal(result);
    }
}
