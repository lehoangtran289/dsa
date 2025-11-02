package leetcode.divideconquer;

public class M_395_LongestSubstringWithAtLeastKRepeatingCharacters {

    /**
     * Divide and Conquer
     * Idea: Find the first invalid character (frequency < k) and split the string by this character.
     * Recursively find the longest valid substring in the left and right parts.
     * ---------------------------
     */
    public int longestSubstring(String s, int k) {
        return longestSubstring(s, k, 0, s.length() - 1);
    }

    private int longestSubstring(String s, int k, int start, int end) {
        int[] freq = new int[26];
        for (int i = start; i <= end; ++i) {
            freq[s.charAt(i) - 'a']++;
        }

        for (int i = start; i <= end; ++i) {
            int curFreq = freq[s.charAt(i) - 'a'];

            if (curFreq != 0 && curFreq < k) {
                int leftEnd = i - 1;
                int rightStart = i;

                // skip all invalid chars
                while (rightStart <= end && s.charAt(rightStart) == s.charAt(i)) {
                    rightStart++;
                }

                return Math.max(
                        longestSubstring(s, k, start, leftEnd),
                        longestSubstring(s, k, rightStart, end)
                );
            }
        }

        // no invalid chars found -> whole array is longest substring
        return end - start + 1;
    }
}
