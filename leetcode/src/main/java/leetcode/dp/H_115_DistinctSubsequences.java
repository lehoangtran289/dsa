package leetcode.dp;

import java.util.Arrays;

public class H_115_DistinctSubsequences {
    /**
     * DP Top Down
     * ---
     * TC: O(m * n)
     * SC: O(m * n)
     */
    private String s;
    private String t;
    private int[][] memo;

    static void main() {
        System.out.println(numDistinct("rabbbit", "rabbit")); // 3
    }

    /**
     * DP bottom up
     * ---
     * TC: O(m * n)
     * SC: O(m * n)
     */
    public static int numDistinct(String s, String t) {
        if (s.length() < t.length()) return 0;

        int[][] dp = new int[s.length() + 1][t.length() + 1];

        for (int i = 0; i <= s.length(); ++i) {
            dp[i][t.length()] = 1;
        }

        for (int i = s.length() - 1; i >= 0; --i) {
            for (int j = t.length() - 1; j >= 0; --j) {
                // skip
                dp[i][j] = dp[i + 1][j];

                // take
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] += dp[i + 1][j + 1];
                }
            }
        }

        return dp[0][0];
    }

    public int numDistinct2(String s, String t) {
        if (t.length() > s.length()) return 0;

        this.s = s;
        this.t = t;
        this.memo = new int[s.length() + 1][t.length() + 1];

        for (int i = 0; i <= s.length(); ++i) {
            Arrays.fill(memo[i], -1);
        }

        return dp(0, 0);
    }

    private int dp(int sStart, int tStart) {
        if (tStart == t.length()) return 1;
        if (sStart == s.length()) return 0;
        if (memo[sStart][tStart] != -1) return memo[sStart][tStart];

        // skip
        int res = dp(sStart + 1, tStart);

        if (s.charAt(sStart) == t.charAt(tStart)) {
            res += dp(sStart + 1, tStart + 1);
        }

        return memo[sStart][tStart] = res;
    }
}
