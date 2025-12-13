package leetcode.dp;

public class M_343_IntegerBreak {
    public static void main(String[] args) {
        System.out.println(integerBreak(10)); // 36
    }

    /**
     * DP Bottom up - Knapsack
     * ----------------------------------
     * dp[i] = max product of integer i
     * Recurrence relation:
     * dp[i] = max(j * (i - j), j * dp[i - j])
     * ----------------------------------
     * TC: O(n^2)
     * SC: O(n)
     */
    public static int integerBreak(int n) {
        if (n <= 3) return n - 1;

        int[] dp = new int[n + 1]; // max product of integer i
        dp[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j < i; ++j) {
                int noBreak = j * (i - j);
                int furtherBreak = j * dp[i - j];

                dp[i] = Math.max(dp[i], Math.max(noBreak, furtherBreak));
            }
        }

        return dp[n];
    }
}
