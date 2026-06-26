package leetcode.string;

import java.util.Arrays;

public class M_3121_CountTheNumberOfSpecialCharactersII {
    public static void main(String[] args) {
        System.out.println(numberOfSpecialChars("aAaA")); // 0
        System.out.println(numberOfSpecialChars("aAAbbbb")); // 1
        System.out.println(numberOfSpecialChars("aAAbB")); // 2
    }

    /**
     * Idea: find last lower index & first upper index
     * ---
     * TC: O(n)
     * SC: O(1)
     */
    public static int numberOfSpecialChars(String word) {
        int[] lastLower = new int[26];
        int[] firstUpper = new int[26];
        Arrays.fill(lastLower, -1);
        Arrays.fill(firstUpper, -1);

        // build first upper and last lower index array
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);

            if (isLower(c)) {
                lastLower[c - 'a'] = i;
            } else {
                if (firstUpper[c - 'A'] == -1) {
                    firstUpper[c - 'A'] = i;
                }
            }
        }

        // build result
        int res = 0;
        for (int i = 0; i < 26; ++i) {
            if (firstUpper[i] != -1 && lastLower[i] != -1 && firstUpper[i] > lastLower[i])
                res++;
        }
        return res;
    }

    private static boolean isLower(char c) {
        return c >= 'a' && c <= 'z';
    }


    /**
     * Using Java APIs
     */
    public static int numberOfSpecialChars2(String word) {
        int res = 0;

        for (char c = 'a'; c <= 'z'; c++) {
            int lastLower = word.lastIndexOf(c);
            int firstUpper = word.indexOf('A' + c - 'a');

            if (lastLower != -1 && firstUpper != -1 && lastLower < firstUpper) res++;
        }

        return res;
    }
}
