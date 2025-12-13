package leetcode.dp;

import java.util.Arrays;
import java.util.List;

public class M_2915_LengthOfTheLongestSubsequenceThatSumsToTarget {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubsequence(
                Arrays.asList(1, 2, 3, 4, 5), 7
        )); // 3
    }

    /**
     * DP 1D Knapsack
     * ----------------------------------
     * dp[i] = length of longest subsequence that sums to i
     * Recurrence relation:
     * dp[i] = max(dp[i], dp[i - num] + 1)
     * ----------------------------------
     * TC: O(n * target)
     * SC: O(target)
     */
    public static int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int[] dp = new int[target + 1];

        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int num : nums) {
            for (int i = target; i >= num; --i) {
                if (dp[i - num] != -1) {
                    dp[i] = Math.max(dp[i], dp[i - num] + 1);
                }
            }
        }

        return dp[target];
    }

    /**
     * DP 2D Knapsack
     * ----------------------------------
     * dp[i][j] = length of longest subsequence using first i numbers that sums to j
     * Recurrence relation:
     * dp[i][j] = max(
     * dp[i-1][j], // don't take current number
     * dp[i-1][j - num] + 1 // take current number
     * )
     * ----------------------------------
     * TC: O(n * target)
     * SC: O(n * target)
     */
    public static int lengthOfLongestSubsequence2(List<Integer> nums, int target) {
        int n = nums.size();
        int[][] dp = new int[n + 1][target + 1];

        // Initialize impossible states with -1 or negative infinity
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
            dp[i][0] = 0; // can always make sum 0 with empty subsequence
        }

        for (int i = 1; i <= n; ++i) {
            int num = nums.get(i - 1);

            for (int j = 1; j <= target; ++j) {
                // Option 1: don't take current number
                dp[i][j] = dp[i - 1][j];

                // Option 2: take current number (if possible)
                if (j >= num && dp[i - 1][j - num] != -1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - num] + 1);
                }
            }
        }

        return dp[n][target];
    }
}
