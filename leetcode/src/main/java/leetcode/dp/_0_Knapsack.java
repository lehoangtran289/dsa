package leetcode.dp;

/**
 * <a href="https://leetcode.com/discuss/post/1200320/thief-with-a-knapsack-a-series-of-crimes-lcdd/">ref</a>.</a>
 */
public class _0_Knapsack {
    public static void main(String[] args) {
        System.out.println(new _0_Knapsack().knapsack(
                new int[]{2, 3, 4, 5},
                new int[]{1, 2, 5, 6},
                8
        )); // 8
    }

    /**
     * ----------------------------------------------
     * Top-down Knapsack DP
     * ----------------------------------------------
     */
    private int[] weights;
    private int[] profits;
    private int[][] memo;

    public int knapsack(int[] weights, int[] profits, int capacity) {
        int n = weights.length;
        this.weights = weights;
        this.profits = profits;
        this.memo = new int[n][capacity + 1]; // int[i][w] = max profit using first i items with capacity w

        return dp(n - 1, capacity);
    }

    private int dp(int i, int w) {
        if (i == 0 || w <= 0) return 0;
        if (memo[i][w] != 0) return memo[i][w];

        if (weights[i] > w) {
            return dp(i - 1, w);
        } else {
            memo[i][w] = Math.max(
                    dp(i - 1, w), // not take
                    dp(i - 1, w - weights[i]) + profits[i] // take
            );
        }

        return memo[i][w];
    }

    /**
     * ----------------------------------------------
     * Bottom-up 2D DP
     * ----------------------------------------------
     */
    public int knapsack2(int[] weights, int[] profits, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; ++i) {
            for (int w = 1; w <= capacity; ++w) { // current capacity
                int index = i - 1; // 0-based index for weights and profits

                if (weights[index] > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(
                        dp[i - 1][w], // not take
                        dp[i - 1][w - weights[index]] + profits[index] // take
                    );
                }
            }
        }

        return dp[n][capacity];
    }

    /**
     * ----------------------------------------------
     * Bottom-up 1D DP
     * ----------------------------------------------
     */
    public int knapsack3(int[] weights, int[] profits, int capacity) {
        int n = weights.length;
        int[] dp = new int[capacity + 1];

        for (int i = 0; i < n; ++i) {
            for (int w = capacity; w >= weights[i]; --w) { // current capacity
                dp[w] = Math.max(
                        dp[w], // not take
                        dp[w - weights[i]] + profits[i] // take
                );
            }
        }

        return dp[capacity];
    }
}
