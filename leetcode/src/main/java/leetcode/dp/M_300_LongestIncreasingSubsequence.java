package leetcode.dp;

import java.util.Arrays;

public class M_300_LongestIncreasingSubsequence {
    public static void main(String[] args) {
        M_300_LongestIncreasingSubsequence obj = new M_300_LongestIncreasingSubsequence();
        int[] nums = new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println(obj.lengthOfLIS2(nums)); // 6
    }

    /**
     * Bottom up DP approach.
     * ----------------------------------
     * Let's say we know dp[0], dp[1], and dp[2].
     * How can we find dp[3] given this information?
     * Well, since dp[2] represents the length of the longest increasing subsequence that ends with nums[2],
     * if nums[3] > nums[2], then we can simply take the subsequence ending at i = 2 and append nums[3] to it, increasing the length by 1
     * ----------------------------------
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     * dp[i] = 1 OR dp[j] + 1 for j < i and nums[j] < nums[i], where dp[j] is the length of the longest increasing subsequence ending at index j.
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int res = 1;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[j] > nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    /**
     * Top down DP approach
     */
    private int[] nums;
    private int[] memo; // memo[i] = length of longest increasing subsequence starting at index i

    public int lengthOfLIS2(int[] nums) {
        this.nums = nums;
        this.memo = new int[nums.length + 1];
        Arrays.fill(memo, 1);

        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            res = Math.max(res, dp(i));
        }

        return res;
    }

    private int dp(int i) {
        if (memo[i] != 1) return memo[i];

        for (int j = 0; j < i; ++j) {
            if (nums[i] > nums[j]) {
                memo[i] = Math.max(memo[i], dp(j) + 1);
            }
        }

        return memo[i];
    }
}
