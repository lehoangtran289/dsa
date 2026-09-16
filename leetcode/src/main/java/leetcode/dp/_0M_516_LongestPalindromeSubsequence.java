package leetcode.dp;

import java.util.Arrays;

public class _0M_516_LongestPalindromeSubsequence {

    /**
     * if s[i] == s[j] : dp[i][j] = dp[i + 1][j - 1] + 2
     * else dp[i][j] = max(dp[i + 1][j], dp[i][j - 1])
     * ---
     * TC: O(n^2)
     * SC: O(n^2)
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n]; // dp[i][j] = max number of palindrome subsequence of s[i...j]

        // traverse from length = 1...n
        for (int len = 1; len <= n; ++len) {
            for (int i = 0; i + len - 1 < n; ++i) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j)) {
                    if (len <= 2) dp[i][j] = len;
                    else dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }

    public int longestPalindromeSubseq3(String s) {
        int n = s.length();
        int[][] dp = new int[n][n]; // dp[i][j] = max number of palindrome subsequence of s[i...j]

        // traverse using 2 pointers bottom up, starting with i = n - 1
        // since we need dp[i + 1][j - 1] when calculating dp[i][j] -> we traverse from bottom up
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                int len = j - i + 1;

                if (s.charAt(i) == s.charAt(j)) {
                    if (len <= 2) dp[i][j] = len;
                    else dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }

            }
        }

        return dp[0][n - 1];
    }

    //-----------------------------------------------------------

    /**
     * TC: O(n^2)
     * SC: O(n^2)
     */
    private String s;
    private int[][] memo;

    public int longestPalindromeSubseq2(String s) {
        int n = s.length();
        this.s = s;
        this.memo = new int[n][n];
        for (int[] row : memo) Arrays.fill(row, -1);

        return dp(0, n - 1);
    }

    private int dp(int i, int j) {
        if (memo[i][j] != -1) return memo[i][j];
        if (i > j) return 0;
        if (i == j) return 1;

        return memo[i][j] = s.charAt(i) == s.charAt(j) ?
                2 + dp(i + 1, j - 1) :
                Math.max(dp(i + 1, j), dp(i, j - 1));
    }
}
