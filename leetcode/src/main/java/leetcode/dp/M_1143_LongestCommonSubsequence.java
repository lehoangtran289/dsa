package leetcode.dp;

public class M_1143_LongestCommonSubsequence {
    public static void main(String[] args) {
        System.out.println(new M_1143_LongestCommonSubsequence().longestCommonSubsequence("abcde", "ace"));
    }

    /**
     * LCS memo
     * Complexity Analysis
     * Time complexity : O(M⋅N).
     * This time, solving each subproblem has a cost of O(1). Again, there are M⋅N subproblems, and so we get a total time complexity of O(M⋅N).
     * Space complexity : O(M⋅N).
     * We need to store the answer for each of the M⋅N subproblems.
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

    // -------------------------------------------------------------------------------------------

    /**
     * LCS bottom up
     * Complexity Analysis
     * Time complexity : O(M⋅N).
     * We're solving M⋅N subproblems. Solving each subproblem is an O(1) operation.
     * Space complexity : O(M⋅N).
     * We're allocating a 2D array of size M⋅N to save the answers to subproblems.
     */
    public int longestCommonSubsequence2(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for (int i = text1.length() - 1; i >= 0; --i) {
            for (int j = text2.length() - 1; j >= 0; --j) {
                //there were two cases.
                //1. The first letter of each string is the same.
                //2. The first letter of each string is different.
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(
                            dp[i + 1][j],
                            dp[i][j + 1]
                    );
                }
            }
        }

        return dp[0][0];
    }
}
