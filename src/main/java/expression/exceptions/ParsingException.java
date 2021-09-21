package expression.exceptions;

import java.text.ParseException;

public class ParsingException extends ParseException {
    public ParsingException(final String s, final int errorOffset) {
        super(s, errorOffset);
    }
}
