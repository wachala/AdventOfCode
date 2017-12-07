package pl.wachala.day7;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RecursiveCircusPartOne {

    public static String getBottomProgram(String input[]) {
        Map<String, String> towerToParent = new HashMap<>();
        List<String> towers = new LinkedList<>();

        for (String line : input) {
            String splitted[] = line.split("->");

            String towerName = splitted[0].split(" ")[0];
            towers.add(towerName);

            if (splitted.length > 1) {
                String children[] = splitted[1].replace(",", "").split(" ");

                for (String child : children)
                    towerToParent.put(child, towerName);
            }
        }

        for (String tower : towers)
            if (towerToParent.get(tower) == null) return tower;

        return null;
    }

}
