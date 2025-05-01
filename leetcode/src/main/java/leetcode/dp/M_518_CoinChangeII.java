package leetcode.dp;

import java.util.Arrays;

public class M_518_CoinChangeII {
    private int[] coins;
    private int[][] memo;

    /**
     * Top down DP
     * state: [coins index][remain amount]
     * relation: dp(i, amount) = dp(i, amount - coins[i]) + dp(i + 1, amount);
     */
    public int change(int amount, int[] coins) {
        this.coins = coins;
        this.memo = new int[coins.length][amount + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dp(0, amount);
    }

    private int dp(int i, int amount) {
        if (amount == 0) return 1;
        if (i == coins.length) return 0;
        if (memo[i][amount] != -1) return memo[i][amount];

        if (coins[i] > amount) {
            return memo[i][amount] = dp(i + 1, amount);
        }

        return memo[i][amount] = dp(i, amount - coins[i]) + dp(i + 1, amount);
    }
}
