package leetcode.dp;

public class M_1143_LongestCommonSubsequence {
    public static void main(String[] args) {
        System.out.println(new M_1143_LongestCommonSubsequence().longestCommonSubsequence("abcde", "ace"));
    }

    /**
     * LCS
     */

    private int[][] dp;

    public int longestCommonSubsequence(String text1, String text2) {
        dp = new int[text1.length() + 1][text2.length() + 1];

        // keep edge = 0 to handle case when index = text.length()
        for (int i = 0; i < dp.length - 1; ++i) {
            for (int j = 0; j < dp[0].length - 1; ++j) {
                dp[i][j] = -1;
            }
        }

        return solve(text1, text2, 0, 0);
    }

    private int solve(
            String text1,
            String text2,
            int p1,
            int p2
    ) {
        // Check whether we've already solved this sub problem.
        // This also covers the base cases where p1 == text1.length
        // or p2 == text2.length
        if (dp[p1][p2] != -1) {
            return dp[p1][p2];
        }

        int res;
        if (text1.charAt(p1) == text2.charAt(p2)) {
            res = solve(text1, text2, p1 + 1, p2 + 1);
        } else {
            res = Math.max(
                    solve(text1, text2, p1 + 1, p2),
                    solve(text1, text2, p1, p2 + 1)
            );
        }

        dp[p1][p2] = res;
        return dp[p1][p2];
    }
}
