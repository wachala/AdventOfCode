package pl.wachala.day18;

import java.util.HashMap;
import java.util.Map;

public class DuetPartOne {
    private static Map<Character, Long> registers;

    private static long getRegisterOrNumberValue(String input) {
        char firstChar = input.charAt(0);

        if(firstChar >= 'a' && firstChar<='z')
            return registers.getOrDefault(firstChar, 0L);

        return Long.parseLong(input);
    }

    public static long getRecoveredFrequency(String input[]) {
        long lastNonZeroFrequency = 0;
        long lastRecoveredFrequency = 0;
        registers = new HashMap<>();

        int currentOperation = 0;

        while (currentOperation>=0 && currentOperation<input.length) {
            String operation = input[currentOperation];

            if(operation.startsWith("snd")) {
                Character register = operation.split(" ")[1].charAt(0);
                Long value = registers.getOrDefault(register, 0L);

                if(value!=0)
                    lastNonZeroFrequency = value;
            }
            else if (operation.startsWith("set")) {
                String splitted[] = operation.split(" ");
                Character toBeSet = splitted[1].charAt(0);
                Long value = getRegisterOrNumberValue(splitted[2]);

                registers.put(toBeSet, value);
            }
            else if (operation.startsWith("add")) {
                String splitted[] = operation.split(" ");
                Character toBeSet = splitted[1].charAt(0);
                Long value = getRegisterOrNumberValue(splitted[2]);

                registers.put(toBeSet, registers.getOrDefault(toBeSet, 0L) + value);
            }
            else if(operation.startsWith("mul")){
                String splitted[] = operation.split(" ");
                Character toBeSet = splitted[1].charAt(0);
                Long value = getRegisterOrNumberValue(splitted[2]);

                registers.put(toBeSet, registers.getOrDefault(toBeSet, 0L) * value);
            }
            else if(operation.startsWith("mod")) {
                String splitted[] = operation.split(" ");
                Character toBeSet = splitted[1].charAt(0);
                Long value = getRegisterOrNumberValue(splitted[2]);

                registers.put(toBeSet, registers.getOrDefault(toBeSet, 0L)%value);
            }
            else if(operation.startsWith("rcv")) {
                Character register = operation.split(" ")[1].charAt(0);

                if(registers.getOrDefault(register, 0L) > 0L) {
                    lastRecoveredFrequency = lastNonZeroFrequency;
                    break;
                }
            }
            else if(operation.startsWith("jgz")) {
                String splitted[] = operation.split(" ");
                Character toBeSet = splitted[1].charAt(0);
                Long offset = getRegisterOrNumberValue(splitted[2]);

                if(registers.getOrDefault(toBeSet, 0L) > 0) {
                    currentOperation += offset;
                    continue;
                }
            }
            currentOperation++;
        }

        return lastRecoveredFrequency;
    }

}
