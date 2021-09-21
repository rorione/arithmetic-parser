package expression.generic.operations;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public class ShortOperations implements Operations<Short> {

    @Override
    public Short add(final Short x, final Short y) {
        return (short)(x + y);
    }

    @Override
    public Short subtract(final Short x, final Short y) {
        return (short)(x - y);
    }

    @Override
    public Short divide(final Short x, final Short y) {
        checkDivisionByZero(x, y);
        return (short) (x / y);
    }

    private void checkDivisionByZero(final short a, final short b) {
        if (b == 0) {
            throw new DivisionByZeroException("Division by zero: " + a + "/" + b);
        }
    }

    @Override
    public Short multiply(final Short x, final Short y) {
        return (short)(x * y);
    }

    @Override
    public Short negate(final Short x) {
        return (short)-x;
    }

    @Override
    public Short parseString(final String s) {
        return (short) Integer.parseInt(s);
    }

    @Override
    public String toString(final Short x) {
        return String.valueOf(x);
    }

    @Override
    public Short abs(final Short x) {
        return x < 0 ? negate(x) : x;
    }

    @Override
    public Short square(final Short x) {
        return multiply(x, x);
    }

    @Override
    public Short mod(final Short x, final Short y) {
        checkDivisionByZero(x, y);
        return (short)(x % y);
    }
}
