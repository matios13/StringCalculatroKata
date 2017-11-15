package co.uk.eclispegroup.string.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class StringCalculator {

    public static final String DEFAULT_REGEX = "[\n,]";
    public static class CalculatorData{
        private final String delimiter;
        private final String numbers;

        public CalculatorData(String delimiter, String numbers) {
            this.delimiter = delimiter;
            this.numbers = numbers;
        }

    }

    public static int add(String userInput) throws Exception {
        if (userInput.isEmpty()) {
            return 0;
        }
        final CalculatorData calculatorData = buildCalculatorData(userInput);
        final String[] split = calculatorData.numbers.split(calculatorData.delimiter);

        handleNegativeNumbers(split);

        return sum(split, n -> n <= 1000);
    }

    private static int sum(String[] split, IntPredicate intPredicate) {
        return Arrays.stream(split)
                .mapToInt(Integer::parseInt)
                .filter(intPredicate)
                .sum();
    }

    private static CalculatorData buildCalculatorData(String userInput) {
        if (userInput.contains("//[")) {
            int delimiterEnd = userInput.lastIndexOf("]");
            String[] delimiterArray = userInput.substring(3, delimiterEnd).split("]\\[");
            String delimiter = "(" + Arrays.stream(delimiterArray)
                    .collect(joining("|")) + ")";
            userInput = userInput.substring(delimiterEnd + 2);
            return new CalculatorData(delimiter, userInput);
        } else if (userInput.contains("//")) {
            String delimiter = userInput.substring(2, 3);
            userInput = userInput.substring(4);
            return new CalculatorData(delimiter, userInput);
        } else {
            return new CalculatorData(DEFAULT_REGEX, userInput);
        }
    }

    private static void handleNegativeNumbers(String[] split) throws Exception {
        String negativeIntegers = Arrays.stream(split)
                .mapToInt(Integer::parseInt)
                .filter(i -> i < 0)
                .mapToObj(String::valueOf)
                .collect(joining(","));

        if (!negativeIntegers.isEmpty()) {
            throw new Exception("negatives not allowed: " + negativeIntegers);
        }
    }
}