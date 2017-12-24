package pl.wachala.day24;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ElectromagneticMoatPartTwo {
    private int longestBridge;
    private int longestBridgeStrength;

    public int getMaximumBridgeStrength(String input[]) {
        Set<Component> components = parseInput(input);

        findLongestBridgeStrength(0, components, 0, 0);

        return longestBridgeStrength;
    }

    private void findLongestBridgeStrength(int port, Set<Component> components, int length, int prevStrength) {
        List<Component> matchingComponents = new LinkedList<>();

        for (Component component : components)
            if (component.couldBeUsed(port)) matchingComponents.add(component);

        if (matchingComponents.size() == 0) {
            if (length > longestBridge) {
                longestBridge = length;
                longestBridgeStrength = prevStrength;
            } else if (length == longestBridge && prevStrength > longestBridgeStrength) {
                longestBridgeStrength = prevStrength;
            }
        }

        for (Component component : matchingComponents) {
            Set<Component> toBePassed = new HashSet<>(components);
            toBePassed.remove(component);

            int currentStrength = prevStrength + component.getFirstPort() + component.getSecondPort();

            if (component.getFirstPort() == port)
                findLongestBridgeStrength(component.getSecondPort(), toBePassed, length + 1, currentStrength);
            else
                findLongestBridgeStrength(component.getFirstPort(), toBePassed, length + 1, currentStrength);
        }
    }

    private Set<Component> parseInput(String input[]) {
        Set<Component> components = new HashSet<>();

        for (String line : input) {
            String[] split = line.split("/");

            components.add(new Component(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }

        return components;
    }

}
