package leetcode.array.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_438_FindAllAnagramsInAString {
    static void main() {
        System.out.println(findAnagrams("cbaebabacd", "abc")); // [0, 6]
    }

    /**
     * TC: O(n)
     * SC: O(n)
     */
    public static List<Integer> findAnagrams(String s, String p) {
        int pLength = p.length(), sLength = s.length();
        List<Integer> res = new ArrayList<>();

        if (pLength > sLength) return res;

        // build target char frequencies for anagram checking
        int[] targetFreq = new int[26];
        for (char c : p.toCharArray()) {
            targetFreq[c - 'a']++;
        }

        // sliding window over s to find all anagrams
        int[] currentFreq = new int[26];

        // process first k
        for (int i = 0; i < pLength; ++i) {
            currentFreq[s.charAt(i) - 'a']++;
        }
        if (Arrays.equals(currentFreq, targetFreq)) {
            res.add(0);
        }

        // sliding window the rest
        for (int i = pLength; i < s.length(); ++i) {
            currentFreq[s.charAt(i - pLength) - 'a']--;
            currentFreq[s.charAt(i) - 'a']++;

            if (Arrays.equals(currentFreq, targetFreq)) {
                res.add(i - pLength + 1);
            }
        }

        return res;
    }
}
