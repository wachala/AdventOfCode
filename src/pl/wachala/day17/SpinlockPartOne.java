package pl.wachala.day17;

import java.util.ArrayList;
import java.util.List;

public class SpinlockPartOne {

    private static int performIteration(List<Integer> buffer, int step, int currentPos, int iteration){
        int insertPos = (currentPos + step) % buffer.size()+1;
        buffer.add(insertPos, iteration);

        return insertPos;
    }

    public static int getNumberAfter2017(int step){
        int currentPosition = 0;
        List<Integer> buffer = new ArrayList<>();
        buffer.add(0);

        for(int i=1;i<=2017;i++)
            currentPosition = performIteration(buffer, step, currentPosition, i);

        return buffer.get((currentPosition + 1)%buffer.size());
    }
    
}
