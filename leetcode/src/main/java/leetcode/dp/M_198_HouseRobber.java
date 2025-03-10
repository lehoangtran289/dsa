package leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class M_198_HouseRobber {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{2, 7, 9, 3, 1}));
    }

    /**
     * DP bottom up (tabulation)
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);

        for (int i = 2; i < n; ++i) {
            dp[i] = Math.max(
                    dp[i - 1],
                    dp[i - 2] + nums[i]
            );
        }

        return dp[n - 1];
    }

    /**
     * DP Memoization
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int rob2(int[] nums) {
        Map<Integer, Integer> memo = new HashMap<>();
        return dp(nums, memo, nums.length - 1);
    }

    private static int dp(
            int[] nums,
            Map<Integer, Integer> memo,
            int i
    ) {
        if (i == 0) return nums[0];
        if (i == 1) return Math.max(nums[0], nums[1]);

        if (!memo.containsKey(i)) {
            memo.put(i, Math.max(
                    dp(nums, memo, i - 1),
                    dp(nums, memo, i - 2) + nums[i]
            ));
        }

        return memo.get(i);
    }
}
