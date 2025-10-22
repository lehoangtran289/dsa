package leetcode.dp;

public class M_213_HouseRobberII {

    /**
     * Idea: since houses in circle -> cannot rob house[n - 1] & house[0] at 1 time.
     * -> res = max(rob[0 : n - 2], rob[1 : n - 1])
     * -------------------------
     * DP
     */
    public int rob(int[] nums) {
        int n = nums.length;

        // base case
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);

        return Math.max(
                dp(nums, 0, n - 2),
                dp(nums, 1, n - 1)
        );
    }

    /**
     * House Robber I
     */
    private int dp(int[] nums, int l, int r) {
        int n = nums.length;
        int[] dp = new int[n];

        dp[l] = nums[l];
        dp[l + 1] = Math.max(dp[l], nums[l + 1]);

        for (int i = l + 2; i <= r; ++i) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[r];
    }
}
