package pl.wachala.day19;

import static pl.wachala.day19.Direction.*;

public class ASeriesOfTubesPartTwo extends ASeriesOfTubesPartOne {
    private int steps;

    protected ASeriesOfTubesPartTwo(char[][] map) {
        super(map);
    }

    @Override
    protected boolean tryPerformingDownMove() {
        if (direction == DOWN) {
            if (verticalMoveAvailable(x, y + 1)) {
                y = y + 1;
                steps++;
                return true;
            } else if (canPerformVerticalMove(x, y, 1)) {
                y = y + 2;
                steps += 2;
                return true;
            }
            return tryTurn();
        }
        return false;
    }

    @Override
    protected boolean tryPerformingUpMove() {
        if (direction == UP) {
            if (verticalMoveAvailable(x, y - 1)) {
                y = y - 1;
                steps++;
                return true;
            } else if (canPerformVerticalMove(x, y, -1)) {
                y = y - 2;
                steps += 2;
                return true;
            } else
                return tryTurn();
        }
        return false;
    }

    @Override
    protected boolean tryPerformingLeftMove() {
        if (direction == LEFT) {
            if (horizontalMoveAvailable(x - 1, y)) {
                x = x - 1;
                steps++;
                return true;
            } else if (canPerformHorizontalMove(x, y, -1)) {
                x = x - 2;
                steps += 2;
                return true;
            } else
                return tryTurn();
        }
        return false;
    }

    @Override
    protected boolean tryPerformingRightMove() {
        if (direction == RIGHT) {
            if (horizontalMoveAvailable(x + 1, y)) {
                x = x + 1;
                steps++;
                return true;
            } else if (canPerformHorizontalMove(x, y, 1)) {
                x = x + 2;
                steps += 2;
                return true;
            } else
                return tryTurn();
        }
        return false;
    }

    @Override
    protected boolean tryTurn() {
        if (horizontalMoveAvailable(x - 1, y)) {
            x -= 1;
            direction = LEFT;
            steps++;
            return true;
        } else if (horizontalMoveAvailable(x + 1, y)) {
            x += 1;
            direction = RIGHT;
            steps++;
            return true;
        } else if (verticalMoveAvailable(x, y - 1)) {
            y -= 1;
            direction = UP;
            steps++;
            return true;
        } else if (verticalMoveAvailable(x, y + 1)) {
            y += 1;
            direction = DOWN;
            steps++;
            return true;
        }
        return false;
    }

    public int getStepsRequired() {
        steps = 0;
        getLetters();

        return steps + 1;
    }

}
