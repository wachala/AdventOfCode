package pl.wachala.day9;

import java.util.Stack;

public class StreamProcessingPartTwo {

    public static int processStream(String stream) {
        Stack<Character> stack = new Stack<>();
        boolean skipCharacter = false;
        int garbageCharacters = 0;

        for (Character c : stream.toCharArray()) {
            if (skipCharacter) {
                skipCharacter = false;
                continue;
            }

            if (stack.size() > 0 && stack.peek() == '<') {
                if (c == '>') stack.pop();
                else if (c == '!') skipCharacter = true;
                else garbageCharacters++;
            } else {
                if (c == '}' && stack.peek() == '{') stack.pop();
                else if (c == '{') stack.push(c);
                else if (c == '<') stack.push(c);
            }
        }
        return garbageCharacters;
    }

}
