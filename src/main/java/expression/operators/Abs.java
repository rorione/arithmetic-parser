package expression.operators;

import expression.generic.operations.Operations;
import expression.interfaces.UltimateExpressionGeneric;

public class Abs<T> extends AbstractUnaryOperator<T> {


    public Abs(final UltimateExpressionGeneric<T> argument, final Operations<T> operations) {
        super(argument, operations);
    }

    @Override
    protected T applyOperation(final T a) {
        return operations.abs(a);
    }

    @Override
    protected String getSymbol() {
        return "abs";
    }
}
