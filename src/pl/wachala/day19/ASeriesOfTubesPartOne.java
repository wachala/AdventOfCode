package pl.wachala.day19;

import static pl.wachala.day19.Direction.*;

public class ASeriesOfTubesPartOne {
    protected char map[][];
    int x;
    int y;
    Direction direction;

    ASeriesOfTubesPartOne(char map[][]) {
        this.map = map;
    }

    private boolean isLetter(int x, int y) {
        return map[y][x] >= 'A' && map[y][x] <= 'Z';
    }

    private boolean isPointInMap(int x, int y) {
        return x >= 0 && x < map[0].length && y >= 0 && y < map.length;
    }

    private boolean moveAvailable(int x, int y, char expectedValue) {
        return isPointInMap(x, y)
                && (map[y][x] == expectedValue || map[y][x] == '+' || isLetter(x, y));
    }

    boolean horizontalMoveAvailable(int x, int y) {
        return moveAvailable(x, y, '-');
    }

    boolean verticalMoveAvailable(int x, int y) {
        return moveAvailable(x, y, '|');
    }

    boolean canPerformVerticalMove(int x, int y, int diff) {
        return (horizontalMoveAvailable(x, y + diff) || moveAvailable(x, y + diff, '_'))
                && verticalMoveAvailable(x, y + 2 * diff);
    }

    boolean canPerformHorizontalMove(int x, int y, int diff) {
        return (verticalMoveAvailable(x + diff, y) || moveAvailable(x + diff, y, '/'))
                && horizontalMoveAvailable(x + 2 * diff, y);
    }

    protected boolean tryPerformingDownMove() {
        if (direction == DOWN) {
            if (verticalMoveAvailable(x, y + 1)) {
                y = y + 1;
                return true;
            } else if (canPerformVerticalMove(x, y, 1)) {
                y = y + 2;
                return true;
            }
            return tryTurn();
        }
        return false;
    }

    protected boolean tryPerformingUpMove() {
        if (direction == UP) {
            if (verticalMoveAvailable(x, y - 1)) {
                y = y - 1;
                return true;
            } else if (canPerformVerticalMove(x, y, -1)) {
                y = y - 2;
                return true;
            } else
                return tryTurn();
        }
        return false;
    }

    protected boolean tryPerformingLeftMove() {
        if (direction == LEFT) {
            if (horizontalMoveAvailable(x - 1, y)) {
                x = x - 1;
                return true;
            } else if (canPerformHorizontalMove(x, y, -1)) {
                x = x - 2;
                return true;
            } else
                return tryTurn();
        }
        return false;
    }


    protected boolean tryPerformingRightMove() {
        if (direction == RIGHT) {
            if (horizontalMoveAvailable(x + 1, y)) {
                x = x + 1;
                return true;
            } else if (canPerformHorizontalMove(x, y, 1)) {
                x = x + 2;
                return true;
            } else
                return tryTurn();
        }
        return false;
    }

    protected boolean tryTurn() {
        if (horizontalMoveAvailable(x - 1, y)) {
            x -= 1;
            direction = LEFT;
            return true;
        } else if (horizontalMoveAvailable(x + 1, y)) {
            x += 1;
            direction = RIGHT;
            return true;
        } else if (verticalMoveAvailable(x, y - 1)) {
            y -= 1;
            direction = UP;
            return true;
        } else if (verticalMoveAvailable(x, y + 1)) {
            y += 1;
            direction = DOWN;
            return true;
        }
        return false;
    }

    private void printMap() {
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++)
                System.out.print(map[i][j]);
            System.out.println();
        }
    }

    private void eraseCell() {
        if (map[y][x] == '|') map[y][x] = '/';
        else if (map[y][x] == '-') map[y][x] = '_';
        else map[y][x] = '*';
    }

    public String getLetters() {
        if (map == null || map.length == 0) return "";

        StringBuilder builder = new StringBuilder();
        x = 0;
        y = 0;
        direction = DOWN;

        for (x = 0; x < map[0].length; x++)
            if (map[0][x] == '|') break;

        while (isPointInMap(x, y)) {
            if (isLetter(x, y))
                builder.append(map[y][x]);

            eraseCell();

            if (tryPerformingDownMove() || tryPerformingUpMove()
                    || tryPerformingLeftMove() || tryPerformingRightMove()) continue;

            break;
        }

        return builder.toString();
    }
}
