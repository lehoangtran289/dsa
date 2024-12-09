package leetcode.dp;

public class M_53_MaximumSubarray {
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

    public static int maxSubArrayKadane(int[] nums) {
        // Initialize our variables using the first element.
        int currSum = nums[0];
        int maxSum = nums[0];

        // Start with the 2nd element since we already used the first one.
        for (int i = 1; i < nums.length; i++) {
            // If current_subarray is negative, throw it away. Otherwise, keep adding to it.
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }
}
