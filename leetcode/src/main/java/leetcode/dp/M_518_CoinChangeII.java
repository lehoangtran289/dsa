package leetcode.dp;

import java.util.Arrays;

/**
 * Unbound knapsack
 */
public class M_518_CoinChangeII {
    /**
     * DP Bottom up 2D - unbounded knapsack
     * Idea:
     * dp[i][j] = number of combinations reach amount j, using first i coins
     * dp[i][j] = not take + take
     * = dp[i - 1][j] + dp[i][j - coins[i]]
     * --------------------------------
     * TC: O(n * amount)
     * SC: O(n * amount)
     */
    public int change3(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        for (int i = 0; i <= n; ++i) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; ++i) {
            int coinValue = coins[i - 1];

            for (int j = 1; j <= amount; ++j) {
                if (j >= coinValue) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coinValue];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][amount];
    }

    /**
     * Top down DP
     * state: [coins index][remain amount]
     * relation: dp(i, amount) = dp(i, amount - coins[i]) + dp(i + 1, amount);
     */
    private int[] coins;
    private int[][] memo;

    public int change(int amount, int[] coins) {
        this.coins = coins;
        this.memo = new int[coins.length][amount + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dp(0, amount);
    }

    private int dp(int i, int amount) {
        if (amount == 0) return 1;
        if (i == coins.length) return 0;
        if (memo[i][amount] != -1) return memo[i][amount];

        if (coins[i] > amount) {
            return memo[i][amount] = dp(i + 1, amount);
        }

        return memo[i][amount] = dp(i, amount - coins[i]) + dp(i + 1, amount);
    }
}
