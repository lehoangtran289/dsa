package leetcode.dp;

public class M_53_MaximumSubarray {
    public static void main(String[] args) {
        int[] input = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println(maxSubArrayKadane(input)); // 6
        maxSubArrayKadane2(input);
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

    /**
     * Kadane approach with start and end index
     */
    public static void maxSubArrayKadane2(int[] nums) {
        int res = nums[0];
        int cur = nums[0];
        int start = 0, end = 0;

        for (int i = 1; i < nums.length; ++i) {
            if (cur < 0) {
                cur = nums[i];
                start = i;
            } else {
                cur += nums[i];
            }

            if (cur > res) {
                res = cur;
                end = i;
            }
        }

        System.out.println("Max sum subarray: " + res);
        System.out.println("Start index: " + start);
        System.out.println("End index: " + end);
    }
}
