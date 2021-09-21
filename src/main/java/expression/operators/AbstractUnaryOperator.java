package expression.operators;

import expression.interfaces.UltimateExpressionGeneric;
import expression.generic.operations.Operations;

public abstract class AbstractUnaryOperator<T> implements UltimateExpressionGeneric<T> {
    private final UltimateExpressionGeneric<T> argument;
    protected final Operations<T> operations;

    protected AbstractUnaryOperator(final UltimateExpressionGeneric<T> argument,
                                    final Operations<T> operations) {
        this.argument = argument;
        this.operations = operations;
    }

    @Override
    public T evaluate(final T x, final T y, final T z) {
        return applyOperation(argument.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return String.format("(%s %s)", getSymbol(), argument.toString());
    }

    protected abstract T applyOperation(final T a);

    protected abstract String getSymbol();
}
