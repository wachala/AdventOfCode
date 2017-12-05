package pl.wachala.day5;

public class MazeOfTwistyTrampolinesPartTwo {

    public static long getNumberOfMoves(long moves[]) {
        int currentIndex = 0;
        long numberOfMoves = 0;

        while (currentIndex >= 0 && currentIndex < moves.length) {
            long currentMove = moves[currentIndex];

            if (currentMove >= 3)
                moves[currentIndex]--;
            else
                moves[currentIndex]++;

            currentIndex += currentMove;
            numberOfMoves++;
        }
        return numberOfMoves;
    }

}
