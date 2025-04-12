package leetcode.dp;

public class M_276_PaintFence {
    public static void main(String[] args) {
        System.out.println(new M_276_PaintFence().numWays(3, 2)); // 6
    }

    /**
     * ----------------------------------------------
     * Top-down DP
     * ----------------------------------------------
     * TC O(n)
     * SC O(n)
     */
    private int k;
    private int[] memo;

    public int numWays(int n, int k) {
        if (n == 1) return k;
        if (n == 2) return k * k;

        this.k = k;
        this.memo = new int[n + 1];
        memo[1] = k;
        memo[2] = k * k;

        return dp(n);
    }

    private int dp(int i) {
        if (memo[i] != 0) return memo[i];

        // #ways when i-th color != (i-1)-th color
        int diffColor = dp(i - 1) * (k - 1);

        // #ways when i-th color == (i-1)-th color
        // (i-2)-th color must be different from (i-1)-th color => dp(i - 2) * (k - 1)
        int sameColor = dp(i - 2) * (k - 1);

        return memo[i] = diffColor + sameColor;
    }

    /**
     * ----------------------------------------------
     * Bottom-up DP
     * ----------------------------------------------
     * TC O(n)
     * SC O(n)
     */
    public int numWays2(int n, int k) {
        if (n == 1) return k;
        if (n == 2) return k * k;

        int[] dp = new int[n + 1];
        dp[1] = k;
        dp[2] = k * k;

        for (int i = 3; i <= n; ++i) {
            dp[i] =
                    dp[i - 1] * (k - 1) +
                    dp[i - 2] * (k - 1);
        }

        return dp[n];
    }

    /**
     * ----------------------------------------------
     * Bottom-up DP, space optimization
     * ----------------------------------------------
     * TC O(n)
     * SC O(1)
     */
    public int numWays3(int n, int k) {
        if (n == 1) return k;
        if (n == 2) return k * k;

        int prevTwo = k;
        int prevOne = k * k;

        for (int i = 3; i <= n; ++i) {
            int cur = (k - 1) * (prevTwo + prevOne);
            prevTwo = prevOne;
            prevOne = cur;
        }

        return prevOne;
    }
}
