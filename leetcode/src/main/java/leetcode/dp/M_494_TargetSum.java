package leetcode.dp;

public class M_494_TargetSum {
    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    }

    /**
     * DP 2D (Knapsack variation)
     * ----------------
     * Idea:
     * dp[i][j] = number of ways to get sum j using first i numbers
     * For each number, we have two choices: add it or subtract it.
     * dp[i][j] = dp[i-1][j - nums[i-1]] + dp[i-1][j + nums[i-1]]
     * ----------------
     * TC: O(n * m), n = nums.length, m = sum of nums
     * SC: O(n * m)
     * ----------------
     */
    public static int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        if (Math.abs(target) > totalSum) return 0; // base case

        int[][] dp = new int[n + 1][2 * totalSum + 2];
        dp[0][totalSum] = 1; // totalSum = 0 index

        for (int i = 1; i <= n; i++) {
            int curNum = nums[i - 1];

            for (int j = 0; j <= 2 * totalSum + 1; j++) {
                if (j - curNum >= 0) {
                    dp[i][j] += dp[i - 1][j - curNum];
                }
                if (j + curNum < 2 * totalSum + 2) {
                    dp[i][j] += dp[i - 1][j + curNum];
                }
            }
        }
        return dp[n][totalSum + target];
    }

    // BRUTE FORCE RECURSION SOLUTION O(2^n)----------------------------------------------------------------------------

    static int res = 0;
    public static int findTargetSumWays0(int[] nums, int target) {
        check(nums, target, 0, 0);
        return res;
    }

    private static void check(int[] nums, int target, int curIdx, int curSum) {
        if (curIdx == nums.length - 1) {
            if (curSum == target) {
                res++;
            }
        } else {
            // +
            check(nums, target, curIdx + 1, curSum + nums[curIdx]);

            // -
            check(nums, target, curIdx + 1, curSum - nums[curIdx]);
        }
    }
}
