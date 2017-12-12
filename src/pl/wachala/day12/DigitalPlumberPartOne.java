package pl.wachala.day12;

import java.util.*;

public class DigitalPlumberPartOne {

    static Map<Integer, List<Integer>> parseInput(String input[]) {
        Map<Integer, List<Integer>> connections = new HashMap<>();

        for (String line : input) {
            String[] split = line.replace(" ", "").split("<->");

            int process = Integer.parseInt(split[0]);
            List<Integer> connectedProcesses = new LinkedList<>();

            for (String connected : split[1].split(","))
                connectedProcesses.add(Integer.parseInt(connected));

            connections.put(process, connectedProcesses);
        }
        return connections;
    }

    public static int getNumberOfProcesses(String input[]) {
        Map<Integer, List<Integer>> connections = parseInput(input);
        Set<Integer> connectedWithZero = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            List<Integer> connectedProcesses = connections.get(current);

            for (Integer connectedProcess : connectedProcesses) {
                if (!connectedWithZero.contains(connectedProcess)) {
                    queue.offer(connectedProcess);
                    connectedWithZero.add(connectedProcess);
                }
            }
        }
        return connectedWithZero.size();
    }

}
