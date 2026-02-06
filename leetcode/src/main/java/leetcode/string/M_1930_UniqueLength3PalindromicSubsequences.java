package leetcode.string;

import java.util.Arrays;

/**
 * Counts the number of unique palindromic subsequences of length 3 in a given string.
 */
public class M_1930_UniqueLength3PalindromicSubsequences {
    public static void main(String[] args) {
        System.out.println(countPalindromicSubsequence("bbcbaba"));
    }

    /**
     * Simulation: choose first and last index of a char and count all unique chars in between
     * -----------------------------
     * TC: O(26) + O(n) + O(26 * n) -> O(n)
     * SC: O(26 * 2) -> O(1)
     */
    public static int countPalindromicSubsequence(String s) {
        int n = s.length();
        int[][] letterIndexes = new int[26][2]; // each character, we store first and last index

        // O(26)
        for (int i = 0; i < 26; ++i) {
            Arrays.fill(letterIndexes[i], -1);
        }

        // O(n)
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);

            if (letterIndexes[c - 'a'][0] == -1) {
                letterIndexes[c - 'a'][0] = i;
            } else {
                letterIndexes[c - 'a'][1] = i;
            }
        }

        // O(26 * n) -> O(n)
        int res = 0;
        for (int i = 0; i < 26; ++i) {
            int firstIndex = letterIndexes[i][0];
            int lastIndex = letterIndexes[i][1];

            if (firstIndex == -1 || lastIndex == -1) continue;

            boolean[] seen = new boolean[26];

            for (int j = firstIndex + 1; j < lastIndex; ++j) {
                char c = s.charAt(j);

                if (!seen[c - 'a']) {
                    res++;
                    seen[c - 'a'] = true;
                }
            }
        }

        return res;
    }
}
