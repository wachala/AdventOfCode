package pl.wachala.day15;

public class DuelingGeneratorsPartOne {
    private static final long FACTOR_A = 16807;
    private static final long FACTOR_B = 48271;
    private static final long DIVIDER = 2147483647;
    private static final int ITERATIONS = 40000000;

    static boolean numbersMatch(long a, long b) {
        for (int i = 0; i < 16; i++) {
            long bitA = a & (1 << i);
            long bitB = b & (1 << i);

            if (bitA != bitB) return false;
        }

        return true;
    }

    public static int getNumberOfMatchingPairs(long startA, long startB) {
        int matching = 0;

        long prevA = startA;
        long prevB = startB;

        for (int i = 0; i < ITERATIONS; i++) {
            prevA = (prevA * FACTOR_A)%DIVIDER;
            prevB = (prevB * FACTOR_B)%DIVIDER;

            if (numbersMatch(prevA, prevB))
                matching++;
        }

        return matching;
    }

}
