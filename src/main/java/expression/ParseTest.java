package expression;

import expression.exceptions.ParsingException;
import expression.generic.operations.IntegerOperations;
import expression.interfaces.TripleExpressionGeneric;
import expression.parser.ExpressionParserGeneric;
import expression.parser.ParserGeneric;

public class ParseTest {
    public static void main(final String[] args) throws ParsingException {
        final ParserGeneric<Integer> parserGeneric = new ExpressionParserGeneric<>(new IntegerOperations(true));
        final TripleExpressionGeneric<Integer> expression = parserGeneric.parse("2");
        System.out.println(expression.toString());
    }
}
