package leetcode.dp;

import java.util.*;

public class H_656_CoinPath {
    public static void main(String[] args) {
        System.out.println(cheapestJump(new int[]{1, 2, 4, -1, 2}, 2)); // [1,3,5]
        System.out.println(cheapestJump(new int[]{1, 2, 4, -1, 2}, 1)); // []
        System.out.println(cheapestJump(new int[]{0, 0, 0, 0, 0, 0}, 3)); // [1, 2, 3, 4, 5, 6]
    }

    /**
     * Idea: DP bottom up for easier reconstruct state
     * dp[i] = min(coins[i] + dp[j]), j in range [i, maxJump + i]
     * ---------------------
     * TC: O(n * maxJump)
     * SC: O(n)
     */
    public static List<Integer> cheapestJump(int[] coins, int maxJump) {
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

    // -------------------------------------------------------------
    private int n;
    private int maxJump;
    private int[] coins;
    private int[] memo;

    /**
     * Idea: DP top down with memoization
     * dp[i] = min(coins[i] + dp[j]), j in range [i, maxJump + i]
     * ---------------------
     * TC: O(n * maxJump)
     * SC: O(n)
     */
    public List<Integer> cheapestJump1(int[] coins, int maxJump) {
        this.n = coins.length;
        if (coins[n - 1] == -1) return new ArrayList<>();

        this.maxJump = maxJump;
        this.coins = coins;
        this.memo = new int[coins.length];
        Arrays.fill(memo, 1 << 30);

        dp(0);

        // no valid path
        if (memo[n - 1] == 1 << 30) return new ArrayList<>();

        // reconstruct coin path
        int curCost = memo[0];
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            if (memo[i] == curCost) {
                res.add(i + 1);
                curCost -= coins[i];
            }
        }
        return res;
    }

    private int dp(int i) {
        // base cases
        if (i >= n) return 0;
        if (i == n - 1) return memo[i] = coins[i];
        if (coins[i] == -1) return 1 << 30;
        if (memo[i] != 1 << 30) return memo[i];

        for (int j = i + 1; j <= Math.min(n - 1, i + maxJump); ++j) {
            memo[i] = Math.min(memo[i], coins[i] + dp(j));
        }
        return memo[i];
    }
}
