package pl.wachala.day2;

public class CorruptionChecksumPartOne {

    private static int calculate(String line) {
        String[] splitted = line.split("\t");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (String numberStr : splitted) {
            int currentNumber = Integer.parseInt(numberStr);

            if (currentNumber > max)
                max = currentNumber;
            if (currentNumber < min)
                min = currentNumber;
        }

        return max - min;
    }

    public static long checkSumForSpreedsheet(String spreedsheet) {
        String lines[] = spreedsheet.trim().split("\n");

        long checkSum = 0;

        for (String line : lines)
            checkSum += calculate(line);

        return checkSum;
    }

}
