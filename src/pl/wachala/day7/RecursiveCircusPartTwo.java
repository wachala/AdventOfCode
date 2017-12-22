package pl.wachala.day7;

import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class RecursiveCircusPartTwo {
    private static Long missingElement;

    private static long getMissingElement(Map<String, List<String>> towerToChildren, Map<String, Integer> weights, String root) {
        Map<Long, List<String>> sumOccurrences = new HashMap<>();
        List<String> children = towerToChildren.getOrDefault(root, Collections.emptyList());

        long sum = 0;

        for (String child : children) {
            long result = getMissingElement(towerToChildren, weights, child);

            List<String> previous = sumOccurrences.getOrDefault(result, new LinkedList<>());
            previous.add(child);
            sumOccurrences.put(result, previous);

            sum += result;
        }

        if (sumOccurrences.entrySet().size() > 1) {
            Long oneOccurrence = null;
            Long moreOccurrences = null;
            Integer singleElement = null;

            for (Map.Entry<Long, List<String>> integerIntegerEntry : sumOccurrences.entrySet()) {
                if (integerIntegerEntry.getValue().size() == 1) {
                    oneOccurrence = integerIntegerEntry.getKey();
                    singleElement = weights.get(integerIntegerEntry.getValue().get(0));
                } else
                    moreOccurrences = integerIntegerEntry.getKey();
            }

            if (nonNull(moreOccurrences) && nonNull(oneOccurrence) && isNull(missingElement))
                missingElement = singleElement + (moreOccurrences - oneOccurrence);
        }

        return sum + weights.get(root);
    }

    public static Long getExpectedElement(String input[]) {
        Map<String, String> towerToParent = new HashMap<>();
        Map<String, Integer> towerToWeight = new HashMap<>();
        Map<String, List<String>> towerToChild = new HashMap<>();

        List<String> towers = new LinkedList<>();

        for (String line : input) {
            String splitted[] = line.split("->");

            String towerInfo[] = splitted[0].split(" ");

            String towerName = towerInfo[0];
            Integer towerWeight = Integer.parseInt(towerInfo[1]
                    .replace("(", "")
                    .replace(")", ""));

            towers.add(towerName);
            towerToWeight.put(towerName, towerWeight);

            if (splitted.length > 1) {
                String children[] = splitted[1].replace(",", "").trim().split(" ");
                towerToChild.put(towerName, new ArrayList<>());

                for (String child : children) {
                    towerToParent.put(child, towerName);
                    towerToChild.get(towerName).add(child);
                }
            }
        }

        String root = null;

        for (String tower : towers) {
            if (towerToParent.get(tower) == null) {
                root = tower;
                break;
            }
        }

        getMissingElement(towerToChild, towerToWeight, root);

        return missingElement;
    }

}
