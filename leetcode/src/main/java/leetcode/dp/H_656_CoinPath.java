package leetcode.dp;

import java.util.*;

public class H_656_CoinPath {

    /**
     * Idea: DP bottom up for easier reconstruct state
     * dp[i] = min(coins[i] + dp[j]), j in range [i, maxJump + i]
     * ---------------------
     * TC: O(n * maxJump)
     * SC: O(n)
     */
    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        int n = coins.length;
        if (coins[n - 1] == -1) return new ArrayList<>();

        // dp[i] = min cost from i to n
        int[] dp = new int[n + 1];

        Arrays.fill(dp, 1 << 30); // +inf
        dp[n - 1] = coins[n - 1];

        for (int i = n - 2; i >= 0; --i) {
            if (coins[i] == -1) continue;

            for (int j = i + 1; j <= Math.min(n - 1, i + maxJump); ++j) {
                dp[i] = Math.min(dp[i], coins[i] + dp[j]);
            }
        }

        // no valid path
        if (dp[0] == 1 << 30) return new ArrayList<>();

        // reconstruct coin path
        int curCost = dp[0];
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            if (dp[i] == curCost) {
                res.add(i + 1);
                curCost -= coins[i];
            }
        }
        return res;
    }
}
