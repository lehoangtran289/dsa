package leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Unbound knapsack
 */
public class M_322_CoinChange {
    public static void main(String[] args) {
        M_322_CoinChange solution = new M_322_CoinChange();
        System.out.println(solution.coinChange(new int[]{1, 2, 5}, 11)); // 3
        System.out.println(solution.coinChange(new int[]{2}, 3)); // -1
    }

    /**
     * DP Bottom up 2D
     * Idea:
     * dp[i][j] = min coins use to reach amount j, using first i coins
     * dp[i][j] = min(dp[i - 1][j], dp[i][j - coins[i]]) ~ not take / take
     * Note: dp[i][j - coins[i]] since we can reuse coins (unbounded)
     * --------------------
     */
    public int coinChange3(int[] coins, int amount) {
        final int INF = 1 << 30;
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], INF);
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; ++i) {
            int coinValue = coins[i - 1];

            for (int j = 1; j <= amount; ++j) {
                if (j >= coinValue) {
                    dp[i][j] = Math.min(
                            dp[i - 1][j], // not take
                            dp[i][j - coinValue] + 1 // take
                    );
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][amount] == INF ? -1 : dp[n][amount];
    }

    /**
     * DP Bottom Up 1D
     * -----------------------
     * TC: O(S * n) - S is the amount, n is the number of coins
     * SC: O(S)
     */
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 10];
        Arrays.fill(dp, amount + 10); // fill with max value
        dp[0] = 0;

        // for each amount n, try each coin c, then recursively find best for amount n - c
        // order of loops is not important
        for (int i = 1; i <= amount; ++i) {
            for (int c : coins) {
                if (i >= c)
                    dp[i] = Math.min(dp[i], dp[i - c] + 1);
            }
        }


        return dp[amount] > amount ? -1 : dp[amount];
    }

    private int[] coins;
    private Map<Integer, Integer> memo;

    /**
     *  DP Top Down with Memoization
     *  Relation: F(S) = min(F(S - c_i)) + 1, for i = 0 -> n - 1
     *  -----------------------
     *  TC: O(S * n) - S is the amount, n is the number of coins
     *  SC: O(S)
     */
    public int coinChange(int[] coins, int amount) {
        this.coins = coins;
        this.memo = new HashMap<>();

        return dp(amount);
    }

    private int dp(int amount) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        if (memo.containsKey(amount)) return memo.get(amount);

        int res = Integer.MAX_VALUE;
        for (int c : coins) {
            int best = dp(amount - c);

            if (best < res && best >= 0) {
                res = best + 1;
            }
        }
        memo.put(amount, res == Integer.MAX_VALUE ? -1 : res);
        return memo.get(amount);
    }
}
