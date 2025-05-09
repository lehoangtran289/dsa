package leetcode.dp;

public class g {
    public static void main(String[] args) {
        System.out.println(maxSubArrayKadane(new int[]{5, 4, -1, 7, 8})); // 23
    }

    public static int maxSubArrayDp(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        int res = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    /**
     * Kadane approach
     * -----------------------
     * Idea: at each index i-th, determines if elements before index i-th are "worth" keeping, or if they should be "discarded"
     * If adding a number make current sum smaller -> start at new position
     * -----------------------
     * TC: O(n)
     * SC: O(1)
     */
    public static int maxSubArrayKadane(int[] nums) {
        int res = nums[0];
        int cur = nums[0];

        for (int i = 1; i < nums.length; ++i) {
            cur = Math.max(cur + nums[i], nums[i]);
            res = Math.max(res, cur);
        }

        return res;
    }
}
