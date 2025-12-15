package leetcode.dp;

/**
 * 0/1 Knapsack Problem
 * -----------------------------------------------
 * <a href="https://leetcode.com/discuss/post/1200320/thief-with-a-knapsack-a-series-of-crimes-lcdd/">ref</a>.</a>
 * <a href="https://leetcode.com/discuss/post/1152328/01-knapsack-problem-and-dynamic-programm-4had/">ref2</a>
 * -----------------------------------------------
 * Given n items with weights and values, put these into a knapsack of capacity W to get the maximum total value
 * dp[i][w] = max profit using first i items with current capacity w
 * dp[i][w] = max(
 *      dp[i - 1][w], // not take
 *      dp[i - 1][w - weights[i]] + profits[i] // take
 * )
 * Result: dp[n][W] ~ max profit using first n items with full capacity W
 * -----------------------------------------------
 * |                  | 0 | 1 | 2            | 3             | 4             | 5             |
 * | Weights | Values |
 * |---------|--------|---|---|--------------|---------------|---------------|---------------|
 * | 0       | 0      | 0 | 0 | 0            | 0             | 0             | 0             |
 * | 1       | 6      | 0 | 6 | 6            | 6             | 6             | 6             |
 * | 2       | 10     | 0 | 6 | 6, 10+0 = 10 | 6, 10+6 = 16  | 6, 10+6 = 16  | 6, 10+6 = 16  |
 * | 3       | 12     | 0 | 6 | 10           | 16, 12+0 = 16 | 16, 12+6 = 18 | 16, 12+10 = 22 |
 * -------------------------------
 * Ref: M_416_PartitionEqualSubsetSum:
 * Bottom up DP 2D
 *      dp[i][sum] = can we achieve subset <sum> using first <i> elements
 *      dp[i][sum] = dp[i - 1][sum] || dp[i - 1][sum - nums[i]] // not take || take
 * --------------------------------
 * TC: O(n*sum)
 * SC: O(n*sum)
 * ---
 * Space optimization : Bottom up DP 1D
 * --------------------------------
 * dp[sum] = can we achieve <sum> using cur element
 * e.g:
 *      num = 2 -> traverse (from end) from sum -> 2, if we can achieve <sum>
 *      if we can, that means dp[cur_sum] = true or dp[cur_sum - 2] = true
 *      => dp[cur_sum] = dp[cur_sum] || dp[cur_sum - 2]
 *
 *      initially, dp[0] = true
 *      if we start with num = 2 -> only dp[2] is true
 *      -> So we can reach sum = 2 using num = 2
 *
 *      then num = 3 -> traverse from sum -> 3
 *      dp[5] = dp[2] -> dp[5] = true
 *      dp[3] = dp[0] = true
 *      dp[4] = dp[1] = false
 *      -> So we can reach sum = 2, 3, 5 using num = 2, 3
 * --------------------------------
 * TC: O(n*sum)
 * SC: O(sum)
 *
 */
public class _0_Knapsack01 {
    /**
     * ----------------------------------------------
     * Top-down Knapsack DP
     * ----------------------------------------------
     */
    private int[] weights;
    private int[] profits;
    private int[][] memo;

    public static void main(String[] args) {
        System.out.println(new _0_Knapsack01().knapsack2(
                new int[]{2, 3, 4, 5},
                new int[]{1, 2, 5, 6},
                8
        )); // 8
    }

    /**
     * ----------------------------------------------
     * Bottom-up 1D DP
     * Idea:
     * dp[w] = max profit with current capacity w
     * dp[w] = max(
     *      dp[w], // not take
     *      dp[w - weights[i]] + profits[i] // take
     * )
     * Result: dp[W] ~ max profit with full capacity W
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

    /**
     * ----------------------------------------------
     * Bottom-up 2D DP
     * Idea:
     * dp[i][w] = max profit using first i items with current capacity w
     * dp[i][w] = max(
     *      dp[i - 1][w], // not take
     *      dp[i - 1][w - weights[i]] + profits[i] // take
     * )
     * Result: dp[n][W] ~ max profit using first n items with full capacity W
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
        for (int[] row : dp) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        return dp[n][capacity];
    }

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

}
