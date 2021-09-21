package expression.operators;

import expression.generic.operations.Operations;
import expression.interfaces.UltimateExpressionGeneric;

public class Square<T> extends AbstractUnaryOperator<T>{
    public Square(final UltimateExpressionGeneric<T> argument, final Operations<T> operations) {
        super(argument, operations);
    }

    @Override
    protected T applyOperation(final T a) {
        return operations.square(a);
    }

    @Override
    protected String getSymbol() {
        return "square";
    }
}
