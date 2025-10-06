package leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class M_322_CoinChange {
    public static void main(String[] args) {
        M_322_CoinChange solution = new M_322_CoinChange();
        System.out.println(solution.coinChange(new int[]{1, 2, 5}, 11)); // 3
        System.out.println(solution.coinChange(new int[]{2}, 3)); // -1
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

    /**
     * DP Bottom Up
     * -----------------------
     * TC: O(S * n) - S is the amount, n is the number of coins
     * SC: O(S)
     */
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 10];
        Arrays.fill(dp, amount + 10); // fill with max value
        dp[0] = 0;

        for (int i = 1; i <= amount; ++i) {
            for (int c : coins) {
                if (i >= c)
                    dp[i] = Math.min(dp[i], dp[i - c] + 1);
            }
        }


        return dp[amount] > amount ? -1 : dp[amount];
    }
}
