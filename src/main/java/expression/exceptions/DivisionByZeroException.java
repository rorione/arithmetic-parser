package expression.exceptions;

public class DivisionByZeroException extends ComputingException {
    public DivisionByZeroException(final String message) {
        super(message);
    }
}
