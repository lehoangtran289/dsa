package leetcode.dp;

public class H_188_BestTimeToBuyAndSellStockIV {

    public static void main(String[] args) {
        System.out.println(new H_188_BestTimeToBuyAndSellStockIV().maxProfit(
                2,
                new int[]{2, 4, 1, 7, 3, 6, 5}
        )); // 9
    }

    /**
     * ----------------------------------------------
     * Top-down DP
     * Idea:
     *      dfs(i, j, 0) = maximum profit after day i with j completed transactions and holding no stock.
     *      dfs(i, j, 1) = maximum profit after day i with j completed transactions while holding one stock.
     * ----------------------------------------------
     * TC O(n * k)
     * SC O(n * k)
     */
    private int n;
    private int[] prices;
    private int[][][] memo;

    public int maxProfit(int k, int[] prices) {
        this.n = prices.length;
        this.prices = prices;
        this.memo = new int[n + 1][k + 1][2];

        return dp(0, k, 0);
    }

    private int dp(
            int day,
            int txRemain,
            int isHold
    ) {
        if (day >= n || txRemain == 0) return 0;
        if (memo[day][txRemain][isHold] != 0) return memo[day][txRemain][isHold];

        if (isHold == 0) { // skip or buy
            memo[day][txRemain][isHold] = Math.max(
                    dp(day + 1, txRemain, isHold),
                    dp(day + 1, txRemain, 1) - prices[day]
            );
        } else { // skip or sell
            memo[day][txRemain][isHold] = Math.max(
                    dp(day + 1, txRemain, isHold),
                    dp(day + 1, txRemain - 1, 0) + prices[day] // selling completes a tx -> txRemain - 1
            );
        }

        return memo[day][txRemain][isHold];
    }

    /**
     * Bottom up (backward)
     * ---------------------------------------
     * dp[i][j][k] = Maximum profit you can make from day i to the end,
     * j transactionsLeft buys remaining,
     * k = holding = whether you currently own a stock
     */
    public int maxProfit2(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][k + 1][2];

        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j <= k; ++j) {
                // hold stock
                dp[i][j][1] = Math.max(
                        dp[i + 1][j][1], // skip
                        dp[i + 1][j][0] + prices[i] // sell
                );

                // not hold stock
                if (j > 0) {
                    dp[i][j][0] = Math.max(
                            dp[i + 1][j][0], // skip
                            dp[i + 1][j - 1][1] - prices[i]
                    );
                } else {
                    dp[i][j][0] = dp[i + 1][j][0]; // skip
                }
            }
        }
        return dp[0][k][0];
    }

    /**
     * Bottom up (forward)
     * ---------------------------------------
     * dp[i][j][k] = Maximum profit you can make from day 0 to day i,
     * j transactionsLeft buys remaining,
     * k = holding = whether you currently own a stock
     */
    public int maxProfit3(int k, int[] prices) {
        final int INF = 1 << 30;
        int n = prices.length;
        int[][][] dp = new int[n][k + 1][2];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= k; ++j) {
                dp[i][j][0] = -INF;
                dp[i][j][1] = -INF;
            }
        }

        // Base cases for day 0
        for (int j = 0; j <= k; j++) {
            dp[0][j][0] = 0;              // don't buy
            if (j > 0) {
                dp[0][j][1] = -prices[0]; // buy
            }
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= k; ++j) {
                // hold
                dp[i][j][1] = dp[i - 1][j][1]; // skip
                if (j > 0) {
                    dp[i][j][1] = Math.max(
                            dp[i][j][1],
                            dp[i - 1][j - 1][0] - prices[i] // buy
                    );
                }

                // not holding
                dp[i][j][0] = Math.max(
                        dp[i - 1][j][0],
                        dp[i - 1][j][1] + prices[i] // sell
                );
            }
        }

        return dp[n - 1][k][0];
    }
}
