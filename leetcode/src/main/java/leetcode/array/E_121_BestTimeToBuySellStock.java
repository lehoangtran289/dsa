package leetcode.array;

public class E_121_BestTimeToBuySellStock {
    public static void main(String[] args) {
        System.out.println(maxProfitKadane(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public static int maxProfit(int[] nums) {
        int minSoFar = Integer.MAX_VALUE;

        int maxProfit = 0;
        for (int num : nums) {
            minSoFar = Math.min(minSoFar, num); // min price so far
            maxProfit = Math.max(maxProfit, num - minSoFar); // max prof so far
        }
        return maxProfit;
    }

    /**
     * Kadane approach
     * Idea: We can think of the problem as finding the maximum subarray sum in the difference array
     * -----------------------
     * TC: O(n)
     * SC: O(n)
     */
    public static int maxProfitKadane(int[] prices) {
        int n = prices.length;
        if (n == 1) return 0;

        int[] diff = new int[n];
        for (int i = 1; i < n; ++i) {
            diff[i] = prices[i] - prices[i - 1];
        }

        // apply find max subarray for diff
        int res = diff[0];
        int cur = diff[0];

        for (int i = 1; i < n; ++i) {
            if (cur < 0) cur = diff[i];
            else cur += diff[i];

            res = Math.max(res, cur);
        }

        return res;
    }
}
