package expression.generic.operations;

public class DoubleOperations implements Operations<Double> {
    @Override
    public Double add(final Double x, final Double y) {
        return x + y;
    }

    @Override
    public Double subtract(final Double x, final Double y) {
        return x - y;
    }

    @Override
    public Double divide(final Double x, final Double y) {
        return x / y;
    }

    @Override
    public Double multiply(final Double x, final Double y) {
        return x * y;
    }

    @Override
    public Double negate(final Double x) {
        return -x;
    }

    @Override
    public Double parseString(final String s) {
        return Double.parseDouble(s);
    }

    @Override
    public String toString(final Double x) {
        return String.valueOf(x);
    }

    @Override
    public Double abs(final Double x) {
        return x < 0 ? negate(x) : x;
    }

    @Override
    public Double square(final Double x) {
        return multiply(x, x);
    }

    @Override
    public Double mod(final Double x, final Double y) {
        return x % y;
    }
}
