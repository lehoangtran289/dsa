package leetcode.dp;

import java.util.Arrays;

public class M_714_BestTimeToBuyAndSellStockWithTransactionFee {

    private int[] prices;
    private int fee;
    private int[][] memo;

    /**
     * Top down DP approach
     * --------------------------
     * state: dp(i, j) - max profit at i-th, j = is hold stock
     * relation:
     * if j = 1 ~ hold stock -> dp(i, 1) = max(dp(i + 1, 1) + prices[i] - fee, dp(i + 1, 0))
     * if j = 0 ~ not hold stock -> dp(i, 0) = max(dp(i + 1, 1) - prices[i], dp(i + 1, 0))
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        this.memo = new int[n + 1][2]; // max profit , hold/not hold
        this.prices = prices;
        this.fee = fee;

        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }

        return dp(0, 0);
    }

    private int dp(int i, int isHold) {
        if (i >= prices.length) return 0;
        if (memo[i][isHold] != -1) return memo[i][isHold];

        if (isHold == 1) {
            return memo[i][isHold] = Math.max(
                    dp(i + 1, 0) + prices[i] - fee,
                    dp(i + 1, 1)
            );
        } else { // isHold == 0
            return memo[i][isHold] = Math.max(
                    dp(i + 1, 1) - prices[i],
                    dp(i + 1, 0)
            );
        }
    }
}
