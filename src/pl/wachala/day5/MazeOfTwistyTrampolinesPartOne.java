package pl.wachala.day5;

public class MazeOfTwistyTrampolinesPartOne {

    public static long getNumberOfMoves(long moves[]) {
        int currentIndex = 0;
        long numberOfMoves = 0;

        while (currentIndex >= 0 && currentIndex < moves.length) {
            long currentMove = moves[currentIndex];
            moves[currentIndex]++;

            currentIndex += currentMove;

            numberOfMoves++;
        }
        return numberOfMoves;
    }

}
