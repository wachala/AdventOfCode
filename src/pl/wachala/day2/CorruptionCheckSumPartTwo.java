package pl.wachala.day2;

import java.util.Arrays;

public class CorruptionCheckSumPartTwo {

    private static int calculate(String line) {
        String[] splitted = line.split("\t");
        int array[] = new int[splitted.length];

        for (int i = 0; i < splitted.length; i++)
            array[i] = Integer.parseInt(splitted[i]);

        Arrays.sort(array);

        for (int i = 0; i < array.length; i++)
            for (int j = i + 1; j < array.length; j++)
                if (array[j] % array[i] == 0)
                    return array[j] / array[i];

        return -1;
    }

    public static long checkSumForSpreadsheet(String spreadsheet) {
        String lines[] = spreadsheet.trim().split("\n");

        long checkSum = 0;

        for (String line : lines)
            checkSum += calculate(line);

        return checkSum;
    }
}
