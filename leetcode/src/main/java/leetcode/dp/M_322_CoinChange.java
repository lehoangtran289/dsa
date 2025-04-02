package leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class M_322_CoinChange {
    private int[] coins;
    private Map<Integer, Integer> memo;

    public int coinChange(int[] coins, int amount) {
        this.coins = coins;
        this.memo = new HashMap<>();

        int res = dp(amount);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int dp(int amount) {
        if (amount == 0) return 0;
        if (memo.containsKey(amount)) return memo.get(amount);

        int res = Integer.MAX_VALUE;
        for (int c : coins) {
            if (amount < c) continue;

            int next = dp(amount - c);

            if (next != Integer.MAX_VALUE) {
                res = Math.min(res, next + 1);
                memo.put(amount, res);
            }
        }

        return res;
    }
}
