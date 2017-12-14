package pl.wachala.day14;

import pl.wachala.day10.KnotHashPartTwo;

import java.util.ArrayList;
import java.util.List;

public class DiskDefragmentationPartOne {
    private static final int MATRIX_SIZE = 128;

    public static int getNumberOfSquaresUsed(String key) {
        int squares = 0;
        for (int i = 0; i < MATRIX_SIZE; i++) {
            String currentKey = key + '-' + i;
            List<Integer> input = new ArrayList<>(currentKey.length());

            for (char c : currentKey.toCharArray())
                input.add((int) c);

            String hashKnot = KnotHashPartTwo.getHash(input);

            for (char c : hashKnot.toCharArray()) {
                int numericalValue;

                if (c < 'a')
                    numericalValue = c - '0';
                else
                    numericalValue = c - 'a' + 10;

                for (char k : Integer.toBinaryString(numericalValue).toCharArray())
                    if (k == '1')
                        squares++;
            }
        }
        return squares;
    }

}
