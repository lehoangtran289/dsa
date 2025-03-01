package leetcode.array.slidingwindow;

import java.util.HashSet;
import java.util.Set;

public class _0_LongestUniqueSubstring {
    public static void main(String[] args) {
        System.out.println(longestSubarrayWithSum("abaabbbbc")); // 3
    }

    // Sliding window technique
    public static int longestSubarrayWithSum(String s) {
        int res = 0;

        int l = 0;
        Set<Character> set = new HashSet<>();
        for (int r = 0; r < s.length(); ++r) {
            char curChar = s.charAt(r);

            while (set.contains(curChar)) {
                set.remove(s.charAt(l));
                l++;
            }

            set.add(curChar);
            res = Math.max(res, r - l + 1);
        }

        return res;
    }
}
