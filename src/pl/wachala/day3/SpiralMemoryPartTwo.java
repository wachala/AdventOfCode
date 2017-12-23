package pl.wachala.day3;

public class SpiralMemoryPartTwo {
    private final int INITIAL_SIZE = 15;

    public int getFirstLargerValue(int puzzle) {
        int board[][] = new int[INITIAL_SIZE][INITIAL_SIZE];

        int currentX = INITIAL_SIZE / 2;
        int currentY = INITIAL_SIZE / 2;

        int steps = 1;
        board[currentY][currentX] = 1;

        currentX++;
        int squareSize = 3;
        int deltaX = 0;
        int deltaY = -1;

        while (true) {
            int sum = getSumOfAdjacentElements(board, currentX, currentY);
            if(sum>puzzle)
                return sum;

            board[currentY][currentX] = sum;

            if (steps % (squareSize - 1) == 0) {
                if (deltaY == 0 && deltaX == 1) {
                    deltaX = 0;
                    deltaY = -1;
                } else if (deltaY == -1) {
                    deltaX = -1;
                    deltaY = 0;
                } else if (deltaX == -1) {
                    deltaX = 0;
                    deltaY = 1;
                } else {
                    deltaX = 1;
                    deltaY = 0;
                }
            }

            if (steps +1== (squareSize * squareSize)) {
                squareSize += 2;
                currentX++;
            }
            else{
                currentX += deltaX;
                currentY += deltaY;
            }

            steps++;
        }
    }

    private int getSumOfAdjacentElements(int[][] board, int currentX, int currentY) {
        int sum = 0;

        for (int i = -1; i < 2; i++) {
            if (isInBoard(board, currentX + i, currentY - 1)) sum += board[currentY - 1][currentX + i];
            if (isInBoard(board, currentX + i, currentY)) sum += board[currentY][currentX + i];
            if (isInBoard(board, currentX + i, currentY + 1)) sum += board[currentY + 1][currentX + i];
        }

        return sum;
    }

    private boolean isInBoard(int board[][], int x, int y) {
        return y >= 0 && y < board.length && x >= 0 && x < board[0].length;
    }

}
