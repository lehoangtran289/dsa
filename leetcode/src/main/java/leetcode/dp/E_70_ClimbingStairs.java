package leetcode.dp;

public class E_70_ClimbingStairs {

    /**
     * Top down DP
     */
    private int n;
    private int[] memo;

    public int climbStairs(int n) {
        this.n = n;
        this.memo = new int[n + 1];

        return dp(0);
    }

    private int dp(int cur) {
        if (cur > n) return 0;
        if (cur == n) return 1;
        if (memo[cur] > 0) return memo[cur];

        return memo[cur] = dp(cur + 1) + dp(cur + 2);
    }

    /**
     * Bottom up DP
     */
    public int climbStairs2(int n) {
        if (n <= 1) return 1;

        int[] dp = new int[n + 1]; // number of ways to reach step ith;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
