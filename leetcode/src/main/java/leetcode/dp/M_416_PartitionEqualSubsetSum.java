package leetcode.dp;

/**
 * Variation of the 0/1 Knapsack problem.
 */
public class M_416_PartitionEqualSubsetSum {
    /**
     * Top down dp
     * --------------------------------
     * TC: O(n*sum)
     * SC: O(n*sum)
     */
    private int[] nums;

    public static void main(String[] args) {
        System.out.println(new M_416_PartitionEqualSubsetSum().canPartition1(
                new int[]{1, 5, 11, 5}
        )); // true
    }

    /**
     * Bottom up DP 1D
     * --------------------------------
     * dp[sum] = can we achieve <sum> using cur element
     * e.g:
     * num = 2 -> traverse from sum -> 2, if we can achieve <sum>
     * if we can, that means dp[sum] = true or dp[sum - 2] = true
     * => dp[sum] = dp[sum] || dp[sum - 2]
     * --------------------------------
     * TC: O(n*sum)
     * SC: O(sum)
     */
    public boolean canPartition0(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;

        if (sum % 2 != 0) return false;
        sum /= 2;

        int n = nums.length;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int j = sum; j >= num; --j) {
                dp[j] = dp[j] || dp[j - num];
            }
        }

        return dp[sum];
    }

    /**
     * Bottom up DP 2D
     * * dp[i][sum] = can we achieve subset <sum> using first <i> elements
     * * dp[i][sum] = dp[i - 1][sum] || dp[i - 1][sum - nums[i]] // not take || take
     * --------------------------------
     * TC: O(n*sum)
     * SC: O(n*sum)
     */
    public boolean canPartition1(int[] nums) {
        int n = nums.length;

        int total = 0;
        for (int num : nums) total += num;
        if (total % 2 != 0) return false;
        int subsetSum = total / 2;

        boolean[][] dp = new boolean[n + 1][subsetSum + 1];
        dp[0][0] = true;

        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= subsetSum; ++j) {
                int curr = nums[i - 1];
                if (curr > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - curr]; // not take || take
                }
            }
        }

        return dp[n][subsetSum];
    }
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
}
