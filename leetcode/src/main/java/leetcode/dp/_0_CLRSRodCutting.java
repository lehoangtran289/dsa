package leetcode.dp;

import java.util.Arrays;

/**
 * CLRS Rod Cutting Problem
 * ------------------------------
 * Given a rod of length n and an array of prices that contains prices of all pieces of size smaller than n.
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
 * ------------------------------
 * Example:
 * prices = [0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30]
 * length = 8
 * max value = 22 (cut into lengths 2 and 6)
 * ------------------------------
 */
public class _0_CLRSRodCutting {

    /**
     * Top down DP
     * --------------------------------
     * TC: O(n^2) - for each length, we try all possible first cuts
     * SC: O(n)
     * --------------------------------
     */
    private int[] price;
    private int[] memo;

    public static void main(String[] args) {
        int[] price = new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

        // top down approach
        _0_CLRSRodCutting obj = new _0_CLRSRodCutting();
        System.out.println(obj.cutRod(price, 2)); // 5
        System.out.println(obj.cutRod(price, 3)); // 8
        System.out.println(obj.cutRod(price, 4)); // 10
        System.out.println(obj.cutRod(price, 7)); // 18
        System.out.println(obj.cutRod(price, 8)); // 22
        System.out.println(obj.cutRod(price, 8)); // 25
        System.out.println(obj.cutRod(price, 10)); // 30

        // Bottom up approach
        System.out.println(obj.cutRod2(price, 2)); // 5
        System.out.println(obj.cutRod2(price, 3)); // 8
        System.out.println(obj.cutRod2(price, 4)); // 10
        System.out.println(obj.cutRod2(price, 7)); // 18
        System.out.println(obj.cutRod2(price, 8)); // 22
        System.out.println(obj.cutRod2(price, 8)); // 25
        System.out.println(obj.cutRod2(price, 10)); // 30
    }

    public int cutRod(int[] price, int n) {
        this.price = price;
        this.memo = new int[n + 1];
        Arrays.fill(memo, -1);

        return dp(n);
    }

    private int dp(int curLength) {
        if (curLength <= 0) return 0;
        if (memo[curLength] != -1) return memo[curLength];

        for (int i = 1; i <= curLength; ++i) {
            memo[curLength] = Math.max(memo[curLength], dp(curLength - i) + price[i]);
        }
        return memo[curLength];
    }

    /**
     * Bottom up DP
     * --------------------------------
     * TC: O(n^2)
     * SC: O(n)
     * --------------------------------
     */
    public int cutRod2(int[] price, int n) {
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                dp[i] = Math.max(dp[i], dp[i - j] + price[j]);
            }
        }
        return dp[n];
    }
}
