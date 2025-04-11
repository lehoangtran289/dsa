package leetcode.dp;

public class M_309_BestTimeToBuyAndSellStockWithCooldown {

    public static void main(String[] args) {
        System.out.println(new M_309_BestTimeToBuyAndSellStockWithCooldown().maxProfit(
                new int[]{1, 2, 3, 0, 2}
        )); // 3
    }

    /**
     * ----------------------------------------------
     * Top-down DP
     * ----------------------------------------------
     * TC O(n)
     * SC O(n)
     */
    private int[] prices;
    private int[][] memo; // max profit at day i

    public int maxProfit(int[] prices) {
        this.prices = prices;
        // [date index][is hold stock][tx remains]
        this.memo = new int[prices.length][2];

        return dp(0, 0);
    }

    private int dp(
            int i,
            int isHoldStock
    ) {
        if (i >= prices.length) {
            return 0;
        }
        if (memo[i][isHoldStock] > 0) {
            return memo[i][isHoldStock];
        }

        if (isHoldStock == 0) {   // not hold => buy or not buy
            memo[i][isHoldStock] = Math.max(
                    dp(i + 1, 1) - prices[i],     // buy
                    dp(i + 1, 0)                  // not buy
            );
        } else {  // hold => sell or not sell
            memo[i][isHoldStock] = Math.max(
                    dp(i + 2, 0) + prices[i], // sell
                    dp(i + 1, 1)                  // not sell
            );
        }

        return memo[i][isHoldStock];
    }
}
