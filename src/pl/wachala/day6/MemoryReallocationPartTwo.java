package pl.wachala.day6;

import static pl.wachala.day6.MemoryReallocationPartOne.getNumberOfReallocation;

public class MemoryReallocationPartTwo {

    public static int getNumberOfCycles(int input[]) {
        getNumberOfReallocation(input);
        return getNumberOfReallocation(input);
    }

}
