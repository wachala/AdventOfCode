package pl.wachala.day6;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MemoryReallocationPartOne {

    public static int getNumberOfReallocation(int[] input) {
        Set<String> seen = new HashSet<>();
        int reallocation = 0;

        while (!seen.contains(Arrays.toString(input))) {
            int max = Integer.MIN_VALUE;
            int maxIndex = 0;
            seen.add(Arrays.toString(input));

            for (int i = 0; i < input.length; i++) {
                if (input[i] > max) {
                    max = input[i];
                    maxIndex = i;
                }
            }

            int toReallocate = input[maxIndex];
            input[maxIndex] = 0;
            int currentIndex = (maxIndex + 1) % input.length;

            while (toReallocate > 0) {
                input[currentIndex]++;
                toReallocate--;

                currentIndex = (currentIndex + 1) % input.length;
            }

            reallocation++;
        }

        return reallocation;
    }

}
