package pl.wachala.day23;

import java.util.HashMap;
import java.util.Map;

public class CoprocessorConflagrationPartOne {
    protected static Map<Character, Long> registers;

    public CoprocessorConflagrationPartOne() {
        registers = new HashMap<>();
    }

    protected long getRegisterOrNumberValue(String input) {
        char firstChar = input.charAt(0);

        if (firstChar >= 'a' && firstChar <= 'z')
            return registers.getOrDefault(firstChar, 0L);

        return Long.parseLong(input);
    }

    protected void setRegistryValue(char register, long value) {
        registers.put(register, value);
    }

    protected long getNumberOfMulOperations(String input[]) {
        int currentOperation = 0;
        int mulPerformed = 0;

        while (currentOperation >= 0 && currentOperation < input.length) {
            String operation = input[currentOperation];
            if (operation.startsWith("set")) {
                String splitted[] = operation.split(" ");
                Character toBeSet = splitted[1].charAt(0);
                Long value = getRegisterOrNumberValue(splitted[2]);

                registers.put(toBeSet, value);
            } else if (operation.startsWith("sub ")) {
                String splitted[] = operation.split(" ");
                Character toBeSet = splitted[1].charAt(0);
                Long value = getRegisterOrNumberValue(splitted[2]);

                setRegistryValue(toBeSet, registers.getOrDefault(toBeSet, 0L) - value);
            } else if (operation.startsWith("mul")) {
                String splitted[] = operation.split(" ");
                Character toBeSet = splitted[1].charAt(0);
                Long value = getRegisterOrNumberValue(splitted[2]);

                mulPerformed++;
                setRegistryValue(toBeSet, registers.getOrDefault(toBeSet, 0L) * value);
            } else if (operation.startsWith("mod")) {
                String splitted[] = operation.split(" ");
                Character toBeSet = splitted[1].charAt(0);
                Long value = getRegisterOrNumberValue(splitted[2]);

                setRegistryValue(toBeSet, registers.getOrDefault(toBeSet, 0L) % value);
            } else if (operation.startsWith("jnz")) {
                String splitted[] = operation.split(" ");
                Character toBeSet = splitted[1].charAt(0);

                if (toBeSet == '1')
                    registers.put('1', 1L);

                Long offset = getRegisterOrNumberValue(splitted[2]);

                if (registers.getOrDefault(toBeSet, 0L) != 0) {
                    currentOperation += offset;
                    continue;
                }
            }
            currentOperation++;
        }
        return mulPerformed;
    }

}
