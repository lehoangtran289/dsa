package leetcode.dp;

public class M_1262_GreatestSumDivisibleByThree {
    public static void main(String[] args) {
        System.out.println(maxSumDivThree(new int[]{3, 6, 5, 1, 8})); // 18
        System.out.println(maxSumDivThree(new int[]{2, 6, 2, 2, 7})); // 15
    }

    /**
     * Bottom up DP
     * --------------------------------
     * dp[i][j] = max sum using first i-th elements with remainder j when divided by 3
     * dp[i][j] = max(
     * dp[i-1][j], // not take
     * dp[i-1][previousRemainder] + cur_num // take
     * )
     * where previousRemainder is calculated so that (previousRemainder + cur_num) % 3 = j
     * --------------------------------
     * TC: O(n)
     * SC: O(n)
     * --------------------------------
     */
    public static int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 1][3]; // max sum using first i-th elements

        // init base cases
        dp[0][1] = (int) -1e9;
        dp[0][2] = (int) -1e9;

        // dp
        for (int i = 1; i <= n; ++i) {
            int curNum = nums[i - 1];

            for (int j = 0; j < 3; ++j) {
                int notTake = dp[i - 1][j];

                int prevRemainder = (j + 3 - curNum % 3) % 3;
                int take = dp[i - 1][prevRemainder] + curNum;

                dp[i][j] = Math.max(notTake, take);
            }
        }

        return dp[n][0];
    }
}
