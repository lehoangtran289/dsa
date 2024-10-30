package leetcode.dp;

import java.util.Arrays;

public class M_300_LongestIncreasingSubsequence {
    public static void main(String[] args) {
        M_300_LongestIncreasingSubsequence obj = new M_300_LongestIncreasingSubsequence();
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(obj.lengthOfLIS(nums)); // 4
    }

    /**
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     * Dynamic programming approach.
     * LIS[i] = 1 OR LIS[j] + 1 for j < i and nums[j] < nums[i], where LIS[j] is the length of the longest increasing subsequence ending at index j.
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int maxLength = 0;
        for (int i = 1; i < nums.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        for (int n : dp) {
            maxLength = Math.max(maxLength, n);
        }

        return maxLength;
    }
}
