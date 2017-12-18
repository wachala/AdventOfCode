package pl.wachala.day18;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class DuetPartTwo {
    private Map<Character, Long> registers;
    private int currentOperation;
    private int sent;
    private boolean blocked;
    private Queue<Long> queue;
    private DuetPartTwo duet;

    private int getSent() {
        return sent;
    }

    private boolean isBlocked() {
        return blocked;
    }

    private void setDuet(DuetPartTwo duet) {
        this.duet = duet;
    }

    private DuetPartTwo(Long id) {
        registers = new HashMap<>();
        queue = new LinkedList<>();
        registers.put('p', id);
    }

    private long getRegisterOrNumberValue(String input) {
        char firstChar = input.charAt(0);

        if (firstChar >= 'a' && firstChar <= 'z')
            return registers.getOrDefault(firstChar, 0L);

        return Long.parseLong(input);
    }

    private void receive(long value) {
        if (blocked)
            blocked = false;

        queue.add(value);
    }

    private void send(long value) {
        sent++;
        duet.receive(value);
    }

    private void performOperations(String input[]) {
        while (currentOperation >= 0 && currentOperation < input.length) {
            String operation = input[currentOperation];

            if (operation.startsWith("snd")) {
                String register = operation.split(" ")[1];
                Long value = getRegisterOrNumberValue(register);

                send(value);
            } else if (operation.startsWith("set")) {
                String splitted[] = operation.split(" ");
                Character toBeSet = splitted[1].charAt(0);
                Long value = getRegisterOrNumberValue(splitted[2]);

                registers.put(toBeSet, value);
            } else if (operation.startsWith("add")) {
                String splitted[] = operation.split(" ");
                Character toBeSet = splitted[1].charAt(0);
                Long value = getRegisterOrNumberValue(splitted[2]);

                registers.put(toBeSet, registers.getOrDefault(toBeSet, 0L) + value);
            } else if (operation.startsWith("mul")) {
                String splitted[] = operation.split(" ");
                Character toBeSet = splitted[1].charAt(0);
                Long value = getRegisterOrNumberValue(splitted[2]);

                registers.put(toBeSet, registers.getOrDefault(toBeSet, 0L) * value);
            } else if (operation.startsWith("mod")) {
                String splitted[] = operation.split(" ");
                Character toBeSet = splitted[1].charAt(0);
                Long value = getRegisterOrNumberValue(splitted[2]);

                registers.put(toBeSet, registers.getOrDefault(toBeSet, 0L) % value);
            } else if (operation.startsWith("jgz")) {
                String splitted[] = operation.split(" ");
                Character toBeSet = splitted[1].charAt(0);

                if (toBeSet == '1')
                    registers.put('1', 1L);

                Long offset = getRegisterOrNumberValue(splitted[2]);

                if (registers.getOrDefault(toBeSet, 0L) > 0) {
                    currentOperation += offset;
                    continue;
                }
            } else if (operation.startsWith("rcv")) {
                Character register = operation.split(" ")[1].charAt(0);

                if (queue.isEmpty()) {
                    blocked = true;
                    return;
                }

                Long val = queue.poll();
                registers.put(register, val);
            }

            currentOperation++;
        }
    }

    public static void main(String[] args) {
        DuetPartTwo first = new DuetPartTwo(0L);
        DuetPartTwo second = new DuetPartTwo(1L);

        first.setDuet(second);
        second.setDuet(first);

        String input2[] = ("set i 31\n" +
                "set a 1\n" +
                "mul p 17\n" +
                "jgz p p\n" +
                "mul a 2\n" +
                "add i -1\n" +
                "jgz i -2\n" +
                "add a -1\n" +
                "set i 127\n" +
                "set p 952\n" +
                "mul p 8505\n" +
                "mod p a\n" +
                "mul p 129749\n" +
                "add p 12345\n" +
                "mod p a\n" +
                "set b p\n" +
                "mod b 10000\n" +
                "snd b\n" +
                "add i -1\n" +
                "jgz i -9\n" +
                "jgz a 3\n" +
                "rcv b\n" +
                "jgz b -1\n" +
                "set f 0\n" +
                "set i 126\n" +
                "rcv a\n" +
                "rcv b\n" +
                "set p a\n" +
                "mul p -1\n" +
                "add p b\n" +
                "jgz p 4\n" +
                "snd a\n" +
                "set a b\n" +
                "jgz 1 3\n" +
                "snd b\n" +
                "set f 1\n" +
                "add i -1\n" +
                "jgz i -11\n" +
                "snd a\n" +
                "jgz f -16\n" +
                "jgz a -19").split("\n");

        while (!first.isBlocked() || !second.isBlocked()) {
            if (first.isBlocked() && !first.queue.isEmpty())
                first.blocked = false;
            else
                first.performOperations(input2);

            if (second.isBlocked() && !second.queue.isEmpty())
                second.blocked = false;
            else
                second.performOperations(input2);
        }

        System.out.println(second.getSent());
    }
}
