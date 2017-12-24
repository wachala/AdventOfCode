package pl.wachala.day22;

import java.util.HashMap;
import java.util.Map;

import static pl.wachala.day22.Direction.*;

public class SporificaVirusPartOne {
    protected final int INITIAL_SIZE = 1000;
    private boolean map[][] = new boolean[INITIAL_SIZE][INITIAL_SIZE];
    protected long infectionCount = 0;
    protected Direction direction = UP;
    protected Integer currentX = 0;
    protected Integer currentY = 0;
    protected Map<Direction, Direction> leftTurns = new HashMap<>();
    protected Map<Direction, Direction> rightTurns = new HashMap<>();
    protected Map<Direction, Runnable> transitions;

    public SporificaVirusPartOne() {
        leftTurns = new HashMap<>();
        rightTurns = new HashMap<>();

        leftTurns.put(UP, LEFT);
        leftTurns.put(LEFT, DOWN);
        leftTurns.put(DOWN, RIGHT);
        leftTurns.put(RIGHT, UP);

        rightTurns.put(UP, RIGHT);
        rightTurns.put(RIGHT, DOWN);
        rightTurns.put(DOWN, LEFT);
        rightTurns.put(LEFT, UP);

        transitions = new HashMap<>();

        transitions.put(UP, () -> currentY = currentY - 1);
        transitions.put(RIGHT, () -> currentX = currentX + 1);
        transitions.put(DOWN, () -> currentY = currentY + 1);
        transitions.put(LEFT, () -> currentX = currentX - 1);
    }

    protected void performBurst() {
        if (map[currentY][currentX])
            direction = rightTurns.get(direction);
        else {
            infectionCount++;
            direction = leftTurns.get(direction);
        }

        map[currentY][currentX] = !map[currentY][currentX];

        transitions.get(direction).run();
    }

    public long getNumberOfInfection(int iterations, String input[]) {
        parseInput(input);

        currentX = INITIAL_SIZE / 2;
        currentY = INITIAL_SIZE / 2;

        for (int i = 0; i < iterations; i++)
            performBurst();

        return infectionCount;
    }

    protected void parseInput(String[] input) {
        int startX = INITIAL_SIZE / 2 - input.length / 2;
        int startY = INITIAL_SIZE / 2 - input[0].length() / 2;

        for (int i = 0; i < input.length; i++)
            for (int j = 0; j < input[0].length(); j++)
                map[i + startY][j + startX] = input[i].charAt(j) == '#';
    }

}
