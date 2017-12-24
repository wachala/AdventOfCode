package pl.wachala.day24;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ElectromagneticMoatPartOne {

    public int getMaximumBridgeStrength(String input[]) {
        Set<Component> components = parseInput(input);

        return findMaximumBridgeStrength(0, components);
    }

    private int findMaximumBridgeStrength(int port, Set<Component> components) {
        List<Component> matchingComponents = new LinkedList<>();
        int localMax = 0;

        for (Component component : components)
            if (component.couldBeUsed(port)) matchingComponents.add(component);

        for (Component component : matchingComponents) {
            Set<Component> toBePassed = new HashSet<>(components);
            toBePassed.remove(component);

            int currentMax = component.getFirstPort() + component.getSecondPort();

            if (component.getFirstPort() == port)
                currentMax += findMaximumBridgeStrength(component.getSecondPort(), toBePassed);
            else
                currentMax += findMaximumBridgeStrength(component.getFirstPort(), toBePassed);

            localMax = Math.max(currentMax, localMax);
        }
        return localMax;
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
