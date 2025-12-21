package leetcode.dp;

public class M_3573_BestTimeToBuyAndSellStockV {

    /**
     * DP Bottom up - Best Time to Buy and Sell Stock V
     * ----------------------------------
     * dp[i][j][state] = max profit at day i with j transactions and state
     * state: 0 - not hold, 1 - hold, 2 - short hold
     * Recurrence relation:
     * dp[i][j][0] = max(
     * dp[i-1][j][0], // skip
     * dp[i-1][j][1] + prices[i], // sell
     * dp[i-1][j][2] - prices[i]  // short buy
     * )
     * dp[i][j][1] = max(
     * dp[i-1][j][1], // skip
     * dp[i-1][j-1][0] - prices[i] // buy
     * )
     * dp[i][j][2] = max(
     * dp[i-1][j][2], // skip
     * dp[i-1][j-1][0] + prices[i] // short sell
     * )
     * ----------------------------------
     * TC: O(n * k)
     * SC: O(n * k)
     */
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        long[][][] dp = new long[n][k + 1][3];

        // base case
        for (int j = 1; j <= k; ++j) {
            dp[0][j][1] = -prices[0];
            dp[0][j][2] = prices[0];
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 1; j <= k; ++j) {
                // not hold -> prev = sell or short buy
                dp[i][j][0] = Math.max(
                        dp[i - 1][j][0], // skip
                        Math.max(
                                dp[i - 1][j][1] + prices[i], // sell
                                dp[i - 1][j][2] - prices[i] // short buy
                        )
                );

                // hold -> prev = buy
                dp[i][j][1] = Math.max(
                        dp[i - 1][j][1], // skip
                        dp[i - 1][j - 1][0] - prices[i] // buy
                );

                // short hold -> prev = buy
                dp[i][j][2] = Math.max(
                        dp[i - 1][j][2], // skip
                        dp[i - 1][j - 1][0] + prices[i] // short sell
                );
            }
        }

        // return max profit at last day with k transactions and not hold state
        return dp[n - 1][k][0];
    }
}
