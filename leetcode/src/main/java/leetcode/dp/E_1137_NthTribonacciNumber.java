package leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class E_1137_NthTribonacciNumber {
    /**
     * DP bottom up (tabulation)
     */
    public int tribonacci1(int n) {
        if (n == 0) return 0;
        if (n <= 2) return 1;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }

    /**
     * DP Top Down Memoization
     */
    private final Map<Integer, Integer> memo = new HashMap<>();

    public int tribonacci2(int n) {
        return dp(n);
    }

    public int dp(int n) {
        if (n == 0) return 0;
        if (n <= 2) return 1;

        if (!memo.containsKey(n)) {
            memo.put(n, dp(n - 1) + dp(n - 2) + dp(n - 3));
        }
        return memo.get(n);
    }
}
