package expression.parser;

import expression.exceptions.ConstantFormatException;
import expression.exceptions.ParsingException;
import expression.interfaces.TripleExpressionGeneric;
import expression.interfaces.UltimateExpressionGeneric;
import expression.literal.Const;
import expression.literal.Variable;
import expression.generic.operations.Operations;
import expression.operators.*;

import java.util.*;
import java.util.function.BiFunction;

public class ExpressionParserGeneric<T> extends BaseParserGeneric<T> {
    private final Operations<T> operations;
    private final List<Priority> priorities;

    private final Map<
            String,
            BiFunction<
                    AbstractBinaryOperator.BinaryArguments<T>,
                    Operations<T>,
                    UltimateExpressionGeneric<T>
                    >
            > binaryConstruct = Map.of(
            "+", Add::new,
            "-", Subtract::new,
            "*", Multiply::new,
            "/", Divide::new,
            "mod", Mod::new
    );

    private final Map<
            String,
            BiFunction<
                    UltimateExpressionGeneric<T>,
                    Operations<T>,
                    UltimateExpressionGeneric<T>
                    >
            > unaryConstruct = Map.of(
            "abs", Abs::new,
            "square", Square::new
    );

    private final Map<String, Priority> binaryOperatorPriorityMap = Map.of(
            "+", new Priority(20),
            "-", new Priority(20),
            "*", new Priority(40),
            "/", new Priority(40),
            "mod", new Priority(40)
    );

    private String buffer = "";

    public ExpressionParserGeneric(final Operations<T> operations) {
        this.operations = operations;

        final Set<Priority> prioritySet = new TreeSet<>();
        prioritySet.add(new Priority(100));
        for (final Map.Entry<String, Priority> entry : binaryOperatorPriorityMap.entrySet()) {
            prioritySet.add(entry.getValue());
        }

        priorities = new ArrayList<>(prioritySet);
    }

    @Override
    public TripleExpressionGeneric<T> parse(final String expression) throws ParsingException {
        source = new StringSource(expression);
        nextChar();
        if (eof()) {
            throw new ParsingException(
                    "Expected expression, found 'EOF' on position " + source.getOffset(),
                    source.getOffset()
            );
        }
        final UltimateExpressionGeneric<T> result = parsePriority(0);
        if (!eof()) {
            throw new ParsingException(
                    "Expected 'EOF', found '" + ch + "' on position " + source.getOffset(),
                    source.getOffset()
            );
        }
        return result;
    }

    private UltimateExpressionGeneric<T> parsePriority(final int priorityIndex) throws ParsingException {
        UltimateExpressionGeneric<T> result;
        final Priority currentPriority = priorities.get(priorityIndex);
        if (currentPriority.equals(priorities.get(priorities.size() - 1))) {
            return parseLiteral(false);
        } else {
            result = parsePriority(priorityIndex + 1);
        }
        while (true) {
            skipWhitespaces();
            if (currentPriority.equals(binaryOperatorPriorityMap.get(buffer))) {
                result = parseBufferOperator(result, priorityIndex);
                continue;
            } else if (buffer.length() == 0) {
                final UltimateExpressionGeneric<T> resultAssumption;
                if (!Character.isLetterOrDigit(ch) && ch != ')') {
                    resultAssumption = parseCharOperator(result, priorityIndex);
                } else {
                    resultAssumption = parseStringOperator(result, priorityIndex);
                }
                if (resultAssumption != null) {
                    result = resultAssumption;
                    continue;
                }
            }
            return result;
        }
    }

    private UltimateExpressionGeneric<T> parseBufferOperator(
            final UltimateExpressionGeneric<T> result,
            final int priorityIndex
    ) throws ParsingException {
        final String operator = buffer;
        buffer = "";
        return construct(result, priorityIndex, operator);
    }

    private UltimateExpressionGeneric<T> parseCharOperator(
            final UltimateExpressionGeneric<T> result,
            final int priorityIndex
    ) throws ParsingException {
        final char charOperator = this.ch;
        nextChar();
        if (charOperator == 0) {
            return null;
        }
        return createOperatorOrAddToBuffer(result, priorityIndex, Character.toString(charOperator));
    }

    private UltimateExpressionGeneric<T> parseStringOperator(
            final UltimateExpressionGeneric<T> result,
            final int priorityIndex
    ) throws ParsingException {
        final StringBuilder operator = new StringBuilder();
        while (isLetter() || isNumberPart()) {
            operator.append(ch);
            nextChar();
        }
        if (operator.length() == 0) {
            return null;
        }
        return createOperatorOrAddToBuffer(result, priorityIndex, operator.toString());
    }

    private UltimateExpressionGeneric<T> createOperatorOrAddToBuffer(
            final UltimateExpressionGeneric<T> result,
            final int priorityIndex,
            final String operator
    ) throws ParsingException {
        final Priority priorityPretend = binaryOperatorPriorityMap.get(operator);
        if (priorityPretend != null) {
            if (priorityPretend.equals(priorities.get(priorityIndex))) {
                return construct(result, priorityIndex, operator);
            } else {
                buffer = operator;
                return null;
            }
        }
        throw new ParsingException(
                "Unknown operator '" + operator + "' on position " + source.getOffset(),
                source.getOffset()
        );
    }

    private UltimateExpressionGeneric<T> construct(
            final UltimateExpressionGeneric<T> result,
            final int priorityIndex,
            final String operator
    ) throws ParsingException {
        final AbstractBinaryOperator.BinaryArguments<T> arguments =
                new AbstractBinaryOperator.BinaryArguments<>(
                        result,
                        parsePriority(priorityIndex + 1)
                );
        return binaryConstruct.get(operator).apply(arguments, operations);
    }


    private UltimateExpressionGeneric<T> parseLiteral(final boolean negate) throws ParsingException {
        skipWhitespaces();
        UltimateExpressionGeneric<T> result;

        if (test('-')) {
            result = parseLiteral(!negate);
            return result;
        }

        if (test('(')) {
            result = parsePriority(0);
            expect(')');
            if (negate) {
                result = new Negate<>(result, operations);
            }
            return result;
        }

        if (isNumberPart()) {
            result = new Const<>(parseConst(negate), operations);
            return result;
        }

        final StringBuilder stringLiteral = new StringBuilder();
        while (isLetter() || isNumberPart()) {
            stringLiteral.append(ch);
            nextChar();
        }

        if (stringLiteral.length() == 1 && isConstant(stringLiteral.charAt(0))) {
            result = new Variable<>(stringLiteral.toString());
            return result;
        } else {
            final BiFunction<
                    UltimateExpressionGeneric<T>,
                    Operations<T>,
                    UltimateExpressionGeneric<T>
                    > constructAssumption = unaryConstruct.get(stringLiteral.toString());
            if (constructAssumption != null) {
                result = constructAssumption.apply(parseLiteral(negate), operations);
                return result;
            } else {
                throw new ParsingException(
                        "Excepted literal or unary operator, found '"
                                + stringLiteral
                                + "' on position "
                                + source.getOffset(),
                        source.getOffset()
                );
            }
        }
    }

    private T parseConst(final boolean negate) throws ConstantFormatException {
        final StringBuilder result = new StringBuilder(negate ? "-" : "");
        while (isNumberPart()) {
            result.append(ch);
            nextChar();
        }

        try {
            return operations.parseString(result.toString());
        } catch (final NumberFormatException e) {
            throw new ConstantFormatException(
                    "Constant overflow or wrong type: " + result + " on position " + source.getOffset(),
                    source.getOffset()
            );
        }

    }
}
