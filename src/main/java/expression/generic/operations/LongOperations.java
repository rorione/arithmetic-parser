package expression.generic.operations;

import expression.exceptions.DivisionByZeroException;

public class LongOperations implements Operations<Long> {

    @Override
    public Long add(final Long x, final Long y) {
        return x + y;
    }

    @Override
    public Long subtract(final Long x, final Long y) {
        return x - y;
    }

    @Override
    public Long divide(final Long x, final Long y) {
        checkDivisionByZero(x, y);
        return x / y;
    }

    private void checkDivisionByZero(final long a, final long b) {
        if (b == 0) {
            throw new DivisionByZeroException("Division by zero: " + a + "/" + b);
        }
    }

    @Override
    public Long multiply(final Long x, final Long y) {
        return x * y;
    }

    @Override
    public Long negate(final Long x) {
        return -x;
    }

    @Override
    public Long parseString(final String s) {
        return Long.parseLong(s);
    }

    @Override
    public String toString(final Long x) {
        return String.valueOf(x);
    }

    @Override
    public Long abs(final Long x) {
        return x < 0 ? negate(x) : x;
    }

    @Override
    public Long square(final Long x) {
        return multiply(x, x);
    }

    @Override
    public Long mod(final Long x, final Long y) {
        checkDivisionByZero(x, y);
        return x % y;
    }
}
