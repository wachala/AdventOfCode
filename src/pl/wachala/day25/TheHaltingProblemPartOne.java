package pl.wachala.day25;

import java.util.HashMap;
import java.util.Map;

import static pl.wachala.day25.State.*;

public class TheHaltingProblemPartOne {
    private boolean tape[];
    private State state;
    private int currentPosition;
    private Map<State, Runnable> transitions;

    public TheHaltingProblemPartOne() {
        int INITIAL_SIZE = 100;
        tape = new boolean[INITIAL_SIZE];

        state = A;
        currentPosition = INITIAL_SIZE / 2;

        transitions = new HashMap<>();
        transitions.put(A, () -> {
            boolean currentValue = tape[currentPosition];
            tape[currentPosition] = !tape[currentPosition];

            if (currentValue)
                currentPosition--;
            else
                currentPosition++;
            state = B;
        });

        transitions.put(B, () -> {
            boolean currentValue = tape[currentPosition];

            if (currentValue) {
                currentPosition--;
                state = B;
            } else {
                currentPosition++;
                state = C;
            }
        });
        transitions.put(C, () -> {
            boolean currentValue = tape[currentPosition];
            tape[currentPosition] = !tape[currentPosition];

            if (currentValue) {
                currentPosition--;
                state = A;
            } else {
                currentPosition++;
                state = D;
            }
        });
        transitions.put(D, () -> {
            boolean currentValue = tape[currentPosition];
            tape[currentPosition] = true;

            if (currentValue) {
                currentPosition--;
                state = F;
            } else {
                currentPosition--;
                state = E;
            }
        });
        transitions.put(E, () -> {
            boolean currentValue = tape[currentPosition];
            tape[currentPosition] = !tape[currentPosition];

            if (currentValue) {
                currentPosition--;
                state = D;
            } else {
                currentPosition--;
                state = A;
            }
        });
        transitions.put(F, () -> {
            boolean currentValue = tape[currentPosition];
            tape[currentPosition] = true;

            if (currentValue) {
                currentPosition--;
                state = E;
            } else {
                currentPosition++;
                state = A;
            }
        });
    }

    private void performStep() {
        if (currentPosition < 0 || currentPosition >= tape.length) {
            int newSize = tape.length * 2;
            boolean[] newTape = new boolean[newSize];

            currentPosition = currentPosition + tape.length / 2;
            System.arraycopy(tape, 0, newTape, currentPosition, tape.length);

            tape = newTape;
        }

        transitions.get(state).run();
    }

    public int getChecksum(int steps) {
        for (int i = 0; i < steps; i++)
            performStep();

        int count = 0;
        for (boolean element : tape)
            if (element) count++;

        return count;
    }

}
