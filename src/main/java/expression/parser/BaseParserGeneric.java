package expression.parser;

import expression.exceptions.ParsingException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public abstract class BaseParserGeneric<T> implements ParserGeneric<T> {
    public static final char END = '\0';
    protected CharSource source;
    protected char ch = 0xffff;

    protected void nextChar() {
        ch = source.hasNext() ? source.next() : END;
    }

    protected boolean test(final char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected boolean test(final String expected) {
        final char startChar = ch;
        for (int i = 0; i < expected.length(); i++) {
            if (!test(expected.charAt(i))) {
                source.goBack(i);
                ch = startChar;
                return false;
            }
        }
        return true;
    }

    protected boolean isNumberPart() {
        return Character.isDigit(ch) || ch == '.';
    }

    protected boolean isLetter() {
        return Character.isLetter(ch);
    }

    protected boolean isConstant(final char c) {
        return c == 'x' || c == 'y' || c == 'z';
    }

    protected void expect(final char c) throws ParsingException {
        if (ch != c) {
            throw new ParsingException("Expected '" + c + "', found '" + ch + "'", source.getOffset());
        }
        nextChar();
    }

    protected boolean eof() {
        return test(END);
    }

    protected void skipWhitespaces() {
        while (true) {
            if (!(test(' ') ||
                    test('\r') ||
                    test('\n') ||
                    test('\t'))) {
                break;
            }
        }
    }
}
