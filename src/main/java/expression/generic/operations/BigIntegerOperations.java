package expression.generic.operations;

import expression.exceptions.ComputingException;
import expression.exceptions.DivisionByZeroException;

import java.math.BigInteger;

public class BigIntegerOperations implements Operations<BigInteger> {
    @Override
    public BigInteger add(final BigInteger x, final BigInteger y) {
        return x.add(y);
    }

    @Override
    public BigInteger subtract(final BigInteger x, final BigInteger y) {
        return x.subtract(y);
    }

    @Override
    public BigInteger divide(final BigInteger x, final BigInteger y) {
        checkDivisionByZero(x, y);
        return x.divide(y);
    }

    private void checkDivisionByZero(final BigInteger a, final BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            throw new DivisionByZeroException("Division by zero: " + a + "/" + b);
        }
    }

    @Override
    public BigInteger multiply(final BigInteger x, final BigInteger y) {
        return x.multiply(y);
    }

    @Override
    public BigInteger negate(final BigInteger x) {
        return x.negate();
    }

    @Override
    public BigInteger parseString(final String s) {
        return new BigInteger(s);
    }

    @Override
    public String toString(final BigInteger x) {
        return String.valueOf(x);
    }

    @Override
    public BigInteger abs(final BigInteger x) {
        return x.abs();
    }

    @Override
    public BigInteger square(final BigInteger x) {
        return multiply(x, x);
    }

    @Override
    public BigInteger mod(final BigInteger x, final BigInteger y) {
        checkBigIntMod(y);
        return x.mod(y);
    }

    private void checkBigIntMod(final BigInteger y) {
        if (y.compareTo(BigInteger.ZERO) <= 0) {
            throw new ComputingException("Big integer modulo with negative second argument");
        }
    }
}
