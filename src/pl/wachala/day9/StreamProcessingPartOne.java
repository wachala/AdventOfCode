package pl.wachala.day9;

import java.util.Stack;

public class StreamProcessingPartOne {

    public static int processStream(String stream) {
        Stack<Character> stack = new Stack<>();
        int groups = 0;
        int previousStarted = 0;
        boolean skipCharacter = false;

        for (Character c : stream.toCharArray()) {
            if (skipCharacter) {
                skipCharacter = false;
                continue;
            }

            if (stack.size() > 0 && stack.peek() == '<') {
                if (c == '>') stack.pop();
                else if (c == '!') skipCharacter = true;
            } else {
                if (c == '}' && stack.peek() == '{') {
                    stack.pop();
                    groups += previousStarted;
                    previousStarted--;
                } else if (c == '{') {
                    stack.push(c);
                    previousStarted++;
                } else if (c == '<') stack.push(c);
            }
        }
        return groups;
    }

}
