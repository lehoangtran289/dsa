package leetcode.dp;

import java.util.Arrays;

/**
 * 1143. Longest Common Subsequence (LCS)
 * --------------------------
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * If there is no common subsequence, return 0.
 */
public class _0M_1143_LongestCommonSubsequence {
    /**
     * Top down DP approach
     * ----------------------------------
     * TC: O(MN)
     * SC: O(MN)
     */
    private int[][] memo;
    private String s1;
    private String s2;

    public static void main(String[] args) {
        _0M_1143_LongestCommonSubsequence solver = new _0M_1143_LongestCommonSubsequence();

        System.out.println("DP Bottom Up Approach:");
        System.out.println(solver.longestCommonSubsequence("abcde", "ace")); // 3
        System.out.println(solver.longestCommonSubsequence("530", "583")); // 2

        System.out.println("\nDP Bottom Up Approach with path construct:");
        System.out.println(solver.longestCommonSubsequence2("abcde", "ace")); // 3
        System.out.println(solver.longestCommonSubsequence2("530", "583")); // 2

        System.out.println("\nDP Top Down Approach:");
        System.out.println(solver.longestCommonSubsequence_withPath("abcde", "ace")); // 3
        System.out.println(solver.longestCommonSubsequence_withPath("530", "583")); // 2
    }

    /**
     * DP bottom up
     * ----------------------------------
     * dp[i][j] = LCS for first i chars of s1 and first j chars of s2
     * Recurrence relation:
     * dp[i][j] = dp[i - 1][j - 1] + 1                 if s1[i] == s2[j]
     * dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])      if s1[i] != s2[j]
     * ----------------------------------
     * TC: O(mn) - s1.length() * s2.length()
     * SC: O(mn)
     */
    public int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1]; // dp[i][j] = LCS for first i chars of s1 and first j chars of s2

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public int longestCommonSubsequence_withPath(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1]; // dp[i][j] = LCS for first i chars of s1 and first j chars of s2

        String[][] dir = new String[m + 1][n + 1]; // to reconstruct the optimal path
        dir[0][0] = "START";

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    dir[i][j] = "DIAG";
                } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                    dp[i][j] = dp[i - 1][j];
                    dir[i][j] = "UP";
                } else {
                    dp[i][j] = dp[i][j - 1];
                    dir[i][j] = "LEFT";
                }
            }
        }
        // Print the LCS path
        for (String[] row : dir) {
            System.out.println(Arrays.toString(row));
        }

        return dp[m][n];
    }

    public int longestCommonSubsequence2(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        memo = new int[s1.length() + 1][s2.length() + 1];

        for (int[] row : memo) Arrays.fill(row, -1);

        return dp(0, 0);
    }

    private int dp(int p1, int p2) {
        if (p1 >= s1.length() || p2 >= s2.length()) return 0;
        if (memo[p1][p2] != -1) return memo[p1][p2];

        if (s1.charAt(p1) == s2.charAt(p2)) {
            memo[p1][p2] = dp(p1 + 1, p2 + 1) + 1;
        } else {
            memo[p1][p2] = Math.max(dp(p1 + 1, p2), dp(p1, p2 + 1));
        }

        return memo[p1][p2];
    }
}
