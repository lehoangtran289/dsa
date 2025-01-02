package leetcode.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class M_2559_CountVowelStringsInRanges {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                vowelStrings(new String[]{"aba", "bcb", "ece", "aa", "e"}, new int[][]{{0, 2}, {1, 4}, {1, 1}}))
        );
    }

    private static final Set<Character> vowels = new HashSet<>();

    public static int[] vowelStrings(String[] words, int[][] queries) {
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        int[] prefixSum = new int[words.length + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= words.length; ++i) {
            prefixSum[i] = prefixSum[i - 1] + isValid(words[i - 1]);
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            res[i] = prefixSum[queries[i][1] + 1] - prefixSum[queries[i][0]];
        }

        return res;
    }

    public static int isValid(String s) {
        return vowels.contains(s.charAt(0)) && vowels.contains(s.charAt(s.length() - 1)) ? 1 : 0;
    }
}
