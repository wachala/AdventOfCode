package pl.wachala.day11;

import java.util.HashMap;
import java.util.Map;

public class HexEdPartTwo {
    private static Map<String, Pair> distances;

    static {
        distances = new HashMap<>();
        distances.put("n", new Pair(0, 1));
        distances.put("s", new Pair(0, -1));
        distances.put("nw", new Pair(-1, 1));
        distances.put("ne", new Pair(1, 0));
        distances.put("sw", new Pair(-1, 0));
        distances.put("se", new Pair(1, -1));
    }

    private static int getDistance(int x, int y) {
        return (Math.abs(x) + Math.abs(y) + Math.abs(x + y)) / 2;
    }

    public static int getDistance(String input[]) {
        int x = 0;
        int y = 0;
        int max = 0;

        for (String dir : input) {
            Pair pair = distances.get(dir);
            x += pair.x;
            y += pair.y;
            max = Math.max(max, getDistance(x, y));
        }

        return max;
    }

}
