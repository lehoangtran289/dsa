package leetcode.dp;

public class H_1770_MaximumScoreFromPerformingMultiplicationOperations {
    // -----------------------------------------------------------------------------------------------------------------
    // DP Top down approach
    // -----------------------------------------------------------------------------------------------------------------
    private int[] nums;
    private int[] multipliers;
    private int[][] memo;

    public int maximumScore(int[] nums, int[] multipliers) {
        this.nums = nums;
        this.multipliers = multipliers;
        this.memo = new int[multipliers.length][nums.length];
        return dp(0, 0);
    }

    public int dp(int i, int left) {
        if (i == multipliers.length) {
            return 0;
        }

        if (memo[i][left] != 0) {
            return memo[i][left];
        }

        int right = nums.length - 1 - (i - left);
        memo[i][left] = Math.max(
                dp(i + 1, left + 1) + multipliers[i] * nums[left],
                dp(i + 1, left) + multipliers[i] * nums[right]
        );

        return memo[i][left];
    }

    // -----------------------------------------------------------------------------------------------------------------
    // DP Bottom up approach
    // -----------------------------------------------------------------------------------------------------------------
    public int maximumScore2(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        int[][] dp = new int[m + 1][n + 1];

        for (int i = m - 1; i >= 0; --i) {
            for (int left = i; left >= 0; --left) {
                int right = (n - 1) - (i - left);
                dp[i][left] = Math.max(
                        dp[i + 1][left + 1] + multipliers[i] * nums[left],
                        dp[i + 1][left] + multipliers[i] * nums[right]
                );
            }
        }

        return dp[0][0];
    }
}
