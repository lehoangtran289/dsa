package leetcode.string;

import java.util.*;

public class _FindPairOfStringHasSameCharAtIndex {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                validPair(new String[]{"abc", "def", "ghi", "ced", "mno", "jkl"}))
        ); // ["def", "ced"]
    }

    /**
     * Given a list words of length n, each word has length m.
     * Find pair of words that has same character at same index.
     * 1 <= m, n <= 10^6
     * ------------------
     * Idea: traverse 0 -> m - 1, using a Map<Character, Integer> to store char at index i-th and its word index in list
     * If map.contains(key) valid then we can return this pair.
     * ------------------
     * TC: O(m * n)
     * SC: O(n)
     */
    public static String[] validPair(String[] words) {
        int n = words.length;
        int m = words[0].length();
        String[] res = new String[2];

        for (int i = 0; i < m; ++i) {
            Map<Character, Integer> seen = new HashMap<>();

            for (int j = 0; j < n; ++j) {
                char c = words[j].charAt(i);

                if (seen.containsKey(c)) {
                    res[0] = words[seen.get(c)];
                    res[1] = words[j];
                    return res; // found a valid pair
                }
                seen.put(c, j);
            }
        }

        return res;
    }
}
