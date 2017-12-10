package pl.wachala.day10;

public class KnotHashPartOne {

    public static int hash(int sequence[]) {
        int SEQUENCE_LENGTH = 256;
        int base[] = new int[SEQUENCE_LENGTH];

        for (int i = 0; i < SEQUENCE_LENGTH; i++) base[i] = i;

        int skipPositions = 0;
        int currentPosition = 0;

        for (int e : sequence) {
            int reverseStart = currentPosition;

            for (int i = 0; i < e / 2; i++) {
                int source = (reverseStart + i) % SEQUENCE_LENGTH;
                int dest = (reverseStart + e - 1 - i) % SEQUENCE_LENGTH;

                int tmp = base[source];
                base[source] = base[dest];
                base[dest] = tmp;
            }

            currentPosition = (currentPosition + e + skipPositions) % SEQUENCE_LENGTH;
            skipPositions++;
        }

        return base[0] * base[1];
    }



}
