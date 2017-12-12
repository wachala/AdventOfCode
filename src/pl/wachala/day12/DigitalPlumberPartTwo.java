package pl.wachala.day12;

import java.util.*;

public class DigitalPlumberPartTwo extends DigitalPlumberPartOne{

    public static int getNumberOfGroups(String input[]) {
        Map<Integer, List<Integer>> connections = parseInput(input);
        Set<Integer> alreadyProcessed = new HashSet<>();
        int groups = 0;

        for(Integer currentStart: connections.keySet()) {

            if(!alreadyProcessed.contains(currentStart)){
                groups++;
                Set<Integer> processesInGroup = new HashSet<>();
                Queue<Integer> queue = new LinkedList<>();
                queue.add(currentStart);

                while(!queue.isEmpty()){
                    Integer current = queue.poll();
                    alreadyProcessed.add(current);
                    List<Integer> connectedProcesses = connections.get(current);

                    for (Integer connectedProcess : connectedProcesses) {
                        if(!processesInGroup.contains(connectedProcess)) {
                            queue.offer(connectedProcess);
                            processesInGroup.add(connectedProcess);
                        }
                    }
                }
            }
        }

        return groups;
    }

}
