package pl.wachala.day8;

import java.util.HashMap;
import java.util.Map;

public class IHeardYouLikeRegistersPartOne {

    protected static boolean isConditionValid(Map<String, Long> registers, String conditionReg, String operator, long value) {
        long coditionRegVal = registers.getOrDefault(conditionReg, 0L);

        if (">".equals(operator)) return coditionRegVal > value;
        else if ("<".equals(operator)) return coditionRegVal < value;
        else if (">=".equals(operator)) return coditionRegVal >= value;
        else if ("!=".equals(operator)) return coditionRegVal != value;
        else if ("==".equals(operator)) return coditionRegVal == value;
        else if ("<=".equals(operator)) return coditionRegVal <= value;

        return false;
    }

    protected static void modifyRegister(Map<String, Long> registers, String register, String operation, Long operand) {
        Long currentValue = registers.getOrDefault(register, 0L);

        if ("inc".equals(operation))
            registers.put(register, currentValue + operand);
        else if ("dec".equals(operation))
            registers.put(register, currentValue - operand);
    }

    public static long getMaxValue(String commands[]) {
        Map<String, Long> registers = new HashMap<>();

        for (String command : commands) {
            String splitted[] = command.split(" ");

            String toModify = splitted[0];
            String operation = splitted[1];
            Long operand = Long.parseLong(splitted[2]);

            String conditionRegister = splitted[4];
            String operator = splitted[5];
            Long conditionValue = Long.parseLong(splitted[6]);

            if (isConditionValid(registers, conditionRegister, operator, conditionValue))
                modifyRegister(registers, toModify, operation, operand);
        }

        long max = 0;
        for (Map.Entry<String, Long> entry : registers.entrySet())
            if (entry.getValue() > max) max = entry.getValue();

        return max;
    }
}
