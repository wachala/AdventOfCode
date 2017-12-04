package pl.wachala.day4;

import java.util.HashSet;
import java.util.Set;

public class HighEntropyPassphrasesPartOne {

    private static boolean isPassPhraseValid(String passphrase) {
        String splitted[] = passphrase.trim().replaceAll(" +", " ").split(" ");
        Set<String> words = new HashSet<>();

        for (String word : splitted) {
            if (words.contains(word)) return false;

            words.add(word);
        }

        return true;
    }

    public static long getNumberOfValidPassphrases(String phrases) {
        String lines[] = phrases.split("\n");
        long count = 0;

        for (String line : lines)
            if (isPassPhraseValid(line)) count++;

        return count;
    }

}
