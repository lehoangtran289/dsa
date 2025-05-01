package leetcode.array.slidingwindow;

public class M_567_PermutationInString {

    /**
     * Check if s1's permutation is a substring of s2
     * Sliding window approach
     * TC: O(n)
     * SC: O(1)
     */
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        // base case
        if (len1 > len2) return false;

        // get frequency of s1
        int[] freq1 = new int[26];
        for (char c : s1.toCharArray()) {
            freq1[c - 'a']++;
        }

        // process first s1.length window of s2
        int[] freq2 = new int[26];
        for (int i = 0; i < len1; ++i) {
            freq2[s2.charAt(i) - 'a']++;
        }
        if (isPermute(freq1, freq2)) return true;

        // process rest of s2
        for (int i = len1; i < len2; ++i) {
            freq2[s2.charAt(i) - 'a']++;
            freq2[s2.charAt(i - s1.length()) - 'a']--;
            if (isPermute(freq1, freq2)) return true;
        }

        return false;
    }

    /**
     * Check permutation
     */
    private boolean isPermute(int[] freq1, int[] freq2) {
        for (int i = 0; i < freq1.length; ++i) {
            if (freq1[i] != freq2[i]) return false;
        }
        return true;
    }
}
