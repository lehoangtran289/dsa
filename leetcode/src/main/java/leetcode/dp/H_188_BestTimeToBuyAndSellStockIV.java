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
     * ----------------------------------------------
     * TC O(n * k)
     * SC O(n * k)
     */
    private int[] prices;
    private int[][][] memo; // max profit at day i

    public int maxProfit(int k, int[] prices) {
        this.prices = prices;
        // [date index][is hold stock][tx remains]
        this.memo = new int[prices.length][2][k + 1];

        return dp(0, 0, k);
    }

    private int dp(
            int i,
            int isHoldStock,
            int txRemain
    ) {
        if (i >= prices.length || txRemain == 0) {
            return 0;
        }
        if (memo[i][isHoldStock][txRemain] > 0) {
            return memo[i][isHoldStock][txRemain];
        }

        if (isHoldStock == 0) {                                           // not hold => buy or not buy
            memo[i][isHoldStock][txRemain] = Math.max(
                    dp(i + 1, 1, txRemain) - prices[i],     // buy
                    dp(i + 1, 0, txRemain)                  // not buy
            );
        } else {                                                                   // hold => sell or not sell
            memo[i][isHoldStock][txRemain] = Math.max(
                    dp(i + 1, 0, txRemain - 1) + prices[i], // sell
                    dp(i + 1, 1, txRemain)                           // not sell
            );
        }

        return memo[i][isHoldStock][txRemain];
    }
}
