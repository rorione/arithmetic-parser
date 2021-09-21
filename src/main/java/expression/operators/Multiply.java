package expression.operators;

import expression.generic.operations.Operations;

public class Multiply<T> extends AbstractBinaryOperator<T> {
    public Multiply(final AbstractBinaryOperator.BinaryArguments<T> arguments, final Operations<T> operations) {
        super(arguments, operations);
    }

    @Override
    public String getSymbol() {
        return "*";
    }

    @Override
    public T applyOperation(final T a, final T b) {
        return operations.multiply(a, b);
    }
}
