package leetcode.dp;

import java.util.Arrays;

public class E_746_MinCostClimbingStairs {

    /**
     * DP bottom up (tabulation)
     * - Cost to reach the i-th step is the minimum of the cost to reach the (i-1)-th step and (i-2)-th step
     * TC O(n)
     * SC O(n)
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= n; ++i) {
            dp[i] = Math.min(
                    dp[i - 1] + cost[i - 1],
                    dp[i - 2] + cost[i - 2]
            );
        }

        return dp[n];
    }

    /**
     * DP bottom up (tabulation), space optimization
     * TC O(n)
     * SC O(1)
     */
    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int prev1Cost = 0;
        int prev2Cost = 0;

        for (int i = 2; i <= n; ++i) {
            int curCost = Math.min(
                    prev1Cost + cost[i - 1],
                    prev2Cost + cost[i - 2]
            );

            prev2Cost = prev1Cost;
            prev1Cost = curCost;
        }

        return prev1Cost;
    }

    /**
     * DP Top down
     * TC O(n)
     * SC O(1)
     */
    public int minCostClimbingStairs3(int[] cost) {
        int n = cost.length;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);

        return dp(cost, memo, n);
    }

    private int dp(int[] cost, int[] memo, int pos) {
        if (pos == 0 || pos == 1) return 0;
        if (memo[pos] != -1) return memo[pos];

        return memo[pos] = Math.min(
                dp(cost, memo, pos - 1) + cost[pos - 1],
                dp(cost, memo, pos - 2) + cost[pos - 2]
        ) ;
    }
}
