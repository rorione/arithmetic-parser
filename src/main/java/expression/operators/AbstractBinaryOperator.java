package expression.operators;

import expression.interfaces.UltimateExpressionGeneric;
import expression.generic.operations.Operations;

public abstract class AbstractBinaryOperator<T> implements UltimateExpressionGeneric<T> {
    private final BinaryArguments<T> arguments;
    protected final Operations<T> operations;

    protected AbstractBinaryOperator(final BinaryArguments<T> arguments,
                                     final Operations<T> operations) {
        this.arguments = arguments;
        this.operations = operations;
    }

    public T evaluate(final T x, final T y, final T z) {
        return applyOperation(arguments.firstElement.evaluate(x, y, z), arguments.secondElement.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", arguments.firstElement, getSymbol(), arguments.secondElement);
    }

    protected abstract String getSymbol();

    protected abstract T applyOperation(final T a, final T b);

    public static class BinaryArguments<T> {
        private final UltimateExpressionGeneric<T> firstElement;
        private final UltimateExpressionGeneric<T> secondElement;

        public BinaryArguments(final UltimateExpressionGeneric<T> firstElement,
                               final UltimateExpressionGeneric<T> secondElement) {
            this.firstElement = firstElement;
            this.secondElement = secondElement;
        }
    }
}
