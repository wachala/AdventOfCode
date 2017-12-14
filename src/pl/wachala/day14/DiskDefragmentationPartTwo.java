package pl.wachala.day14;

import pl.wachala.day10.KnotHashPartTwo;

import java.util.ArrayList;
import java.util.List;

public class DiskDefragmentationPartTwo {
    private static final int MATRIX_SIZE = 128;

    private static void markGroup(int matrix[][], int x, int y, int nr) {
        if (x < 0 || y < 0 || x >= matrix.length || y >= matrix.length)
            return;
        if (matrix[x][y] == 1) {
            matrix[x][y] = nr;
            markGroup(matrix, x - 1, y, nr);
            markGroup(matrix, x + 1, y, nr);
            markGroup(matrix, x, y + 1, nr);
            markGroup(matrix, x, y - 1, nr);
        }
    }

    private static int getNumberOfRegions(String key) {
        int groups = 0;

        int[][] matrix = getMatrix(key);

        for (int i = 0; i < MATRIX_SIZE; i++)
            for (int j = 0; j < MATRIX_SIZE; j++)
                if (matrix[i][j] == 1) {
                    groups++;
                    markGroup(matrix, i, j, groups + 1);
                }

        return groups;
    }

    private static int getNumericalValueOfHex(char hex) {
        return hex < 'a'
                ? hex - '0'
                : hex - 'a' + 10;
    }

    public static int[][] getMatrix(String key) {
        int matrix[][] = new int[MATRIX_SIZE][MATRIX_SIZE];

        for (int i = 0; i < MATRIX_SIZE; i++) {
            String currentKey = key + '-' + i;
            List<Integer> input = new ArrayList<>(currentKey.length());

            for (char c : currentKey.toCharArray())
                input.add((int) c);

            String hashKnot = KnotHashPartTwo.getHash(input);
            int horizontalIndex = 0;

            for (char c : hashKnot.toCharArray()) {
                int numericalValue = getNumericalValueOfHex(c);
                String binaryString = Integer.toBinaryString(numericalValue);

                if (binaryString.length() < 4) {
                    for (int k = 0; k < 4 - binaryString.length(); k++)
                        matrix[i][horizontalIndex++] = 0;
                }

                for (char k : Integer.toBinaryString(numericalValue).toCharArray()) {
                    if (k == '1')
                        matrix[i][horizontalIndex] = 1;

                    horizontalIndex++;
                }
            }
        }

        return matrix;
    }

}
