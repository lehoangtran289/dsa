package leetcode.dp;

public class E_746_MinCostClimbingStairs {

    /**
     * DP bottom up (tabulation)
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1]; // minimum cost to reach the i-th step
        dp[0] = 0; // start from 0 -> cost = 0
        dp[1] = 0; // start from 1 -> cost = 0

        for (int i = 2; i < n + 1; ++i) {
            dp[i] = Math.min(
                    dp[i - 1] + cost[i - 1], // take 1 step
                    dp[i - 2] + cost[i - 2] // take 2 steps
            );
        }

        return dp[n];
    }
}
