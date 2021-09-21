package expression.operators;

import expression.generic.operations.Operations;

public class Divide<T> extends AbstractBinaryOperator<T> {
    public Divide(final AbstractBinaryOperator.BinaryArguments<T> arguments, final Operations<T> operations) {
        super(arguments, operations);
    }

    @Override
    public String getSymbol() {
        return "/";
    }

    @Override
    public T applyOperation(final T a, final T b) {
       return operations.divide(a, b);
    }

}
