package leetcode.array;

public class M_3335_TotalCharactersInStringAfterTransformationsI {

    /**
     * TC: O(n + 26 * t)
     * SC: O(1)
     */
    public int lengthAfterTransformations(String s, int t) {
        final int MOD = (int) Math.pow(10, 9) + 7;
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        while (t-- > 0) {
            int zFreq = freq[25];

            for (int i = 25; i > 0; --i) {
                freq[i] = freq[i - 1];
            }

            freq[0] = 0; // reset a
            if (zFreq > 0) { // handle z
                freq[0] = zFreq;
                freq[1] = (freq[1] + zFreq) % MOD;
            }
        }

        int res = 0;
        for (int i = 0; i < 26; ++i) {
            res = (res + freq[i]) % MOD;
        }

        return res;
    }
}
