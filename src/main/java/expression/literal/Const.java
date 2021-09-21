package expression.literal;

import expression.generic.operations.Operations;
import expression.interfaces.UltimateExpressionGeneric;

public class Const<T> implements UltimateExpressionGeneric<T> {
    private final T value;
    final Operations<T> operations;

    public Const(final T value, final Operations<T> operations) {
        this.operations = operations;
        this.value = value;
    }

    @Override
    public String toString() {
        return operations.toString(value);
    }

    public T getValue() {
        return value;
    }

    @Override
    public T evaluate(final T x, final T y, final T z) {
        return getValue();
    }
}
