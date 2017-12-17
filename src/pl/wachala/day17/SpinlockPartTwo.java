package pl.wachala.day17;

public class SpinlockPartTwo {
    private static final int iterations = 50000000;

    public static int getNumberAfterZero(int step) {
        int positionOfZero = 0;
        int nextToZero = 0;
        int currentPos = 0;

        for (int i = 1; i <= iterations; i++) {
            currentPos = (currentPos + step) % i + 1;

            if (currentPos <= positionOfZero)
                positionOfZero++;
            if (currentPos == positionOfZero + 1)
                nextToZero = i;
        }

        return nextToZero;
    }

}
