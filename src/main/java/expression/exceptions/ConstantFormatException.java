package expression.exceptions;

public class ConstantFormatException extends ParsingException {
    public ConstantFormatException(final String s, final int offset) {
        super(s, offset);
    }
}
