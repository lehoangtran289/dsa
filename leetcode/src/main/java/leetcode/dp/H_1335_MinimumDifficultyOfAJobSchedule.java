package leetcode.dp;

public class H_1335_MinimumDifficultyOfAJobSchedule {
    private int d;
    private int[] jobDifficulty;
    private int[][] memo;
    private int[] hardestRemaining;

    /**
     * DP Top down approach
     * With iteration in recursion relation
     */
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (d > n) return -1;

        this.jobDifficulty = jobDifficulty;
        this.d = d;
        this.memo = new int[n + 1][d + 1];
        this.hardestRemaining = new int[n];

        int hardestJob = 0;
        for (int i = n - 1; i >= 0; --i) {
            hardestRemaining[i] = Math.max(hardestJob, jobDifficulty[i]);
            hardestJob = hardestRemaining[i];
        }

        for (int i = 0; i < n + 1; ++i) {
            for (int j = 0; j < d + 1; ++j) {
                memo[i][j] = -1;
            }
        }

        return dp(0, 1);
    }

    private int dp(int i, int day) {
        if (day == d) return hardestRemaining[i];
        if (memo[i][day] != -1) return memo[i][day];

        int remainDays = d - day;
        int hardestSoFar = 0, best = Integer.MAX_VALUE;

        // Iterate through the options and choose the best
        for (int j = i; j < jobDifficulty.length - remainDays; ++j) {
            hardestSoFar = Math.max(hardestSoFar, jobDifficulty[j]);

            // recursion relation
            best = Math.min(
                    best,
                    hardestSoFar + dp(j + 1, day + 1)
            );
        }
        memo[i][day] = best;

        return best;
    }
}
