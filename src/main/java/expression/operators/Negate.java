package expression.operators;

import expression.interfaces.UltimateExpressionGeneric;
import expression.generic.operations.Operations;

public class Negate<T> extends AbstractUnaryOperator<T> {
    public Negate(final UltimateExpressionGeneric<T> element, final Operations<T> operations) {
        super(element, operations);
    }

    @Override
    public String getSymbol() {
        return "-";
    }

    @Override
    public T applyOperation(final T a) {
        return operations.negate(a);
    }
}
