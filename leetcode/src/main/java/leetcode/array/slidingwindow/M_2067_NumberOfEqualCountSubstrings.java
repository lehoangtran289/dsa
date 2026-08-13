package leetcode.array.slidingwindow;

public class M_2067_NumberOfEqualCountSubstrings {
    static void main() {
        System.out.println(equalCountSubstrings("aaabcbbcc", 3)); // 3
    }

    /**
     * Idea: not monotonic to use dynamic sliding window directly. s[i:j] valid doesn't mean s[i:j-1] valid
     * -> Use sliding window fixed length, since distinct chars in range [1, 26] -> length = k * count
     * ---
     * TC: O(26 * n)
     * SC: O(26)
     */
    public static int equalCountSubstrings(String s, int count) {
        int n = s.length();
        int res = 0;

        for (int uniques = 1; uniques <= 26; ++uniques) {
            int windowLength = uniques * count;
            if (windowLength > n) break;

            int[] frequencies = new int[26];

            // process first k
            for (int i = 0; i < windowLength; ++i) {
                frequencies[s.charAt(i) - 'a']++;
            }

            if (isValidSubstring(frequencies, count)) {
                res++;
            }

            // sliding window to process the rest
            for (int i = windowLength; i < n; ++i) {
                frequencies[s.charAt(i - windowLength) - 'a']--;
                frequencies[s.charAt(i) - 'a']++;

                if (isValidSubstring(frequencies, count)) {
                    res++;
                }
            }
        }

        return res;
    }

    private static boolean isValidSubstring(int[] frequencies, int count) {
        for (int frequency : frequencies) {
            if (frequency != 0 && frequency != count) return false;
        }

        return true;
    }
}
