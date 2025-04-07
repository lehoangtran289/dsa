package leetcode.dp;

public class M_416_PartitionEqualSubsetSum {
    public static void main(String[] args) {
        System.out.println(new M_416_PartitionEqualSubsetSum().canPartition2(
                new int[]{1, 5, 11, 5}
        )); // true
    }

    /**
     * TOP DOWN DP
     * This problem is a variation of the 0/1 Knapsack problem.
     */
    private int[] nums;
    private Boolean[][] memo;
    private int subsetSum;

    public boolean canPartition(int[] nums) {
        int total = 0;
        for (int n : nums) total += n;
        if (total % 2 != 0) return false;
        subsetSum = total / 2;

        this.nums = nums;
        this.memo = new Boolean[nums.length + 1][subsetSum + 1];
        return dp(subsetSum, 0);
    }

    private boolean dp(
            int curSum,
            int i
    ) {
        if (curSum == subsetSum) return true;
        if (i == nums.length - 1 || curSum < 0) return false;
        if (memo[i][curSum] != null) return memo[i][curSum];

        memo[i][curSum] = dp(curSum + nums[i], i + 1) || dp(curSum, i + 1);
        return memo[i][curSum];
    }

    /**
     * BOTTOM UP DP
     * This problem is a variation of the 0/1 Knapsack problem.
     */

    public boolean canPartition2(int[] nums) {
        int n = nums.length;

        int total = 0;
        for (int num : nums) total += num;
        if (total % 2 != 0) return false;
        int subsetSum = total / 2;

        // DP table
        // dp[i][w] = true if we can get sum w using first i elements
        boolean[][] dp = new boolean[n + 1][subsetSum + 1];
        dp[0][0] = true;

        for (int i = 1; i <= n; ++i) {
            int curr = nums[i - 1];
            for (int j = 0; j <= subsetSum; ++j) {
                if (curr > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j - curr] || dp[i - 1][j];
                }
            }
        }

        return dp[n][subsetSum];
    }
}
