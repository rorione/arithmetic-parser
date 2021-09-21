package expression.operators;

import expression.generic.operations.Operations;

public class Add<T> extends AbstractBinaryOperator<T> {

    public Add(final AbstractBinaryOperator.BinaryArguments<T> arguments, final Operations<T> operations) {
        super(arguments, operations);
    }

    @Override
    public String getSymbol() {
        return "+";
    }

    public T applyOperation(final T a, final T b) {
        return operations.add(a, b);
    }
}
