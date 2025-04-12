package leetcode.dp;

public class M_1143_LongestCommonSubsequence {
    public static void main(String[] args) {
        System.out.println(new M_1143_LongestCommonSubsequence().longestCommonSubsequence("abcde", "ace"));
    }

    /**
     * Top down DP approach
     * ----------------------------------
     * TC O(M⋅N)
     * This time, solving each subproblem has a cost of O(1). Again, there are M⋅N subproblems, and so we get a total time complexity of O(M⋅N).
     * SC : O(M⋅N).
     * We need to store the answer for each of the M⋅N subproblems.
     */
    private int[][] dp;
    private String text1;
    private String text2;
    
    public int longestCommonSubsequence(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
        dp = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 0; i < dp.length; ++i) {
            for (int j = 0; j < dp[0].length; ++j) {
                dp[i][j] = -1;
            }
        }

        return dp(0, 0);
    }

    /**
     * Counting DP
     */
    private int dp(int p1, int p2) {
        if (p1 >= text1.length() || p2 >= text2.length()) {
            return 0;
        }
        if (dp[p1][p2] != -1) {
            return dp[p1][p2];
        }

        if (text1.charAt(p1) == text2.charAt(p2)) {
            dp[p1][p2] = dp(p1 + 1, p2 + 1) + 1;
        } else {
            dp[p1][p2] = Math.max(
                    dp(p1 + 1, p2),
                    dp(p1, p2 + 1)
            );
        }

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
