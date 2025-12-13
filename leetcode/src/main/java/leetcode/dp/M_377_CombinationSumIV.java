package leetcode.dp;

public class M_377_CombinationSumIV {
    public static void main(String[] args) {
        System.out.println(combinationSum4(new int[]{1, 2}, 4)); // 5
    }

    /**
     * DP Bottom up - Combination Sum IV
     * ----------------------------------
     * dp[i] = number of combinations to get sum i
     * Recurrence relation:
     * dp[i] += dp[i - num] for num in nums
     * ----------------------------------
     * TC: O(n * target)
     * SC: O(target)
     */
    public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        // for each sum, can we use num in nums as last element?
        for (int i = 1; i <= target; ++i) {
            for (int num : nums) {
                if (i >= num) dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }
}
