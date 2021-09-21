package expression.parser;

import expression.exceptions.ParsingException;
import expression.interfaces.TripleExpressionGeneric;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface ParserGeneric<T> {
    TripleExpressionGeneric<T> parse(String expression) throws ParsingException;
}