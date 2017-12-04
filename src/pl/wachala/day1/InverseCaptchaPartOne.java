package pl.wachala.day1;

public class InverseCaptchaPartOne {

    public static long inverse(String input) {
        long sum = 0;

        for (int i = 0; i < input.length() - 1; i++) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                sum += input.charAt(i) - '0';
            }
        }

        if (input.length() > 1 && input.charAt(input.length() - 1) == input.charAt(0)) {
            sum += input.charAt(0) - '0';
        }

        return sum;
    }

}
