package expression.generic.operations;

public interface Operations<T> {
    T add(T x, T y);
    T subtract(T x, T y);
    T divide(T x, T y);
    T multiply(T x, T y);
    T negate(T x);
    T parseString(String s);
    String toString(T x);
    T abs(T x);
    T square(T x);
    T mod(T x, T y);
}
