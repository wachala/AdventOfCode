package pl.wachala.day1;

public class InverseCaptchaPartTwo {

    public static long inverse(String input) {
        long sum = 0;
        int distance = input.length() / 2;

        for (int i = 0; i < input.length(); i++) {
            int matching = i + distance;

            if (matching > input.length() - 1) {
                matching = matching - input.length();
            }

            if (input.charAt(i) == input.charAt(matching)) {
                sum += input.charAt(i) - '0';
            }

        }

        return sum;
    }

}
