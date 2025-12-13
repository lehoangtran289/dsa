package leetcode.dp;

/**
 * Unbounded knapsack with constraint
 */
public class M_3183_TheNumberOfWaysToMakeTheSum {

    /**
     * DP Bottom Up 1D - unbounded knapsack
     * Idea:
     * dp[j] = number of combinations reach amount j
     * dp[j] += dp[j - coin]
     * --------------------------------
     * Note: have to handle coin 4 separately since they can only be used max twice
     * --------------------------------
     * TC: O(n)
     * SC: O(n)
     */
    public int numberOfWays2(int n) {
        final int MOD = 1_000_000_007;

        int[] coins = new int[]{1, 2, 6};
        long[] dp = new long[n + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int j = coin; j <= n; ++j) {
                dp[j] = (dp[j] + dp[j - coin]) % MOD;
            }
        }

        if (n >= 4) dp[n] = (dp[n] + dp[n - 4]) % MOD;
        if (n >= 8) dp[n] = (dp[n] + dp[n - 8]) % MOD;
        return (int) dp[n];
    }

    /**
     * DP Bottom up 2D - unbounded knapsack
     * Idea:
     * dp[i][j] = number of combinations reach amount j, using first i coins
     * dp[i][j] = not take + take
     * = dp[i - 1][j] + dp[i][j - coins[i]]
     * --------------------------------
     * TC: O(n)
     * SC: O(n)
     */
    public int numberOfWays1(int n) {
        final int MOD = 1_000_000_007;

        int[] coins = new int[]{1, 2, 6};
        long[][] dp = new long[3 + 1][n + 1];

        for (int i = 0; i <= 3; ++i) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= 3; ++i) {
            int coin = coins[i - 1];

            for (int j = 1; j <= n; ++j) {
                if (j >= coin) {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - coin]) % MOD;
                } else {
                    dp[i][j] = dp[i - 1][j] % MOD;
                }
            }
        }

        if (n >= 4) dp[3][n] = (dp[3][n] + dp[3][n - 4]) % MOD;
        if (n >= 8) dp[3][n] = (dp[3][n] + dp[3][n - 8]) % MOD;
        return (int) dp[3][n];
    }
}
