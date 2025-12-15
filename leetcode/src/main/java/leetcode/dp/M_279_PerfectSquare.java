package leetcode.dp;

import java.util.Arrays;

public class M_279_PerfectSquare {

    /**
     * DP Bottom up
     * dp[i] = min number of perfect squares that sum to i
     * ----------------------------------
     * TC: O(n * sqrt(n))
     * SC: O(n)
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1]; // count number of perfect square sum to i-th
        Arrays.fill(dp, 1 << 30);
        dp[0] = 0;

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j * j <= i; ++j) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }
}
