package expression.generic.operations;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public class IntegerOperations implements Operations<Integer> {
    final boolean overflowCheck;

    public IntegerOperations(final boolean overflowCheck) {
        this.overflowCheck = overflowCheck;
    }

    @Override
    public Integer add(final Integer x, final Integer y) {
        if (overflowCheck) {checkAddictionOverflow(x, y);}
        return x + y;
    }

    private void checkAddictionOverflow(final int a, final int b) {
        if ((a > 0 && Integer.MAX_VALUE - a < b) || (a < 0 && Integer.MIN_VALUE - a > b)) {
            throw new OverflowException("Addition overflow: " + a + " + " + b);
        }
    }

    @Override
    public Integer subtract(final Integer x, final Integer y) {
        if (overflowCheck) {checkSubtractionOverflow(x, y);}
        return x - y;
    }

    private void checkSubtractionOverflow(final int a, final int b) {
        if ((b < 0 && Integer.MAX_VALUE + b < a) || (b > 0 && Integer.MIN_VALUE + b > a)) {
            throw new OverflowException("Subtraction overflow: " + a + " - " + b);
        }
    }

    @Override
    public Integer divide(final Integer x, final Integer y) {
        checkDivisionByZero(x, y);
        if (overflowCheck) {checkDivisionOverflow(x, y);}
        return x / y;
    }

    private void checkDivisionByZero(final int a, final int b) {
        if (b == 0) {
            throw new DivisionByZeroException("Division by zero: " + a + "/" + b);
        }
    }

    private void checkDivisionOverflow(final int a, final int b) {
        if (b == -1 && a == Integer.MIN_VALUE) {
            throw new OverflowException("Division overflow: " + a + " / " + b);
        }
    }

    @Override
    public Integer multiply(final Integer x, final Integer y) {
        if (overflowCheck) {checkMultiplicationOverflow(x, y);}
        return x * y;
    }

    private void checkMultiplicationOverflow(final int a, final int b) {
        if ((a > 0) && (b > Integer.MAX_VALUE / a || b < Integer.MIN_VALUE / a)
                || (a < -1) && (b > Integer.MIN_VALUE / a || b < Integer.MAX_VALUE / a)
                || (a == -1 && b == Integer.MIN_VALUE)) {
            throw new OverflowException("Multiplication overflow: " + a + " * " + b);
        }
    }

    @Override
    public Integer negate(final Integer x) {
        if (overflowCheck) {checkNegationOverflow(x);}
        return -x;
    }

    private void checkNegationOverflow(final int a) {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException("Negation overflow: " + a);
        }
    }

    @Override
    public Integer parseString(final String s) {
        return Integer.parseInt(s);
    }

    @Override
    public String toString(final Integer x) {
        return String.valueOf(x);
    }

    @Override
    public Integer abs(final Integer x) {
        return x < 0 ? negate(x) : x;
    }

    @Override
    public Integer square(final Integer x) {
        return multiply(x, x);
    }

    @Override
    public Integer mod(final Integer x, final Integer y) {
        checkDivisionByZero(x, y);
        return x % y;
    }
}
