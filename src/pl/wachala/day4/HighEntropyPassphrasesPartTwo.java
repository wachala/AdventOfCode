package pl.wachala.day4;

public class HighEntropyPassphrasesPartTwo {

    private static int[] getWordStats(String stats) {
        int result[] = new int[26];

        for (char c : stats.toCharArray())
            result[c - 'a']++;

        return result;
    }

    private static boolean wordStatsEquals(int first[], int second[]) {
        for (int i = 0; i < 26; i++)
            if (first[i] != second[i]) return false;

        return true;
    }

    private static boolean isPassPhraseValid(String passphrase) {
        String splitted[] = passphrase.trim().replaceAll(" +", " ").split(" ");
        int wordStats[][] = new int[splitted.length][26];

        for (int i = 0; i < splitted.length; i++)
            wordStats[i] = getWordStats(splitted[i]);

        for (int i = 0; i < splitted.length - 1; i++) {
            for (int j = i + 1; j < splitted.length; j++) {
                if (splitted[i].length() != splitted[j].length()) continue;
                if (wordStatsEquals(wordStats[i], wordStats[j])) return false;
            }
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
