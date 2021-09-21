package expression.operators;

import expression.generic.operations.Operations;

public class Mod<T> extends AbstractBinaryOperator<T>{

    public Mod(final AbstractBinaryOperator.BinaryArguments<T> arguments, final Operations<T> operations) {
        super(arguments, operations);
    }

    @Override
    protected String getSymbol() {
        return "mod";
    }

    @Override
    protected T applyOperation(final T a, final T b) {
        return operations.mod(a, b);
    }
}
