package leetcode.divideconquer;

public class M_395_LongestSubstringWithAtLeastKRepeatingCharacters {

    /**
     * Divide and Conquer
     * Idea: Find the first invalid character (frequency < k) and split the string by this character.
     * Recursively find the longest valid substring in the left and right parts.
     * ---------------------------
     * TC: max O(n^2)
     */
    public int longestSubstring(String s, int k) {
        return helper(s, k, 0, s.length() - 1);
    }

    private int helper(String s, int k, int left, int right) {
        if (left > right) return 0;

        // build frequency array
        int[] freq = new int[26];
        for (int i = left; i <= right; ++i) {
            freq[s.charAt(i) - 'a']++;
        }

        // process valid substring
        for (int i = left; i <= right; ++i) {
            char c = s.charAt(i);

            if (freq[c - 'a'] < k) {
                int leftEnd = i - 1;
                int rightStart = i + 1;

                // skip all invalid chars
                while (rightStart < s.length() && freq[s.charAt(rightStart) - 'a'] < k) {
                    rightStart++;
                }

                int leftResult = helper(s, k, left, leftEnd);
                int rightResult = helper(s, k, rightStart, right);

                return Math.max(leftResult, rightResult);
            }
        }

        // If we reach here, it means the entire substring is valid
        return right - left + 1;
    }
}
