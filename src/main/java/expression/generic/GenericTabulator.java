package expression.generic;

import expression.exceptions.ComputingException;
import expression.exceptions.ParsingException;
import expression.generic.operations.*;
import expression.interfaces.TripleExpressionGeneric;
import expression.parser.ExpressionParserGeneric;
import expression.parser.ParserGeneric;

import java.util.Map;

public class GenericTabulator implements Tabulator {

    private static final Map<String, Operations<?>> operationsMap = Map.of(
            "i", new IntegerOperations(true),
            "u", new IntegerOperations(false),
            "d", new DoubleOperations(),
            "bi", new BigIntegerOperations(),
            "s", new ShortOperations(),
            "l", new LongOperations()
    );

    public static void main(final String[] args) {
        if (args.length < 2) {
            System.out.println("At least 2 arguments required");
            return;
        }
        final GenericTabulator tabulator = new GenericTabulator();
        final Object[][][] res = tabulator.tabulate(args[0].substring(1), args[1], -2, 2, -2, 2, -2, 2);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    System.out.format("x = %d, y = %d, z = %d, result = %s \n", i - 2, j - 2, k - 2, res[i][j][k]);
                }
            }
        }
    }

    @Override
    public Object[][][] tabulate(final String mode,
                                 final String expression,
                                 final int x1, final int x2,
                                 final int y1, final int y2,
                                 final int z1, final int z2) {
        final Operations<?> operations = operationsMap.get(mode);
        if (operations == null) {
            throw new IllegalArgumentException("Unknown computation type");
        }
        return getResult(expression, x1, x2, y1, y2, z1, z2, operations);
    }

    private <T> Object[][][] getResult(final String expression,
                                       final int x1, final int x2,
                                       final int y1, final int y2,
                                       final int z1, final int z2,
                                       final Operations<T> operations) {

        final ParserGeneric<T> parserGeneric = new ExpressionParserGeneric<>(operations);
        final TripleExpressionGeneric<T> parsedExpression;
        try {
            parsedExpression = parserGeneric.parse(expression);
        } catch (final ParsingException e) {
            e.printStackTrace();
            return null;
        }

        final Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int i = 0; i <= x2 - x1; i++) {
            for (int j = 0; j <= y2 - y1; j++) {
                for (int k = 0; k <= z2 - z1; k++) {
                    final T x = operations.parseString(String.valueOf(i + x1));
                    final T y = operations.parseString(String.valueOf(j + y1));
                    final T z = operations.parseString(String.valueOf(k + z1));
                    try {
                        result[i][j][k] = parsedExpression.evaluate(x, y, z);
                    } catch (final ComputingException ignored) {}
                }
            }
        }

        return result;
    }
}
