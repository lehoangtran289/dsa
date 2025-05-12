package leetcode.array.heap;

public class M_122_BestTimeToBuySellStockII {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}));
    }

    /**
     * Peak valley approach
     * -----------------------
     * Try to make profit whenever the price increases
     * TC: O(n)
     * SC: O(1)
     */
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;

        for (int i = 1; i < prices.length; ++i) {
            if (prices[i] > prices[i - 1]) maxProfit += prices[i] - prices[i - 1];
        }

        return maxProfit;
    }
}
