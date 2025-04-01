package leetcode.dp;

public class M_2140_SolvingQuestionsWithBrainpower {
    private int[][] questions;
    private long[] memo;

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * Top-down DP
     * TC: O(n)
     * SC: O(n)
     * -----------------------------------------------------------------------------------------------------------------
     */
    public long mostPoints1(int[][] questions) {
        int n = questions.length;

        this.questions = questions;
        this.memo = new long[n + 1];

        return dp(0);
    }

    private long dp(int i) {
        if (i > questions.length - 1) return 0;
        if (memo[i] != 0) return memo[i];

        int point = questions[i][0];
        int brainpower = questions[i][1];

        memo[i] = Math.max(
                point + dp(i + brainpower + 1),
                dp(i + 1)
        );
        return memo[i];
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * Bottom-up DP
     * TC: O(n)
     * SC: O(n)
     * -----------------------------------------------------------------------------------------------------------------
     */
    public long mostPoints2(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1];

        for (int i = n - 1; i >= 0; --i) {
            int point = questions[i][0];
            int brainpower = questions[i][1];

            long take = i + brainpower + 1 > dp.length - 1 ?
                    point :
                    point + dp[i + brainpower + 1];
            long skip = dp[i + 1];

            dp[i] = Math.max(take, skip);
        }

        return dp[0];
    }
}
