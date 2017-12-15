package pl.wachala.day15;

public class DuelingGeneratorsPartTwo extends DuelingGeneratorsPartOne {
    private static final long FACTOR_A = 16807;
    private static final long FACTOR_B = 48271;
    private static final long DIVIDER = 2147483647;
    private static final int ITERATIONS = 5000000;


    private static long generateNextValue(long prevValue, long factor, int condition) {
        long number = prevValue;

        while (true) {
            number = (number * factor) % DIVIDER;
            if (number % condition == 0) break;
        }

        return number;
    }

    public static int getNumberOfMatchingPairs(long startA, long startB) {
        int matching = 0;

        long valueFromGenA = startA;
        long valueFromGenB = startB;

        for (int i = 0; i < ITERATIONS; i++) {
            valueFromGenA = generateNextValue(valueFromGenA, FACTOR_A, 4);
            valueFromGenB = generateNextValue(valueFromGenB, FACTOR_B, 8);

            if (numbersMatch(valueFromGenA, valueFromGenB)) matching++;
        }

        return matching;
    }

}
