package expression.literal;

import expression.interfaces.UltimateExpressionGeneric;

public class Variable<T> implements UltimateExpressionGeneric<T> {
    private final String value;

    public Variable(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public T evaluate(final T x, final T y, final T z) {
        return switch (value) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }

    public String getValue() {
        return value;
    }
}