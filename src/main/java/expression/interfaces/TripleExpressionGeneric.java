package expression.interfaces;

public interface TripleExpressionGeneric<T> {
    T evaluate(T x, T y, T z);
}