package leetcode.string;

public class M_1888_MinimumNumberOfFlipsToMakeTheBinaryStringAlternating {

    /**
     * Greedy + simulation
     * Idea: there are only 2 possible alternating patterns: '010101...' and '101010...'
     * Then we can simulate the type 1 process (move first digit to last position)
     * and update the mismatch count accordingly
     * -----
     * TC: O(n) where n is the length of the input string
     * SC: O(1)
     */
    public int minFlips(String s) {
        int n = s.length();
        int mismatchCount = 0;

        // assuming pattern is '010101...' -> 0 at even, 1 at odd
        for (int i = 0; i < n; ++i) {
            int curDigit = s.charAt(i) - '0';
            if (curDigit != (i % 2)) mismatchCount++;
        }

        // n - count = pattern '10101...'
        int res = Math.min(mismatchCount, n - mismatchCount);

        // simulate type 1 process: moves first digit to last position
        // then check if mismatch digit
        for (int i = 0; i < n; ++i) {
            int curDigit = s.charAt(i) - '0';

            if (curDigit != (i % 2)) mismatchCount--;
            if (curDigit != ((i + n) % 2)) mismatchCount++;

            res = Math.min(res, Math.min(mismatchCount, n - mismatchCount));
        }

        return res;
    }
}
