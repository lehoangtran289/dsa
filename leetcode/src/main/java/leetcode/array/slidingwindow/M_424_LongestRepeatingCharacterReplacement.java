package leetcode.array.slidingwindow;

public class M_424_LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        System.out.println(characterReplacement("ABAB", 2)); // 4
        System.out.println(characterReplacement("AABABBA", 1)); // 4
    }

    /**
     * Sliding Window
     * TC: O(n)
     * SC: O(1)
     */
    public static int characterReplacement(String s, int k) {
        int res = 0;
        int[] freq = new int[26];
        int maxFreq = 0;
        int l = 0;

        for (int r = 0; r < s.length(); ++r) {
            int rIndex = s.charAt(r) - 'A';
            freq[rIndex]++;

            // the maximum frequency we have seen in any window yet
            maxFreq = Math.max(maxFreq, freq[rIndex]);

            while (l <= r && (r - l + 1) - maxFreq > k) {
                int lIndex = s.charAt(l) - 'A';
                freq[lIndex]--;
                maxFreq = Math.max(maxFreq, freq[lIndex]);
                l++;
            }

            res = Math.max(res, r - l + 1);
        }

        return res;
    }
}
