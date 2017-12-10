package pl.wachala.day10;

import java.util.Arrays;
import java.util.List;

public class KnotHashPartTwo {
    private static final List<Integer> SUFFIX = Arrays.asList(17, 31, 73, 47, 23);
    private static final Integer SEQUENCE_LENGTH = 256;
    private static final Integer ROUNDS = 64;

    static class Pair {
        int position;
        int toSkipped;

        Pair(int position, int toSkipped) {
            this.position = position;
            this.toSkipped = toSkipped;
        }
    }

    private static int[] getBaseArray() {
        int result[] = new int[SEQUENCE_LENGTH];

        for (int i = 0; i < SEQUENCE_LENGTH; i++)
            result[i] = i;

        return result;
    }

    public static String getHash(List<Integer> input) {
        input.addAll(SUFFIX);

        int[] baseArray = getBaseArray();
        int start = 0;
        int skip = 0;

        for (int i = 0; i < ROUNDS; i++) {
            Pair result = calculateHash(baseArray, input, start, skip);
            start = result.position;
            skip = result.toSkipped;
        }

        return getDenseHash(baseArray);
    }

    private static String getDenseHash(int base[]) {
        int xored[] = new int[16];

        for (int i = 0; i < 16; i++) {
            int xor = 0;
            for (int j = 16 * i; j < 16 * (i + 1); j++)
                xor ^= base[j];

            xored[i] = xor;
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            String hex = Integer.toHexString(xored[i]);
            if (hex.length() == 2)
                builder.append(hex);
            else
                builder.append("0").append(hex);
        }

        return builder.toString();
    }

    public static Pair calculateHash(int base[], List<Integer> lengths, int currentPosition, int skipPositions) {
        for (int e : lengths) {
            int reverseStart = currentPosition;

            for (int i = 0; i < e / 2; i++) {
                int source = (reverseStart + i) % SEQUENCE_LENGTH;
                int dest = (reverseStart + e - 1 - i) % SEQUENCE_LENGTH;

                int tmp = base[source];
                base[source] = base[dest];
                base[dest] = tmp;
            }

            currentPosition = (currentPosition + e + skipPositions) % SEQUENCE_LENGTH;
            skipPositions++;
        }
        return new Pair(currentPosition, skipPositions);
    }

}
