package pl.wachala.day22;

public class SporificaVirusPartTwo extends SporificaVirusPartOne {
    private char map[][] = new char[INITIAL_SIZE][INITIAL_SIZE];

    protected void performBurst() {
        char currentChar = map[currentY][currentX];

        if (currentChar == '.') {
            direction = leftTurns.get(direction);
            map[currentY][currentX] = 'W';
        } else if (currentChar == 'W') {
            map[currentY][currentX] = '#';
            infectionCount++;
        } else if (currentChar == 'F') {
            map[currentY][currentX] = '.';

            direction = leftTurns.get(direction);
            direction = leftTurns.get(direction);
        } else {
            map[currentY][currentX] = 'F';
            direction = rightTurns.get(direction);
        }

        transitions.get(direction).run();
    }

    @Override
    protected void parseInput(String[] input) {
        for (int i = 0; i < INITIAL_SIZE; i++)
            for (int j = 0; j < INITIAL_SIZE; j++)
                map[i][j] = '.';

        int startX = INITIAL_SIZE / 2 - input.length / 2;
        int startY = INITIAL_SIZE / 2 - input[0].length() / 2;

        for (int i = 0; i < input.length; i++)
            for (int j = 0; j < input[0].length(); j++)
                map[i + startY][j + startX] = input[i].charAt(j);
    }

}
