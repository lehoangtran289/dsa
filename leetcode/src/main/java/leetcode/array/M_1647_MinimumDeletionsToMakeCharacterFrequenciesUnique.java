package leetcode.array;

import java.util.HashSet;
import java.util.Set;

public class M_1647_MinimumDeletionsToMakeCharacterFrequenciesUnique {
    public static void main(String[] args) {
        System.out.println(minDeletions("aaabbbcc")); // 2
    }

    /**
     * Greedy approach
     * Idea: Count frequency, then try decrement frequency
     * --------------------
     * TC: O(N)
     * SC: O(1)
     */
    public static int minDeletions(String s) {
        // Count frequency of each character
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // process del frequency
        int res = 0;
        Set<Integer> seen = new HashSet<>();

        for (int f : freq) {
            while (f > 0 && seen.contains(f)) {
                f--;
                res++;
            }
            seen.add(f);
        }
        return res;
    }
}
