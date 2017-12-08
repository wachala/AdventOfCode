package pl.wachala.day8;

import java.util.HashMap;
import java.util.Map;

public class IHeardYouLikeRegistersPartTwo extends IHeardYouLikeRegistersPartOne {

    public static long getMaxValue(String commands[]) {
        Map<String, Long> registers = new HashMap<>();

        long maxValueDuringProcessing = 0;

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

            long currentMax = 0;
            for (Map.Entry<String, Long> entry : registers.entrySet())
                if (entry.getValue() > currentMax) currentMax = entry.getValue();

            if (currentMax > maxValueDuringProcessing)
                maxValueDuringProcessing = currentMax;
        }

        return maxValueDuringProcessing;
    }

}
