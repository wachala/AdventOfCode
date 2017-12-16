package pl.wachala.day16;

import java.util.*;
import java.util.function.BiConsumer;

public class PermutationPromenadePartOne {
    private static final int ELEMENTS = 16;

    public static String getOrderAfterDances(String input[], int numberOfDances) {
        char current = 'a';
        char order[] = new char[ELEMENTS];
        Map<Character, Integer> charPositions = new HashMap<>();

        for (int i = 0; i < ELEMENTS; i++) {
            charPositions.put(current, i);
            order[i] = current++;
        }

        char originalValue[] = Arrays.copyOf(order, ELEMENTS);
        List<BiConsumer<char[], Map<Character, Integer>>> operationsToBePerformed = parseOperations(input);

        for (int i = 1; i <= numberOfDances; i++) {
            getOrderAfterDance(order, operationsToBePerformed, charPositions);

            if (Arrays.equals(originalValue, order)) {
                int missingSteps = numberOfDances % i;
                i = numberOfDances - missingSteps;
            }
        }

        return String.valueOf(order);
    }

    private static List<BiConsumer<char[], Map<Character, Integer>>> parseOperations(String input[]) {
        List<BiConsumer<char[], Map<Character, Integer>>> operationsToBePerformed = new ArrayList<>(input.length);

        for (String line : input) {
            char operationType = line.charAt(0);

            if (operationType == 's') {
                int value = Integer.parseInt(line.substring(1));
                operationsToBePerformed.add((o, p) -> spinArray(o, value, p));
            } else if (operationType == 'x') {
                String splitted[] = line.substring(1).split("/");

                int source = Integer.parseInt(splitted[0]);
                int dest = Integer.parseInt(splitted[1]);

                operationsToBePerformed.add((o, p) -> swapElementAt(o, source, dest, p));
            } else if (operationType == 'p') {
                String splitted[] = line.substring(1).split("/");

                char source = splitted[0].charAt(0);
                char dest = splitted[1].charAt(0);


                operationsToBePerformed.add((o, p) -> {
                    int sourceIndex = p.get(source);
                    int destIndex = p.get(dest);

                    swapElementAt(o, sourceIndex, destIndex, p);
                });
            }
        }

        return operationsToBePerformed;
    }

    private static void getOrderAfterDance(char[] order, List<BiConsumer<char[], Map<Character, Integer>>> operationsToBePerformed,
                                           Map<Character, Integer> charPositions) {
        for (BiConsumer<char[], Map<Character, Integer>> operation : operationsToBePerformed)
            operation.accept(order, charPositions);
    }

    private static void swapElementAt(char input[], int source, int dest, Map<Character, Integer> positions) {
        char tmp = input[source];
        input[source] = input[dest];
        input[dest] = tmp;

        positions.put(input[source], source);
        positions.put(input[dest], dest);
    }

    private static void spinArray(char input[], int spin, Map<Character, Integer> positions) {
        reverseArray(input, 0, input.length - 1);
        reverseArray(input, 0, spin - 1);
        reverseArray(input, spin, input.length - 1);
        updatePositions(input, positions);
    }

    private static void updatePositions(char input[], Map<Character, Integer> positions) {
        for (int i = 0; i < input.length; i++)
            positions.put(input[i], i);
    }

    private static void reverseArray(char input[], int start, int end) {
        while (start < end) {
            char tmp = input[end];
            input[end] = input[start];
            input[start] = tmp;
            end--;
            start++;
        }
    }

}
